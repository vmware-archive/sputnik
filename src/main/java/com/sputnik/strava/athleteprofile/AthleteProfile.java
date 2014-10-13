package com.sputnik.strava.athleteprofile;

public class AthleteProfile {
    private String email;
    private String name;
    private String avatarMedium;
    private String avatarLarge;

    public AthleteProfile(String email, String name, String avatarMedium, String avatarLarge) {
        this.email = email;
        this.name = name;
        this.avatarMedium = avatarMedium;
        this.avatarLarge = avatarLarge;
    }

    public String getAvatarMedium() {
        return avatarMedium;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
