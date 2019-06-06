package com.example.demo.transactionalService;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepository personRepository; //1 可以直接注入我们的RersonRepository的Bean。

    @Transactional(rollbackFor = IllegalArgumentException.class) //2 使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("黄俊东")) {
            throw new IllegalArgumentException("黄俊东已存在， 数据将回滚"); //3 硬编码手动触发异常
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class}) //4 使用@Transactional注解的noRollbackFor属性，指定特定异常时，数据回滚。
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("黄俊东")) {
            throw new IllegalArgumentException("黄俊东虽已存 在，数据将不会回滚");
        }
        return p;
    }
}