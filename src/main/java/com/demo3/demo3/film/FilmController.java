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

@CrossOrigin
@RestController
@RequestMapping( path="api/films")
@Validated
public class FilmController {


    @Autowired
    private FilmRepository filmRepo;


    @GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Film>> getFilms() {
        Iterable<Film> a = filmRepo.findAll();
        List<Film> films = (List) a;
        return ResponseEntity.ok(films);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Film> findFilmById(@PathVariable("id") @Min(0)Long filmId){
        Film film = filmRepo.findById(filmId).get();
        return new ResponseEntity<>(film,HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/exists/title/{title}")
    public ResponseEntity<Boolean> findFilmUniqueByTitle(@PathVariable("title") String title){
        Film film = filmRepo.findFilmByTitle(title);
        if (film.getId()!= null && film.getTitle().equals(title)){
            return new ResponseEntity<>(false,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
    }

    @GetMapping(path="/exists/release/{year}")
    public ResponseEntity<List<Film>> getFilmsByYear(@PathVariable("year") Long year){
        if ( year <= 1 || year>= 2021){
            throw new IllegalStateException("Year invalid, no books");
        }
        Iterable<Film> filmI = filmRepo.findFilmsByReleaseYear(year);
        List<Film> films = (List) filmI;
        return new ResponseEntity<>(films,HttpStatus.ACCEPTED);
    }


    @GetMapping(path="/actors_by_film/{id}")
    public Set<Actor> findActorsByFilm(
            @PathVariable("id") Long filmId){
        Set<Actor> returnSet = null;
        Film filmA = filmRepo.findById(filmId).orElse(null);
        if(filmA != null){
            returnSet = filmA.getAllActors();
        }
        return returnSet;
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<HttpStatus> deleteFilm(@PathVariable("id") Long filmId){
        boolean exists = filmRepo.existsById(filmId);
        if (!exists){
            throw new IllegalStateException("Film with ID of " + filmId + "does not exist");
        }
        filmRepo.deleteById(filmId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Film>  addFilm(@Valid @RequestBody Film film){
        film = filmRepo.save(film);
        return new ResponseEntity<>(film,HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(path ="/update/{id}",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )

    public ResponseEntity<Film> updateActor(@PathVariable("id") Long filmId,
                                      @RequestBody Map<String,String> json) {
        Film film = filmRepo.findById(filmId).orElseThrow(() -> new IllegalStateException("Film with Id" + filmId + "does not exist"));
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
        if (json.containsKey("rating"))
        {
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
        return new ResponseEntity<Film>(savedFilm,HttpStatus.OK);

    }



}
