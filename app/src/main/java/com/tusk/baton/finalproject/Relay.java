package com.tusk.baton.finalproject;

import android.graphics.Bitmap;

import static com.tusk.baton.finalproject.Resources.PRIVACY_PUBLIC;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Relay {

    private String title;
    private Bitmap picture;
    private Leg[] legs;
    private Runner[] runners;
    private int privacy;

    public Relay() {
        initialize("", null, new Leg[] {}, new Runner[] {}, PRIVACY_PUBLIC);
    }

    public Relay(String inTitle, Bitmap inPicture, Leg[] inLegs, Runner[] inRunners, int inPrivacy) {
        initialize(inTitle, inPicture, inLegs, inRunners, inPrivacy);
    }

    private void initialize(String inTitle, Bitmap inPicture, Leg[] inLegs, Runner[] inRunners, int inPrivacy) {
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

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Leg[] getLegs() {
        return legs;
    }

    public void setLegs(Leg[] legs) {
        this.legs = legs;
    }

    public Runner[] getRunners() {
        return runners;
    }

    public void setRunners(Runner[] runners) {
        this.runners = runners;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }
}
