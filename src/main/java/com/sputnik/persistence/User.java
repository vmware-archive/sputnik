package com.sputnik.persistence;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    private String email;

    private boolean admin;

    public User(String email) {
        this.email = email;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public boolean getAdmin() {
        return admin;
    }
}
