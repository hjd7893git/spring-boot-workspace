package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.domain.Employee;
import com.itheima.mapper.EmployeeMapper;
import com.itheima.service.EmloyeeService;
import org.springframework.stereotype.Service;

/**
 * Created by hjd on 2020-08-04 12:16
 * -->
 **/
@Service
public class EmloyeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmloyeeService {
}
