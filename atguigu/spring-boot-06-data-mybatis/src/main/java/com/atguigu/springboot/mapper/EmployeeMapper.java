package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

//@Mapper或者@MapperScan将接口扫描装配到容器中
@Repository
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
