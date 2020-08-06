package com.service.imp;

import com.dao.DepartmentDruid;
import com.dao.DepartmentDruidMapper;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hjd on 2020-07-15 17:39
 * -->
 **/
@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentDruidMapper departmentDao;

    @Override
    public void add(DepartmentDruid department) {
        departmentDao.insert(department);
        int se = 1/0;
    }

    @Override
    public DepartmentDruid findById(int departmentId) {
        return departmentDao.selectByPrimaryKey(departmentId);
    }
}
