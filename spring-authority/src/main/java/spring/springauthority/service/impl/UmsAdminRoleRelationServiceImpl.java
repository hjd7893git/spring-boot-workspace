package spring.springauthority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.springauthority.entity.UmsAdminRoleRelation;
import spring.springauthority.entity.UmsResource;
import spring.springauthority.mapper.UmsAdminRoleRelationMapper;
import spring.springauthority.service.IUmsAdminRoleRelationService;

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
public class UmsAdminRoleRelationServiceImpl extends ServiceImpl<UmsAdminRoleRelationMapper, UmsAdminRoleRelation> implements IUmsAdminRoleRelationService {
    @Autowired
    UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    public List<UmsResource> getResourceList(Long id) {
        return umsAdminRoleRelationMapper.getResourceList(id);
    }
}
