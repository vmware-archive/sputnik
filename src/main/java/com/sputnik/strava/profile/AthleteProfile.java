package com.sputnik.strava.profile;

public class AthleteProfile {
    private String email;
    private String name;

    public AthleteProfile(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
