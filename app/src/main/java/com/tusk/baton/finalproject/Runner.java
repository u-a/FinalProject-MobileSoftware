package com.tusk.baton.finalproject;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Runner {
    private String name;
    private int id;
    int checkedStatus;
    MyLocation currentLocation;

    public Runner() {
        initialize("", 0, 0, new MyLocation());
    }

    public Runner(String inName, int inID, int inStatus, MyLocation inLocation) {
        initialize(inName, inID, inStatus, inLocation);
    }

    private void initialize(String inName, int inID, int inStatus, MyLocation inLocation) {
        name = inName;
        id = inID;
        checkedStatus = inStatus;
        currentLocation = inLocation;
    }
}
