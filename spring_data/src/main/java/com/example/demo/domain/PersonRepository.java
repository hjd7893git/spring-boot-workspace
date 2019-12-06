package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "hjd") //REST定制节点路径
public interface PersonRepository extends JpaRepository<Person, Long> {

    //1使用方法名查询，接受一个name参数，返回值为列表。
    List<Person> findByAddress(String address);

    //2使用方法名查询，接受name和address，返回值为单个对象。
    @Query("select p from Person p where p.name= ?1 and p.address= ?2")
    Person withNameAndAddress(String name, String address);

    //    @RestResource(path = "123",rel = "456") //第一个path为访问的后缀名
    //3使用@Query查询，参数按照名称绑定。
    @Query("select p from Person p where p.name= :name and p.address like %:address%")
    List<Person> withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    //4使用@NamedQuery查询，请注意我们在实体类中做的@NamedQuery的定义。
    List<Person> withNameAndAddressNamedQuery(@Param("n") String name, @Param("ad") String address);

    //5更新，在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
    /**
     * 　　可以用@Query注解来将自定义sql语句绑定到自定义方法上。
     * 　　可以用@Modifying注解来标注只需要绑定参数的自定义的更新类语句（更新、插入、删除）。
     * 　　@Modifying只与@Query联合使用，派生类的查询方法和自定义的方法不需要此注解，如：
     */
    @Modifying
    @Query("update Person p set p.name=:name where id=:id")
    Integer updataName(@Param("name") String name, @Param("id") Long id);

}