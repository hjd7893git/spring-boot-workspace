package com.example.demo;

import com.example.demo.domain.Msg;
import com.example.demo.domain.SysUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管 理员显示");
        System.out.println("====================================>>"+ SecurityContextHolder.getContext().getAuthentication());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken token =(UsernamePasswordAuthenticationToken)authentication;
        UserDetails userDetails = (UserDetails)token.getPrincipal();
        System.out.println("---------------------------->"+userDetails.getUsername());
        model.addAttribute("msg", msg);
        return "index";
    }
}