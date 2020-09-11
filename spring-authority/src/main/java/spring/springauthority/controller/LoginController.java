package spring.springauthority.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.springauthority.dto.UmsAdminLoginParam;
import spring.springauthority.service.IUmsAdminService;

/**
 * Created by hjd on 2020-08-28 16:45
 * --> 登陆
 **/
@Controller
public class LoginController {
    @Autowired
    IUmsAdminService iUmsAdminService;

    @PostMapping(value = "/loginToken")
    public String login(UmsAdminLoginParam umsAdminLoginParam) {
        System.out.println("hhahahaha");
        String password = umsAdminLoginParam.getPassword();
        String username = umsAdminLoginParam.getUsername();
        String toker = iUmsAdminService.login(username, password);
        if (toker != null) {
            System.out.println("登陆成功！！！！！！！！！！！！！！！！！！！");
        } else {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); //todo:比较重要
//            HttpServletRequest request = attributes.getRequest();
            return "redirect:/error.html";
        }
        return "redirect:/success.html";
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String login2(UmsAdminLoginParam umsAdminLoginParam) {
        System.out.println("hhahahaha");
        String password = umsAdminLoginParam.getPassword();
        String username = umsAdminLoginParam.getUsername();
        String toker = iUmsAdminService.login(username, password);
        if (toker != null) {
            System.out.println("登陆成功！！！！！！！！！！！！！！！！！！！");
        } else {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); //todo:比较重要
//            HttpServletRequest request = attributes.getRequest();
            return "redirect:/error.html";
        }
        return "redirect:/success.html";
    }


    @ResponseBody
//    @PreAuthorize("hasAuthority('1:测试1')") //todo:调用方法前进行判断
    @GetMapping(value = "/hjd1")
    public String hello() {
        return "hjd1";
    }

    @ResponseBody
//    @PreAuthorize("hasAuthority('2:测试2')") //todo:调用方法前进行判断
    @GetMapping(value = "/hjd2")
    public String hello2() {
        return "hjd2";
    }

    @ResponseBody
//    @PreAuthorize("hasAuthority('3:测试3')") //todo:调用方法前进行判断
    @GetMapping(value = "/hjd3")
    public String hello3() {
        return "hjd3";
    }

    @PostMapping(value = "/hello")
    public String hello233() {
        System.out.println("helllllllllllllo");
        return "success";
    }

    @ResponseBody
    @GetMapping(value = "/bai")
    public String bai() {
        return "bai";
    }

    @ResponseBody
    @GetMapping(value = "/hjd4")
    public String hjd4() {
        return "hjd4";
    }

}
