package com.example.demo;

import com.example.demo.domain.Person;
import com.example.demo.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@RestController
public class DataController {
    //1 Spring Data JPA已自动为你注册bean，所以可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * save支持批量保存：<S extends T> Iterable save(Iterable entities);
     *
     * 删除：
     * 支持使用id删除对象、批量删除以及删除全部：
     * void delete(ID id);
     * void delete(T entity);
     * void delete(Iterable<? extends T> entities);
     * void deleteAll();
     */
    @GetMapping("/all")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    //如果RequestMapping中表示为"/viewItems/{id}"，id和形参名称一致，@PathVariable不用指定名称。
    @RequestMapping(value = "/del/{id}")
    public void delete(@PathVariable(value = "id") Long idd) {
        personRepository.deleteById(idd);
    }

    /**
     * 测试findByAddress
     */
    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }

    /**
     * 测试findByNameAndAddress
     */
    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person people = personRepository.withNameAndAddress(name, address);
        return people;
    }

    /**
     * 测试withNameAndAddressQuery
     */
    @RequestMapping("/q3")
    public List<Person> q3(String name, String address) {
        List<Person> p = personRepository.withNameAndAddressQuery(name, address);
        return p;
    }

    /**
     * 测试withNameAndAddressNamedQuery
     */
    @RequestMapping("/q4")
    public List<Person> q4(String name, String address) {
        List<Person> p = personRepository.withNameAndAddressNamedQuery(name, address);
        return p;
    }

    /**
     * 修改信息
     */
    @GetMapping("/up1")
    public Integer up1(String name, Long id) {
        return personRepository.updataName(name, id);
    }

    //查询id进行修改
    @PostMapping("up2")
    public Person up2(Person person) {
        Person person1 = personRepository.findById(person.getId()).get();
        System.out.println(person.getName());
        return personRepository.save(person1);
    }

    //查询到新增页面时，id禁止修改，修改其它内容后直接存储
    @PostMapping("up3")
    public Person up3(Person person){
        return personRepository.save(person);
    }


    /**
     * 测试排序
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> people = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return people;
    }

    /**
     * 测试分页
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeople = personRepository.findAll(new PageRequest(0, 2));
        return pagePeople;
    }
}