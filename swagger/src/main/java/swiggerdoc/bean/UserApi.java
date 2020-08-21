package swiggerdoc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hjd on 2020-08-21 14:48
 * -->
 **/
@Data
@Accessors(chain = true)
@ApiModel(value="user对象", description="资讯（动态、长文）表")
public class UserApi {
    @ApiModelProperty(value = "用户id")
    private String uuid;
    @ApiModelProperty(value = "用户name")
    private String name;
    @ApiModelProperty(value = "用户age")
    private int age;
}
