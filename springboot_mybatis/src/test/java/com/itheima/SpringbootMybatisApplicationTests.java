package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
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

        //-------------------------------------------查询-----------------------------------------------
        //service
        List<Employee> objects = emloyeeServicep.list(new QueryWrapper<Employee>().eq("id", 2));
        List<Employee> objectss = emloyeeServicep.list(new QueryWrapper<Employee>().ne("id",2));
        List<Employee> objectssnotdate = emloyeeServicep.list(new QueryWrapper<Employee>().select(Employee.class, i -> !"date".equals(i.getColumn()))); //排除某个字段
        List<Employee> objects2 = emloyeeServicep.list(new QueryWrapper<Employee>().lambda().eq(Employee::getGender,2).eq(Employee::getLastName,"黄俊东"));
        List<Employee> objects23 = emloyeeServicep.list(new QueryWrapper<Employee>().lambda().eq(Employee::getId,2).and(i->i.eq(Employee::getLastName,"黄俊洞")));
        List<Employee> objects3 = emloyeeServicep.list(new QueryWrapper<Employee>().select("id,LastName").eq("id",2));
        List<Employee> objects4 = emloyeeServicep.list(new QueryWrapper<Employee>().lambda().select(Employee::getId,Employee::getLastName).eq(Employee::getId,2));
        List<Employee> objects5 = emloyeeServicep.list(Wrappers.<Employee>lambdaQuery().select(Employee::getId, Employee::getLastName).eq(Employee::getId, 2));
        List<Employee> objects57 = emloyeeServicep.query().eq("id", 2).list();


        LambdaQueryWrapper<Employee> q2 = new QueryWrapper<Employee>().lambda();
        q2.eq(Employee::getGender,2);
        q2.eq(Employee::getLastName,"hjd");
        List<Employee> list = emloyeeServicep.list(q2);


        //-------------------------------------------分页-----------------------------------------------
        //分页查询--【与PageHelper组合使用】一般先设置分页对象，传递此分页对象的参数。到业务逻辑中进行使用
        com.github.pagehelper.Page pageNew = new com.github.pagehelper.Page();
        com.github.pagehelper.Page page1 = pageNew.setPageNum(1).setPageSize(3).setOrderBy("id DESC");
        //排序
        PageHelper.orderBy(page1.getOrderBy());
        //分页
        PageHelper.startPage(page1.getPageNum(),page1.getPageSize());
        List<Employee> objectspage = emloyeeServicep.list(new QueryWrapper<Employee>());

        //new QueryWrapper<Employee>().lambda() == Wrappers.<Employee>lambdaQuery()




        //-------------------------------------------更新-----------------------------------------------
        //第一个参数为空的话，第二个参数里必须加入更新条件，不不然报错，
        //若第二个参数有更新条件的话，第一个参数可以不加入更新条件
        emloyeeServicep.update(new Employee().setLastName("222222222"), new QueryWrapper<Employee>().lambda().eq(Employee::getId,2));
        emloyeeServicep.update(new UpdateWrapper<Employee>().lambda().set(Employee::getLastName,"updata").eq(Employee::getId,2));
        //前一个条件后一个条件的更新
        emloyeeServicep.update(new Employee().setEmail("1622@qq.com"),Wrappers.<Employee>lambdaUpdate().set(Employee::getLastName,"黄俊东").eq(Employee::getId,2));




        //-------------------mapper 使用方式和上述差不多一致
        List<Employee> mp1 = employeeMapper.selectList(Wrappers.<Employee>query().select("id,LastName").eq("LastName","hjd"));
        List<Employee> mp2 = employeeMapper.selectList(Wrappers.<Employee>lambdaQuery().select(Employee::getId, Employee::getLastName).eq(Employee::getId, 2));


        //分页查询(适合用mapper)
        Page<Employee> employeePage = employeeMapper.selectPage(new Page<Employee>(1, 2), Wrappers.<Employee>query().select("id,LastName").eq("LastName", "hjd"));
        IPage<Map<String, Object>> page = employeeMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<Employee>query().select("id,LastName").eq("LastName", "hjd"));

        employeePage.getRecords().forEach(System.out::println);
        page.getRecords().forEach(System.out::println);

    }
    @Test
    public void t3(){
        List<Employee> objects3 = emloyeeServicep.list(new QueryWrapper<Employee>().select("date").eq("id",21));
        System.out.println("数据查询得到的时间数据为："+objects3);
    }

}
