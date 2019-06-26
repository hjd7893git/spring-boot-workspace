package com.example.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Administrator on 2019/5/29.
 */
//@RestController
@Controller
public class DemoApplicationForMVC {
//    @Override
//    public void addViewControllers(ViewControllerRegistry viewControllerRegistry){
//        viewControllerRegistry.addViewController("/xx").setViewName("/xy");
//    }

//    @Bean  //定义视图解析器
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/templates/");
//        resolver.setSuffix(".html");
////        resolver.setViewClass(HTML.class);
//        return resolver;
//    }

    @RequestMapping("/2")
    public String index2() {
        return "xy";
    }

    @RequestMapping("/3")
    public ModelAndView index3(ModelAndView modelAndView) {
        modelAndView.setViewName("xy");
        return modelAndView;

    }

    @RequestMapping("/3r")
    public ModelAndView index3r(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:xy.jsp");
        return modelAndView;

    }
}

