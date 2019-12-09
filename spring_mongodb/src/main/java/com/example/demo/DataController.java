package com.example.demo;

import com.example.demo.daomain.PersonRepository;
import com.example.demo.domain.Location;
import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save") //1
    public Person save() {
        Person p = new Person("wyf", 32);
        Collection<Location> locations = new LinkedHashSet<Location>();
        Location loc1 = new Location("上海", "2009");
        Location loc2 = new Location("合肥", "2010");
        Location loc3 = new Location("广州", "2011");
        Location loc4 = new Location("马鞍山", "2012");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        p.setLocations(locations);
        return personRepository.save(p);
    }

    @RequestMapping("/q1") //2
    public Person q1(String name) {
        return personRepository.findByName(name);
    }

    @RequestMapping("/q2") //3
    public List<Person> q2(Integer age) {
        return personRepository.withQueryFindByAge(age);
    }

    //根据id进行查询
    @GetMapping("findById/{id}")
    public Person findById(@PathVariable("id") String id){
        return personRepository.findById(id).orElse(null);
    }

    //查询所有
    @GetMapping("findAll")
    public List<Person> findall(){
        return personRepository.findAll();
    }

    //更新
    @PostMapping("updata")
    public Person updata(Person person){
        return personRepository.save(person);
    }

    //删除
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") String id){
        personRepository.deleteById(id);
        return personRepository.findById(id).orElse(null) == null ? "删除成功！" : "删除失败";
    }



}