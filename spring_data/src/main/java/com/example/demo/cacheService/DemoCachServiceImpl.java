package com.example.demo.cacheService;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DemoCachServiceImpl implements DemoCachService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people", key = "#person.id") //1@CachePut缓存新增的或更新的数据到缓存，其中缓存名称为people，数据的key是person的id。
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    @CacheEvict(value = "people") //2 @CacheEvict从缓存people中删除key为id的数据
    public void remove(Long id) {
        System.out.println("删除了id、key为" + id + "的数据缓存");
//        personRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "people", key = "#person.id") //3@Cacheable缓存key为person的id数据到缓存people中。
    public Person findOne(Person person) {
        Person p = personRepository.findById(person.getId()).get();
        System.out.println("为id、key为:" + p.getId() + "数据做了缓存");
        return p;
    }
    /*
    如果没有指定key，则方法参数作为key保存到缓存中
     */
}