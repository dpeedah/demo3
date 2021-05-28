package com.demo3.demo3.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( path="api/actors")
public class ActorController {

    @Autowired
    private ActorRepository actorRepo;

    @GetMapping(path="/all")
    public ResponseEntity<List<Actor>> getActors() {
        Iterable<Actor> a = actorRepo.findAll();
        List<Actor> actors = (List) a;
        return ResponseEntity.ok(actors);
    }

    @GetMapping(path="/byid/{id}")
    public ResponseEntity<Actor> findActorById(@PathVariable("id") Long actorId){
        Actor actor = actorRepo.findById(actorId).get();
        return new ResponseEntity<>(actor,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteActor(@PathVariable("id") Long actorId){
        boolean exists = actorRepo.existsById(actorId);
        if (!exists){
            throw new IllegalStateException("Actor with ID of " + actorId + "does not exist");
        }
        actorRepo.deleteById(actorId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Actor> addActor(@Valid @RequestBody Actor actor){
        Optional<Actor> actorByName = actorRepo.findActorByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());
        if (actorByName.isPresent()){
            throw new IllegalStateException("Full name already exists");
        }
        actorRepo.save(actor);
        return new ResponseEntity<>(actor,HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(path ="{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable("id") Long actorId,
                              @RequestParam(required = false) String name,
                              @RequestParam (required = false) String lName){
        Actor actor = actorRepo.findById(actorId).orElseThrow( () -> new IllegalStateException("Actor with Id" + actorId + "does not exist"));
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
