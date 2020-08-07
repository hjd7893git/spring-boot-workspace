package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.domain.Employee;
import com.itheima.domain.User;
import com.itheima.mapper.EmployeeMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.service.EmloyeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public EmployeeMapper employeeMapper;

    @Autowired
    public EmloyeeService emloyeeServicep;

    @Test
    public void contextLoads() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void t2(){
//        emloyeeServicep.save(new Employee().setLastName("hjd")); //开启set返回对象@Accessors(chain = true) //插入
        List<Employee> employees = employeeMapper.selectList(null);
        employees.forEach(System.out::println);
        //service
        List<Employee> objects = emloyeeServicep.list(new QueryWrapper<Employee>().eq("id",2));
        List<Employee> objects2 = emloyeeServicep.list(new QueryWrapper<Employee>().lambda().eq(Employee::getId,2));
        List<Employee> objects3 = emloyeeServicep.list(new QueryWrapper<Employee>().select("id,LastName").eq("id",2));
        List<Employee> objects4 = emloyeeServicep.list(new QueryWrapper<Employee>().lambda().select(Employee::getId,Employee::getLastName).eq(Employee::getId,2));
        List<Employee> objects5 = emloyeeServicep.list(Wrappers.<Employee>lambdaQuery().select(Employee::getId,Employee::getLastName).eq(Employee::getId,2));
        //new QueryWrapper<Employee>().lambda() == Wrappers.<Employee>lambdaQuery()

        //第一个参数为空的话，第二个参数里必须加入更新条件，不不然报错，
        //若第二个参数有更新条件的话，第一个参数可以不加入更新条件
        emloyeeServicep.update(new Employee().setLastName("dss"), new QueryWrapper<Employee>().lambda().eq(Employee::getId,2));
        emloyeeServicep.update(new UpdateWrapper<Employee>().lambda().set(Employee::getLastName,"updata").eq(Employee::getId,2));
        //前一个条件后一个条件的更新
        emloyeeServicep.update(new Employee().setEmail("1622@qq.com"),Wrappers.<Employee>lambdaUpdate().set(Employee::getLastName,"黄俊东").eq(Employee::getId,2));


        //mapper 使用方式和上述差不多一致
        List<Employee> mp1 = employeeMapper.selectList(Wrappers.<Employee>query().select("id,LastName").eq("LastName","hjd"));
        List<Employee> mp2 = employeeMapper.selectList(Wrappers.<Employee>lambdaQuery().select(Employee::getId, Employee::getLastName).eq(Employee::getId, 2));


        //分页查询(适合用mapper)
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<Employee>(1, 2), Wrappers.<Employee>query().select("id,LastName").eq("LastName", "hjd"));
        IPage<Map<String, Object>> page = employeeMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<Employee>query().select("id,LastName").eq("LastName", "hjd"));

        employeePage.getRecords().forEach(System.out::println);
        page.getRecords().forEach(System.out::println);

    }

}
