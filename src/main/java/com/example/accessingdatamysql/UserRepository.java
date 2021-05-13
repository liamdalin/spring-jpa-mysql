package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
// details refer to https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("select u from User u where u.firstName like :firstnamePrefix%")
    List<User> findByFirstnamePrefix(@Param("firstnamePrefix") String firstnamePrefix);
}
