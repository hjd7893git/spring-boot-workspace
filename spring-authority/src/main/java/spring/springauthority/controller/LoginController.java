package spring.springauthority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.springauthority.dto.UmsAdminLoginParam;
import spring.springauthority.service.IUmsAdminService;

/**
 * Created by hjd on 2020-08-28 16:45
 * --> 登陆
 **/
@RestController
public class LoginController {
    @Autowired
    IUmsAdminService iUmsAdminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String password = umsAdminLoginParam.getPassword();
        String username = umsAdminLoginParam.getUsername();
        String toker = iUmsAdminService.login(username,password);
        if(toker!=null){
            System.out.println("登陆失败");
            return "error";
        }else{
            System.out.println("登陆成功");
            return "susscss";
        }



    }

}
