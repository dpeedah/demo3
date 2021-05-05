package com.demo3.demo3.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( path="api/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepo;

    @GetMapping(path="/all")
    public Iterable<Actor> getActors() {
        return actorRepo.findAll();
    }

    @GetMapping(path="/byid/{id}")
    public Optional<Actor> findById(@PathVariable("id") Long actor_id){
        return actorRepo.findById(actor_id);
    }
}
