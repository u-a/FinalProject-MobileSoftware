package com.tusk.baton.finalproject;

import android.graphics.Bitmap;

import static com.tusk.baton.finalproject.Resources.PRIVACY_PUBLIC;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Relay {

    private String title;
    private int picture;
    private String[] legs;
    private String[] runners;
    private int privacy;

    public Relay() {
        initialize("", 0, new String[]{}, new String[] {}, PRIVACY_PUBLIC);
    }

    public Relay(String inTitle, int inPicture, String[] inLegs, String[] inRunners, int inPrivacy) {
        initialize(inTitle, inPicture, inLegs, inRunners, inPrivacy);
    }

    private void initialize(String inTitle, int inPicture, String [] inLegs, String[] inRunners, int inPrivacy) {
        title = inTitle;
        picture = inPicture;
        legs = inLegs;
        runners = inRunners;
        privacy = inPrivacy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String[] getLegs() {
        return legs;
    }

    public void setLegs(String[] legs) {
        this.legs = legs;
    }

    public String[] getRunners() {
        return runners;
    }

    public void setRunners(String[] runners) {
        this.runners = runners;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }
}
