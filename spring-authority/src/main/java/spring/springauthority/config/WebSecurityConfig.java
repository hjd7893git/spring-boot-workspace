package spring.springauthority.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//1扩展SpringSecurity配置需继承WebSecurityConfigurerAdapter。


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //自带认证
        auth.userDetailsService(userDetailsService()).passwordEncoder(NoOpPasswordEncoder.getInstance());//不加密
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置权限
                .antMatchers("/admin/login", "/admin/register").permitAll()// 对登录注册要允许匿名访问
                .anyRequest().authenticated() //4 所有请求需要认证即登录后才能访问
                .and().formLogin()
                    .loginPage("/login") //请求时未登录跳转接口
                    .failureUrl("/login?error")
                    .permitAll()    //5 定制登录行为，登录页面可任意访问。
                .and().logout().permitAll(); //6 定制注销行为，注销请求可任意访问。
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
}