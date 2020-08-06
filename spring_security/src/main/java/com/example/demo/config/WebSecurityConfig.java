package com.example.demo.config;

import com.example.demo.security.CustomUserService;
import com.example.demo.security.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//1扩展SpringSecurity配置需继承WebSecurityConfigurerAdapter。

    @Autowired
    CustomUserService customUserService;  //2 注册CustomUserService的Bean。

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        自定义认证
        auth.authenticationProvider(myAuthenticationProvider);

//        //内存认证
//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("user").password("123").roles("ROLE");

        //自带认证
        auth.userDetailsService(customUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());//不加密
//                .passwordEncoder(new BCryptPasswordEncoder()); //加密
        //3添加我们自定义的user detail service认证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置权限
                .anyRequest().authenticated() //4 所有请求需要认证即登录后才能访问
                .and()
                .formLogin()
                    .loginPage("/login") //请求时未登录跳转接口
                    .failureUrl("/login?error")
                    .permitAll()    //5 定制登录行为，登录页面可任意访问。
                    .and()
                .authorizeRequests().antMatchers("/admin/login", "/admin/register").permitAll()// 对登录注册要允许匿名访问
                .and().logout().permitAll(); //6 定制注销行为，注销请求可任意访问。
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }
    /**
     * 这里简单总结一下，首先当用户发送请求的时候，会进入到UsernamePasswordAuthenticationFilter中得到一个UsernamePasswordAuthenticationToken，
     * 它其实相当于一个令牌，不过还没有经过认证，然后调用AuthenticationManager的实现类ProviderManager中判断登录方式是否支持，如果支持，
     * 则会调用AuthenticationProvider接口的抽象实现类AbstractUserDetailsAuthenticationProvider中调用它的扩展类DaoAuthenticationProvider中获取我们自己实现的MyUserDetails类获取用户密码进行用户身份验证，
     * 然后返回该对象，设置UsernamePasswordAuthenticationToken这个令牌认证通过，用户身份校验成功。
     *
     * 身份认证成功后，最后在UsernamePasswordAuthenticationFilter返回后会进入一个AbstractAuthenticationProcessingFilter类中调用successfulAuthentication方法中，
     * 这个方法最后会返回我们自己定义的登录成功处理器handler，在返回之前，它会调用SecurityContext，最后将认证的结果放入SecurityContextHolder中，SecurityContext类很简单
     * ，重写了equals方法和hascode方法，保证了authentication的唯一性。SecurityContextHolder类实际上对ThreadLocal的一个封装，可以在不同方法之间进行通信，我们可以简单理解为线程级别的一个全局变量。
     * 因此可以在同一个线程中的不同方法中获取到认证信息。最后会被SecurityContextPersistenceFilter过滤器使用，这个过滤器的作用是什么呢？当一个请求来的时候，它会将session中的值传入到该线程中，
     * 当请求返回的时候，它会判断该请求线程是否有SecurityContext，如果有它会将其放入到session中，因此保证了请求结果可以在不同的请求之间共享
     */
}