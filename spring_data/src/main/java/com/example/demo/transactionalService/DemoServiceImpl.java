package com.example.demo.transactionalService;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class DemoServiceImpl implements DemoService {

    /**
     * @Transactional 不仅可以注解在方法上，也可以注解在类上。当注解在类上的时候意味着此类的多有public方法都是开启事务的，如果类级别同事使用了@Transactional注解，则使用在类级别的
     * 注解回重载方法级别的注解
     * <p>
     * spring Data JPA 对所有的默认方法都开启了事务支持，且查询类事务默认启用readOnly = true属性
     * @Transactional(propagation = REQUIRED,isolation = Isolation.DEFAULT,readOnly = true)
     * <p>
     * name	当在配置文件中有多个 TransactionManager , 可以用该属性指定选择哪个事务管理器。
     * propagation	事务的传播行为，默认值为 REQUIRED。
     * isolation	事务的隔离度，默认值采用 DEFAULT。
     * timeout	事务的超时时间，默认值为-1。如果超过该时间限制但事务还没有完成，则自动回滚事务。
     * read-only	指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。
     * rollback-for	用于指定能够触发事务回滚的异常类型，如果有多个异常类型需要指定，各类型之间可以通过逗号分隔。
     * no-rollback- for	抛出 no-rollback-for 指定的异常类型，不回滚事务。
     *
     * 事务性的生效期是必须有相关的异常，而且是要被Transactional所捕获必须往上抛，
     */

    @Autowired
    PersonRepository personRepository; //1 可以直接注入我们的RersonRepository的Bean。 Repository里面都是事务性的

    @Transactional(rollbackFor = IllegalArgumentException.class) //2 使用@Transactional注解的rollbackFor属性，指定特定异常时，数据回滚
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("黄俊东")) {
            throw new IllegalArgumentException("黄俊东已存在， 数据将回滚"); //3 硬编码手动触发异常
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    //4 使用@Transactional注解的noRollbackFor属性，指定特定异常时，数据不回滚。
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if (person.getName().equals("黄俊东")) {
            throw new IllegalArgumentException("黄俊东虽已存 在，数据将不会回滚");
        }
        return p;
    }
}