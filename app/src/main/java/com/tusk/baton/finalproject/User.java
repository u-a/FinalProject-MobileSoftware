package com.tusk.baton.finalproject;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.List;
import java.util.Locale;

/**
 * Created by swoos on 4/26/2017.
 */

public class User {

    //check for null for all gets and sets
    private static User instance = null;

    private String name;
    private String username;

    private String phoneNumber;
    private Bitmap profilePicture;

    private Runner runner;
    private MyLocation myLocation;

    protected User(){
        name = "";
        username = "";
        phoneNumber = "";
    }

    protected User(String name, String username, String phoneNumber) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public static User getInstance(){
        if (instance == null){
            instance = new User();
        }
        return instance;
    }

//    public User (String name, String username, String phoneNumber, MyLocation myLocation){
//        this.name = name;
//        this.username = username;
//        this.phoneNumber = phoneNumber;
//        this.myLocation = myLocation;
//    }

    //need to figure out location
    public void initializeUserRunner(){
        if (myLocation != null ) {
            runner = new Runner(name, phoneNumber, Resources.NOT_CHECKED_IN, myLocation);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

}
