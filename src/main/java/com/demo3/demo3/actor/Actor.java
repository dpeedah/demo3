package com.demo3.demo3.actor;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity
@Table (name = "actor")
public class Actor {

    @Id
    @Column(name="actor_id")
    @GeneratedValue
    private Long id;


    private String firstName;
    private String lastName;

    public Actor() {
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
