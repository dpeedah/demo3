package com.demo3.demo3.actor;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
public interface ActorRepository extends CrudRepository<Actor,Long> {

    Optional<Actor> findActorByFirstNameAndLastName(String fname, String lname);
    Optional<Iterable<Actor>>findActorsByFirstName(String fname);
    Optional<Iterable<Actor>>findActorsByLastName(String lname);
}
