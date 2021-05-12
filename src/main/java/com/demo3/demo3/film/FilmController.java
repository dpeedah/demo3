package com.demo3.demo3.film;

import com.demo3.demo3.actor.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping( path="api/films")
@Validated
public class FilmController {
    @Autowired
    private FilmRepository filmRepo;


    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> getFilms() {
        Iterable<Film> a = filmRepo.findAll();
        List films = (List) a;
        return ResponseEntity.ok(films);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Film> findFilmById(@PathVariable("id") @Min(0)Long film_id){
        Film film = filmRepo.findById(film_id).get();
        return new ResponseEntity<Film>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/actors_by_film/{id}")
    public Set<Actor> findActorsByFilm(
            @PathVariable("id") Long film_id){
        Set returnSet = null;
        Film filma = filmRepo.findById(film_id).orElse(null);
        if(filma != null){
            returnSet = filma.getAllActors();
        }
        return returnSet;
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteActor(@PathVariable("id") Long film_id){
        boolean exists = filmRepo.existsById(film_id);
        if (!exists){
            throw new IllegalStateException("Film with ID of " + film_id + "does not exist");
        }
        filmRepo.deleteById(film_id);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Film>  addFilm(@Valid @RequestBody Film film){
        Film film1 = filmRepo.findFilmByTitle(film.getTitle());
        if (film1 != null){
            throw new IllegalStateException("Full name already exists");
        }
        //Actor actor1 = new Actor(firstName,lastName);

        film = filmRepo.save(film);
        return new ResponseEntity<Film>(film,HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(path ="{id}",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )

    public ResponseEntity<Film> updateActor(@PathVariable("id") Long film_id,
                                      @RequestBody Map<String,String> json) {
        Film film = filmRepo.findById(film_id).orElseThrow(() -> new IllegalStateException("Film with Id" + film_id + "does not exist"));
        String title = null;
        if (json.containsKey("title")){
             title = json.get("title");
        }

        String description = null;
        if (json.containsKey("description")){
            description = json.get("description");
        }

        String strReleaseYear = null;
        Long releaseYear = null;
        if (json.containsKey("releaseYear")){
            strReleaseYear = json.get("releaseYear");
            releaseYear = Long.parseLong(strReleaseYear);
        }

        String rating = null;
        if (json.containsKey("rating")){
            rating = json.get("rating");
        }

        if (title != null && title.length() > 0) {
            film.setTitle(title);
        }

        if (description != null && description.length() > 0) {
            film.setDescription(description);
        }

        if (releaseYear != null) {
            film.setReleaseYear(releaseYear);
        }

        if (rating != null) {
            Ratings rate = Ratings.fromString("G");
            film.setRating(Ratings.fromString(rating));
        }
        final Film savedFilm = filmRepo.save(film);
        return ResponseEntity.ok(savedFilm);


    }



}
