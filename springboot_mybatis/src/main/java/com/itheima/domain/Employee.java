package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Employee {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "lastName")
    private String lastName;
    private Integer gender;
    private String email;
    private Integer dId;
    private Date date;

    private int age;

    private int roleId;

}
