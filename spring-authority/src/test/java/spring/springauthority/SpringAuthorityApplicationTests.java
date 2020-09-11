package spring.springauthority;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.springauthority.entity.UmsAdmin;
import spring.springauthority.service.IUmsAdminService;

import java.util.List;

@SpringBootTest
class SpringAuthorityApplicationTests {

    @Autowired
    IUmsAdminService iUmsAdminService ;

    @Test
    public void t1(){
        List<UmsAdmin> list = iUmsAdminService.list();
        System.out.println(list);

    }

}
