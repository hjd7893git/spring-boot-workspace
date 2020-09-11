package spring.springauthority.dy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import spring.springauthority.dy.DynamicSecurityService;
import spring.springauthority.entity.UmsResource;
import spring.springauthority.service.IUmsResourceService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hjd on 2020-08-31 18:07
 * -->使用时加入此bean
 **/
@Configuration
public class UseDyConfig {
    @Autowired
    IUmsResourceService iUmsResourceService;

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = iUmsResourceService.list();
                for (UmsResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                System.out.println("读取的资源权限：" + map.toString());
                return map;
            }
        };
    }
}
