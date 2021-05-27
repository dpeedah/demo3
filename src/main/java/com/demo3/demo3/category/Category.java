package com.demo3.demo3.category;

import com.demo3.demo3.film.Film;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToMany(mappedBy = "allCategories")
    Set<Film> films;

    public Category() {
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public Category(String name) {
        this.name = name;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    @NotBlank(message = "Name required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.isEmpty()){
            throw new IllegalArgumentException("name is null");
        }else if(name.matches("[a-zA-Z]+")){
            this.name = name;
        }else{
            throw new IllegalArgumentException("name is not a word");
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
