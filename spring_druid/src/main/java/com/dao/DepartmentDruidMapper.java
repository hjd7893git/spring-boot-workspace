package com.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DepartmentDruidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DepartmentDruid record);

    int insertSelective(DepartmentDruid record);

    DepartmentDruid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DepartmentDruid record);

    int updateByPrimaryKey(DepartmentDruid record);
}