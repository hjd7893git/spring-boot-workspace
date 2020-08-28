package spring.springauthority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.springauthority.dto.UserLogin;
import spring.springauthority.entity.UmsAdmin;
import spring.springauthority.entity.UmsResource;
import spring.springauthority.mapper.UmsAdminMapper;
import spring.springauthority.mapper.UmsAdminRoleRelationMapper;
import spring.springauthority.mapper.UmsRoleMapper;
import spring.springauthority.service.IUmsAdminService;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-28
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements IUmsAdminService {

    @Autowired
    UmsRoleMapper umsRoleMapper;

    @Autowired
    UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public String login(String username, String password) {
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (password != userDetails.getPassword()) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //执行存储
            return userDetails.getUsername();
            //添加登陆记录
        } catch (AuthenticationException e) {
            System.out.println("异常登陆");
            return null;
        }

    }

    public UserDetails loadUserByUsername(String username) {
        List<UmsAdmin> listAdmin = this.list(new QueryWrapper<UmsAdmin>().lambda().eq(UmsAdmin::getUsername, username));
        if (listAdmin != null) {
            List<UmsResource> resourceList = umsAdminRoleRelationMapper.getResourceList(listAdmin.get(0).getId());
            return new UserLogin(listAdmin.get(0), resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

}
