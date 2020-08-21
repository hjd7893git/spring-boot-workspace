package swiggerdoc.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import swiggerdoc.base.BaseResult;
import swiggerdoc.base.SameUrlData;
import swiggerdoc.bean.UserApi;

import java.util.Map;

/**
 * Created by hjd on 2020-08-21 14:46
 * -->
 **/
@RestController
@Api(value = "controller控制模块", tags = {"哈哈哈"})
public class ApiController {

    /**
     * POST请求
     */
    @ApiOperation(value = "精选TAB接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "token", value = "用户登陆后获取token", paramType = "body", dataType = "Map", required = true)})
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功")
            , @ApiResponse(code = -300, message = "参数不合法请检查必填项")
            , @ApiResponse(code = -400, message = "token失效！")
            , @ApiResponse(code = -900, message = "系统异常")
    })
    @PostMapping(value = "/post")
    @SameUrlData
    public BaseResult<UserApi> post(/*@RequestBody UserApi userApi,*/@RequestBody @ApiParam(name = "token",value ="用户登陆后获取token",type="body") Map<String, String> param, @ApiParam(value = "用户的id") String uuid) {
        UserApi user2 = new UserApi().setAge(Integer.parseInt(param.get("age"))).setName(param.get("name")).setUuid(uuid);
        BaseResult baseResult = new BaseResult().setData(user2);
        return baseResult;
    }

    @GetMapping(value = "/get")
    public String get() {
        return "哈喽！";
    }

}
