package com.service;


import com.dao.DepartmentDruid;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hjd on 2020-07-15 17:41
 * -->
 **/
public interface DepartmentService {
    @Transactional
    public void add(DepartmentDruid department);

    public DepartmentDruid findById(int departmentId);
}
