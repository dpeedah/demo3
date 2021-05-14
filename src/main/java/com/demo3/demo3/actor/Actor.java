package com.demo3.demo3.actor;

import com.demo3.demo3.film.Film;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "actor")
public class Actor {

    @Id
    @Column(name="actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last name required")
    @Column(name = "last_name")
    private String lastName;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @ManyToMany(mappedBy = "allActors")
    Set<Film> actedIn;

    public Actor() {
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public Date getLastUpdate(){return lastUpdate;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("firstName is null");
        }else if(firstName.matches("[a-zA-Z]+")){
            this.firstName = firstName;
        }else{
            throw new IllegalArgumentException("firstName is not a word");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("lastName is null");
        }else if(lastName.matches("[a-zA-Z]+")){
            this.lastName = lastName;
        }else{
            throw new IllegalArgumentException("lastName is not a word");
        }
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }


}
