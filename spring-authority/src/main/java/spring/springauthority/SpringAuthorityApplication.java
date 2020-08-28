package spring.springauthority;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import spring.springauthority.service.impl.UmsAdminServiceImpl;

@MapperScan(value = {"spring.springauthority.mapper"})
@SpringBootApplication
public class SpringAuthorityApplication {
    @Autowired
    UmsAdminServiceImpl umsAdminService;


    public static void main(String[] args) {
        SpringApplication.run(SpringAuthorityApplication.class, args);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> umsAdminService.loadUserByUsername(username);
    }

}
