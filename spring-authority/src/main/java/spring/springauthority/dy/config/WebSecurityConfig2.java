package spring.springauthority.dy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import spring.springauthority.config.IgnoreUrlsConfig;
import spring.springauthority.dy.DynamicAccessDecisionManager;
import spring.springauthority.dy.DynamicSecurityFilter;
import spring.springauthority.dy.DynamicSecurityMetadataSource;
import spring.springauthority.dy.DynamicSecurityService;
import spring.springauthority.service.IUmsAdminService;

@Order(1)
@Configuration
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {//1扩展SpringSecurity配置需继承WebSecurityConfigurerAdapter。

    @Autowired
    IUmsAdminService iUmsAdminService;

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //自带认证
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return iUmsAdminService.loadUserByUsername(username);
            }
        }).passwordEncoder(NoOpPasswordEncoder.getInstance());//不加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        for (String url : ignoreUrlsConfig().getUrls()) {
            http.authorizeRequests().antMatchers(url).permitAll();
        }

        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll(); //跨域请求会先进行一次options请求

        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated();

        http.formLogin().loginPage("/index");
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        http.rememberMe(); //记住我


        if (dynamicSecurityService != null) {
            http.authorizeRequests().and().addFilterBefore(dynamicSecurityFilter(), FilterSecurityInterceptor.class);
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }


    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

}