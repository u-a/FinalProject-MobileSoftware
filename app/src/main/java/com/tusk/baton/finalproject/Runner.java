package com.tusk.baton.finalproject;

import android.location.Location;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Runner {
    private String name;
    private String id;

    int checkedStatus;
    Location currentLocation;

    public Runner() {
        initialize("", "", 0, new Location(""));
    }

    public Runner(String inName, String inID, int inStatus, Location inLocation) {
        initialize(inName, inID, inStatus, inLocation);
    }

    private void initialize(String inName, String inID, int inStatus, Location inLocation) {
        this.name = inName;
        this.id = inID;
        this.checkedStatus = inStatus;
        this.currentLocation = inLocation;
    }
}
