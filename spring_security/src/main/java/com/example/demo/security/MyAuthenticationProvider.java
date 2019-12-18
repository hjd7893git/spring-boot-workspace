package com.example.demo.security;

import com.example.demo.dao.SysUserRepository;
import com.example.demo.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SysUser userDetails = sysUserRepository.findByUsername(authentication.getName());
        String password = authentication.getCredentials().toString();
        if (userDetails == null || userDetails.getUsername() == null) {
            throw new UsernameNotFoundException("用户名未找到");
        }
        //验证用户密码
        if (userDetails.getPassword().equals(password)) {
            //如果账户被禁用
            if (!userDetails.isEnabled()) {
                throw new DisabledException("用户被禁用");
            }
            System.out.println(userDetails.getUsername());
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }//用户密码错误
        throw new BadCredentialsException("用户凭证错误");
    }

    /**
     * todo：supports函数用来指明该Provider是否适用于该类型的认证，如果不合适，则寻找另一个Provider进行验证处理。
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        // [返回为true时执行，否则此认证器无效]
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
