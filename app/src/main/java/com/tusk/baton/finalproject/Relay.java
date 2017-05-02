package com.tusk.baton.finalproject;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.tusk.baton.finalproject.Resources.PRIVACY_PUBLIC;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Relay {

    private static final String TAG = "Relay~~";
    private String title;
    private int picture;
    private ArrayList<Leg> legs;
    private ArrayList<Runner> runners;
    private int privacy;

    public Relay() {
        initialize("", 0, new ArrayList<Leg>(), new ArrayList<Runner>(), PRIVACY_PUBLIC);
    }

    public Relay(String inTitle, int inPicture, ArrayList<Leg> inLegs, ArrayList<Runner> inRunners, int inPrivacy) {
        initialize(inTitle, inPicture, inLegs, inRunners, inPrivacy);
    }

    private void initialize(String inTitle, int inPicture, ArrayList<Leg> inLegs, ArrayList<Runner> inRunners, int inPrivacy) {
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

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Leg> legs) {
        this.legs = legs;
    }

    public void addLeg(Leg inLeg) {
        legs.add(inLeg);
    }

    public ArrayList<Runner> getRunners() {
        return runners;
    }

    public void setRunners(ArrayList<Runner> runners) {
        this.runners = runners;
    }

    public void addRunner(Runner inRunner ){
        runners.add(inRunner);
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getJSONString() {

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("title", title);
            jObj.put("picture", picture);
            jObj.put("privacy", privacy);

            ArrayList<String> legStringList = new ArrayList<>();
            for (Leg iLeg: legs) {
                legStringList.add(iLeg.getJSONString());
            }

            JSONArray jarr = new JSONArray(legStringList);
//            for (int i = 0; i < legStringList.size(); i++) {
//                jarr.put(i,legStringList.get(i));
//            }
//            Log.d(TAG, "getJSONString: jarr="+jarr.toString());
            jObj.put("legs", jarr);

            ArrayList<String> runnerStringList = new ArrayList<>();
            for (Runner iRunner: runners) {
                runnerStringList.add(iRunner.getJSONString());
            }
            jObj.put("runners", new JSONArray(runnerStringList));


        } catch (JSONException e) {
            Log.d(TAG, "getJSONString: parsing failed");
            e.printStackTrace();
            return null;
        }
        return jObj.toString();
    }

//    private JSONArray

    public void setFromJSON(JSONObject jObj) {
        try {
            Log.d(TAG, "setFromJSON: jobj="+jObj.toString());
            title = jObj.getString("title");
            picture = jObj.getInt("picture");
            privacy = jObj.getInt("privacy");

//            JSONArray legArray = jObj.getJSONArray("legs");
////            JSONArray legArray2 = new JSONArray("[{\\\"title\\\":\\\"Meet at Richards house for wine and cheese\\\",\\\"description\\\":\\\"\\\",\\\"category\\\":\\\"\\\",\\\"latitude\\\":0,\\\"longitude\\\":0,\\\"date\\\":1493712378748},{\\\"title\\\":\\\"Sing at TOTS\\\",\\\"description\\\":\\\"\\\",\\\"category\\\":\\\"\\\",\\\"latitude\\\":0,\\\"longitude\\\":0,\\\"date\\\":1493712378748},{\\\"title\\\":\\\"Get Bennys\\\",\\\"description\\\":\\\"\\\",\\\"category\\\":\\\"\\\",\\\"latitude\\\":0,\\\"longitude\\\":0,\\\"date\\\":1493712378748},{\\\"title\\\":\\\"Count Calories\\\",\\\"description\\\":\\\"\\\",\\\"category\\\":\\\"\\\",\\\"latitude\\\":0,\\\"longitude\\\":0,\\\"date\\\":1493712378748}]");
//            Log.d(TAG, "setFromJSON: legarray="+legArray.toString());
//            for (int i = 0; i < legArray.length(); i++) {
//                Leg l = new Leg();
//                Log.d(TAG, "setFromJSON: legArray[i]="+legArray.getJSONObject(i));
//                l.setFromJSON(legArray.getJSONObject(i));
//            }

            JSONArray runnerArray = jObj.getJSONArray("runners");
            for (int i = 0; i < runnerArray.length(); i++) {
                Runner r = new Runner();
                r.setFromJSON(runnerArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            Log.d(TAG, "setFromJSONString: parsing failed");
            e.printStackTrace();
        }
    }

}
