package com.example.demo.transactionalService;

import com.example.demo.domain.Person;

public interface DemoService {
    public Person savePersonWithRollBack(Person person);

    public Person savePersonWithoutRollBack(Person person);
}