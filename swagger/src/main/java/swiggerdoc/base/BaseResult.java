/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package swiggerdoc.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 返回的结果的父类
 *
 * @param <T>
 * @author Administrator
 * @version $Id: ResultItem.java, v 0.1 2016年2月25日 下午1:51:33 Administrator Exp $
 */
@Data
@Accessors(chain = true)
@ApiModel(value="BaseResult", description="基础返回对象")
public class BaseResult<T> {

    private String startTime;
    private int code;
    private String desc;
    @ApiModelProperty("对象信息")
    private T data;
    private String token;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public BaseResult() {
        desc = "";
        code = 0;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        startTime = df.format(new Date());// new Date()为获取当前系统时间
    }


}
