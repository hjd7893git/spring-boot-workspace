package spring.springauthority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import spring.springauthority.entity.UmsRole;
import spring.springauthority.mapper.UmsRoleMapper;
import spring.springauthority.service.IUmsRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-28
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

}
