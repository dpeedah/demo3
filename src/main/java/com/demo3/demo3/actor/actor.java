package com.demo3.demo3.actor;

import java.util.Date;

public class actor {
    private Long id;
    private String firstName;
    private String lastName;
    private java.util.Date timeStamp;

    public actor() {
    }

    public actor(Long id, String firstName, String lastName, Date timeStamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeStamp = timeStamp;
    }

    public actor(String firstName, String lastName, Date timeStamp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "actor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
