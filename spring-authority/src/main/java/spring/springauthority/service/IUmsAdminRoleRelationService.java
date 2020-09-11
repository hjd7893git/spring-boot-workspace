package spring.springauthority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import spring.springauthority.entity.UmsAdminRoleRelation;
import spring.springauthority.entity.UmsResource;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-28
 */
public interface IUmsAdminRoleRelationService extends IService<UmsAdminRoleRelation> {
    List<UmsResource> getResourceList(Long id);
}
