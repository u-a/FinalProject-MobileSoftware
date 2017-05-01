package com.tusk.baton.finalproject;

import android.location.Location;

import java.util.Date;

import static com.tusk.baton.finalproject.Resources.CATEGORY_GAMEDAY;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Leg {

    private Location myLocation;
    private Date time;
    private String category;

    public Leg() {
        initialize(new Location(""), new Date(), CATEGORY_GAMEDAY);
    }

    public Leg(Location inLocation, Date inTime, String inCategory) {
        initialize(inLocation, inTime, inCategory);
    }

    private void initialize(Location inLocation, Date inTime, String inCategory) {
        myLocation = inLocation;
        time = inTime;
        category = inCategory;
    }
}
