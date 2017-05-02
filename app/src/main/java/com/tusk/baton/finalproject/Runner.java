package com.tusk.baton.finalproject;

import android.location.Location;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Runner {
    private String name;
    private String id;
    private int checkedStatus;
    private Location currentLocation;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(int checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
