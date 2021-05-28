package com.demo3.demo3.actor;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface ActorRepository extends CrudRepository<Actor,Long> {
    Optional<Actor> findActorByFirstNameAndLastName(String name, String lName);
    Optional<Iterable<Actor>>findActorsByFirstName(String fName);
    Optional<Iterable<Actor>>findActorsByLastName(String lName);
}
