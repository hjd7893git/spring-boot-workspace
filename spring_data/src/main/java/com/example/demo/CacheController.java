package com.example.demo;

import com.example.demo.cacheService.DemoCachServiceImpl;
import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching        //开启缓存支持
public class CacheController {
    @Autowired
    DemoCachServiceImpl demoService;

    @RequestMapping("/put")
    public Person put(Person person) {
        return demoService.save(person);
    }

    @RequestMapping("/able")
    public Person cacheable(Person person) {
        return demoService.findOne(person);
    }

    @RequestMapping("/evit")
    public String evit(Long id) {
        demoService.remove(id);
        return "ok";
    }
}