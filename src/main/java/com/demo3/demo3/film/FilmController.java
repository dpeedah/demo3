package com.demo3.demo3.film;

import com.demo3.demo3.actor.Actor;
import com.demo3.demo3.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping( path="api/films")
public class FilmController {
    @Autowired
    private FilmRepository filmRepo;

    @GetMapping(path="/all")
    public Iterable<Film> getFilm() {
        Iterable<Film> a = filmRepo.findAll();
        return filmRepo.findAll();
    }

    @GetMapping(path="/byid/{id}")
    public Optional<Film> findFilmById(@PathVariable("id") Long film_id){
        return filmRepo.findById(film_id);
    }

    @DeleteMapping(path="/{id}")
    public void deleteActor(@PathVariable("id") Long film_id){
        boolean exists = filmRepo.existsById(film_id);
        if (!exists){
            throw new IllegalStateException("Film with ID of " + film_id + "does not exist");
        }
        filmRepo.deleteById(film_id);
    }

    @PostMapping(path = "/create")
    public @ResponseBody void addFilm(@RequestBody Film film){
        Optional<Film> film1 = filmRepo.findFilmByTitle(film.getTitle());
        if (film1.isPresent()){
            throw new IllegalStateException("Full name already exists");
        }
        //Actor actor1 = new Actor(firstName,lastName);

        filmRepo.save(film);
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
