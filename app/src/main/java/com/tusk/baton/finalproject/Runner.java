package com.tusk.baton.finalproject;

import android.location.Location;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Runner {
    private static final String TAG = "Runner~~";
    private String name;
    private String id;
    private int checkedStatus;
    private Location currentLocation;

    public Runner() {
        Location l = new Location("");
        l.setLatitude(0);
        l.setLongitude(0);
        initialize("", "", 0, l);
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

    public String getJSONString() {
        JSONObject jObj = new JSONObject();
        try {
            jObj.put("name", name);
            jObj.put("id", id);
            jObj.put("checkedstatus", checkedStatus);
            jObj.put("latitude", currentLocation.getLatitude());
            jObj.put("longitude", currentLocation.getLongitude());
        } catch (JSONException e) {
            Log.d(TAG, "getJSONString: parsing failed");
            e.printStackTrace();
            return null;
        }
        return jObj.toString();
    }

    public void setFromJSON(JSONObject jObj) {
        try {
            name = jObj.getString("name");
            id = jObj.getString("id");
            checkedStatus = jObj.getInt("checkedstatus");
            int locLat = jObj.getInt("latitude");
            int locLong = jObj.getInt("longitude");
            currentLocation.setLatitude(locLat);
            currentLocation.setLongitude(locLong);
        } catch (JSONException e) {
            Log.d(TAG, "setFromJSON: parsing failed");
            e.printStackTrace();
        }
    }

}
