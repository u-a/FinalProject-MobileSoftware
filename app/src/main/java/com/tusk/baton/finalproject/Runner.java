package com.tusk.baton.finalproject;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Runner {
    private String name;
    private String id;

    int checkedStatus;
    MyLocation currentLocation;

    public Runner() {
        initialize("", "", 0, new MyLocation());
    }

    public Runner(String inName, String inID, int inStatus, MyLocation inLocation) {
        initialize(inName, inID, inStatus, inLocation);
    }

    private void initialize(String inName, String inID, int inStatus, MyLocation inLocation) {
        this.name = inName;
        this.id = inID;
        this.checkedStatus = inStatus;
        this.currentLocation = inLocation;
    }
}
