package com.demo3.demo3.film;
import com.demo3.demo3.actor.Actor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title required")
    @Column(name="title")
    private String title;

    @NotBlank(message = "description required")
    @Column(name="description")
    private String description = " ";

    @Column(name = "release_year")
    @Max(3000)
    @Min(0)
    private Long releaseYear;

    @Column(name = "language_id")
    private Long languageId = 1L;

    @Column(name="length")
    @Max(3600)
    @Min(1)
    private Long lengthMinutes;

    @Column(name = "rating")
    private Ratings rating;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    Set<Actor> allActors ;

    public Film(String title, String description, Long releaseYear, Long lengthMinutes) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.lengthMinutes = lengthMinutes;
        this.rating = Ratings.G;
    }

    public Film(String title, String description, Long releaseYear, Long languageId, Long lengthMinutes, Ratings rating) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.lengthMinutes = lengthMinutes;
        this.rating = rating;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Film() {
    }


    public Long getId() {
        return id;
    }

    public Set<Actor> getAllActors() {
        return allActors;
    }

    public void setAllActors(Set<Actor> allActors) {
        this.allActors = allActors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 3){
            this.description = description;
        }

    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        if (releaseYear <= 3000L && releaseYear>=1800){
            this.releaseYear = releaseYear;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(Long lengthMinutes) {
        if (lengthMinutes >= 1 && lengthMinutes<=360){
            this.lengthMinutes = lengthMinutes;
        }else{
            throw new IllegalArgumentException();
        }
        this.lengthMinutes = lengthMinutes;
    }

    public Ratings getRating() {
        return rating;
    }

    public void setRating(Ratings rating) {
        this.rating = rating;
    }
}
