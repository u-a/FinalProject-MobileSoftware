package com.tusk.baton.finalproject;

import android.location.Location;

import java.util.Date;

import static com.tusk.baton.finalproject.Resources.CATEGORY_GAMEDAY;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Leg {

    private Location myLocation;
    private String title;
    private String description;
    private Date time;
    private String category;

    public Leg() {
        initialize(new Location(""), new Date(), CATEGORY_GAMEDAY, "", "");
    }

    public Leg(Location inLocation, Date inTime, String inCategory, String inTitle, String inDescription) {
        initialize(inLocation, inTime, inCategory, inTitle, inDescription);
    }

    private void initialize(Location inLocation, Date inTime, String inCategory, String inTitle, String inDescription) {
        myLocation = inLocation;
        time = inTime;
        category = inCategory;
        title = inTitle;
        description = inDescription;
    }

    public Location getLocation() {
        return myLocation;
    }

    public void setLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MyLocation getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(MyLocation myLocation) {
        this.myLocation = myLocation;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
