package com.demo3.demo3.film;
import com.demo3.demo3.actor.Actor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description = " ";

    @Column(name = "release_year")
    private Long releaseYear;

    @Column(name = "language_id")
    private Long languageId = 1L;

    @Column(name="length")
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



    public Film() {
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
        this.description = description;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
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
        this.lengthMinutes = lengthMinutes;
    }

    public Ratings getRating() {
        return rating;
    }

    public void setRating(Ratings rating) {
        this.rating = rating;
    }
}
