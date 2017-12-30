package com.stefan.training.spring.data.lab1;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


/**
 * You don’t have to write an implementation of the repository interface.
 * Spring Data JPA creates an implementation on the fly when you run the application.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    // we can declare any other method here just per our need
    List<Customer> findByLastName(String lastName);
}
