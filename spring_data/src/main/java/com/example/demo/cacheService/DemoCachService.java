package com.example.demo.cacheService;

import com.example.demo.domain.Person;

public interface DemoCachService {
    public Person save(Person person);

    public void remove(Long id);

    public Person findOne(Person person);
}