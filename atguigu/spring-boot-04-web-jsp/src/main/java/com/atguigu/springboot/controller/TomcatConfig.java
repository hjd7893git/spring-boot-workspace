//package com.atguigu.springboot.controller;
//
//import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//
//@Configuration
//public class TomcatConfig {
//    @Bean
//    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
//        factory.setDocumentRoot(new File("F:\\迅雷下载\\源码、资料、课件\\代码\\spring-boot-04-web-jsp\\src\\main\\webapp\\"));
//        return (EmbeddedServletContainerFactory) factory;
//    }
//}
