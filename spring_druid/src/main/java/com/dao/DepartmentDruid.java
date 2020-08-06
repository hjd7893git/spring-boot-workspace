package com.dao;

import java.io.Serializable;

/**
 * department
 * @author 
 */
public class DepartmentDruid implements Serializable {
    private Integer id;

    private String departmentname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

}