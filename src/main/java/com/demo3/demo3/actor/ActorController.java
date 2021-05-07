package com.demo3.demo3.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    public Optional<Actor> findActorById(@PathVariable("id") Long actor_id){
        return actorRepo.findById(actor_id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteActor(@PathVariable("id") Long actor_id){
        boolean exists = actorRepo.existsById(actor_id);
        if (!exists){
            throw new IllegalStateException("Actor with ID of " + actor_id + "does not exist");
        }
        actorRepo.deleteById(actor_id);
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void addActor(@RequestBody Actor actor){
        Optional<Actor> actorByName = actorRepo.findActorByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());
        if (actorByName.isPresent()){
            throw new IllegalStateException("Full name already exists");
        }
        //Actor actor1 = new Actor(firstName,lastName);

        actorRepo.save(actor);
    }

    @Transactional
    @PutMapping(path ="{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Long actor_id,
                              @RequestParam(required = false) String name,
                              @RequestParam (required = false) String lName){
        Actor actor = actorRepo.findById(actor_id).orElseThrow( () -> new IllegalStateException("Actor with Id" + actor_id + "does not exist"));
        if  (name != null && name.length() > 0){
            actor.setFirstName(name);
        }

        if (lName != null && lName.length() > 0) {
            actor.setLastName(lName);
        }

        final Actor savedActor = actorRepo.save(actor);
        return ResponseEntity.ok(savedActor);
    }
}
