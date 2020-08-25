package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.domain.Employee;
import com.itheima.mapper.EmployeeMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author miemie
 * @since 2018-08-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTest {

    @Autowired
    private EmployeeMapper userMapper;

    @Test
    public void tests() {
        System.out.println("----- 普通查询 ------");
        List<Employee> plainUsers = userMapper.selectList(new QueryWrapper<Employee>().eq("role_id", 2L));
        List<Employee> lambdaUsers = userMapper.selectList(new QueryWrapper<Employee>().lambda().eq(Employee::getRoleId, 2L));
//        Assert.assertEquals(plainUsers.size(), lambdaUsers.size());
        print(plainUsers);

        System.out.println("----- 带子查询(sql注入) ------");
        List<Employee> plainUsers2 = userMapper.selectList(new QueryWrapper<Employee>()
                .inSql("role_id", "select id from role where id = 2"));
        List<Employee> lambdaUsers2 = userMapper.selectList(new QueryWrapper<Employee>().lambda()
                .inSql(Employee::getRoleId, "select id from role where id = 2"));
//        Assert.assertEquals(plainUsers2.size(), lambdaUsers2.size());
        print(plainUsers2);

        System.out.println("----- 带嵌套查询 ------");
        List<Employee> plainUsers3 = userMapper.selectList(new QueryWrapper<Employee>()
                .nested(i -> i.eq("role_id", 2L).or().eq("role_id", 3L))
                .and(i -> i.ge("age", 20)));
        List<Employee> lambdaUsers3 = userMapper.selectList(new QueryWrapper<Employee>().lambda()
                .nested(i -> i.eq(Employee::getRoleId, 2L).or().eq(Employee::getRoleId, 3L))
                .and(i -> i.ge(Employee::getAge, 20)));
//        Assert.assertEquals(plainUsers3.size(), lambdaUsers3.size());
        print(plainUsers3);

        System.out.println("----- 自定义(sql注入) ------");
        List<Employee> plainUsers4 = userMapper.selectList(new QueryWrapper<Employee>()
                .apply("role_id = 2"));
        print(plainUsers4);

        UpdateWrapper<Employee> uw = new UpdateWrapper<>();
        uw.set("email", null);
        uw.eq("id", 4);
        userMapper.update(new Employee(), uw);
        Employee u4 = userMapper.selectById(4);
//        Assert.assertNull(u4.getEmail());


    }

    @Test
    public void lambdaQueryWrapper() {
        System.out.println("----- 普通查询 ------");
        List<Employee> plainUsers = userMapper.selectList(new LambdaQueryWrapper<Employee>().eq(Employee::getRoleId, 2L));
        List<Employee> lambdaUsers = userMapper.selectList(new QueryWrapper<Employee>().lambda().eq(Employee::getRoleId, 2L));
        Assert.assertEquals(plainUsers.size(), lambdaUsers.size());
        print(plainUsers);

        System.out.println("----- 带子查询(sql注入) ------");
        List<Employee> plainUsers2 = userMapper.selectList(new LambdaQueryWrapper<Employee>()
                .inSql(Employee::getRoleId, "select id from role where id = 2"));
        List<Employee> lambdaUsers2 = userMapper.selectList(new QueryWrapper<Employee>().lambda()
                .inSql(Employee::getRoleId, "select id from role where id = 2"));
        Assert.assertEquals(plainUsers2.size(), lambdaUsers2.size());
        print(plainUsers2);

        System.out.println("----- 带嵌套查询 ------");
        List<Employee> plainUsers3 = userMapper.selectList(new LambdaQueryWrapper<Employee>()
                .nested(i -> i.eq(Employee::getRoleId, 2L).or().eq(Employee::getRoleId, 3L))
                .and(i -> i.ge(Employee::getAge, 20)));
        List<Employee> lambdaUsers3 = userMapper.selectList(new QueryWrapper<Employee>().lambda()
                .nested(i -> i.eq(Employee::getRoleId, 2L).or().eq(Employee::getRoleId, 3L))
                .and(i -> i.ge(Employee::getAge, 20)));
        Assert.assertEquals(plainUsers3.size(), lambdaUsers3.size());
        print(plainUsers3);

        System.out.println("----- 自定义(sql注入) ------");
        List<Employee> plainUsers4 = userMapper.selectList(new QueryWrapper<Employee>()
                .apply("role_id = 2"));
        print(plainUsers4);

        UpdateWrapper<Employee> uw = new UpdateWrapper<>();
        uw.set("email", null);
        uw.eq("id", 4);
        userMapper.update(new Employee(), uw);
        Employee u4 = userMapper.selectById(4);
        Assert.assertNull(u4.getEmail());
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    /**
     * SELECT id,name,age,email,role_id FROM Employee
     * WHERE ( 1 = 1 ) AND ( ( name = ? AND age = ? ) OR ( name = ? AND age = ? ) )
     */
    @Test
    public void testSql() {
        QueryWrapper<Employee> w = new QueryWrapper<>();
        w.and(i -> i.eq("1", 1))
                .nested(i ->
                        i.and(j -> j.eq("name", "a").eq("age", 2))
                                .or(j -> j.eq("name", "b").eq("age", 2)));
        userMapper.selectList(w);
    }
}
