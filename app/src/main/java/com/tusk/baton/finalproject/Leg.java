package com.tusk.baton.finalproject;

import android.location.Location;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static com.tusk.baton.finalproject.Resources.CATEGORY_GAMEDAY;

/**
 * Created by Ushan on 4/26/2017.
 */

public class Leg {

    private static final String TAG = "Leg~~";
    private Location myLocation;
    private String title;
    private String description;
    private Date time;
    private String category;

    public Leg() {
        Location l = new Location("");
        l.setLatitude(0);
        l.setLongitude(0);
        initialize(l, new Date(), CATEGORY_GAMEDAY, "", "");
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

    public String getJSONString() {
        JSONObject jObj = new JSONObject();
        try {
            jObj.put("title", title);
            jObj.put("description", description);
            jObj.put("category", category);
            jObj.put("latitude", myLocation.getLatitude());
            jObj.put("longitude", myLocation.getLongitude());
            jObj.put("date", time.getTime());
        } catch (JSONException e) {
            Log.d(TAG, "getJSONString: parsing failed");
            e.printStackTrace();
            return null;
        }
        return jObj.toString();
    }

    public void setFromJSON(JSONObject jObj) {
        try {
            title = jObj.getString("title");
            description = jObj.getString("title");
            category = jObj.getString("category");
            int locLat = jObj.getInt("latitude");
            int locLong = jObj.getInt("longitude");
            myLocation.setLatitude(locLat);
            myLocation.setLongitude(locLong);
            time.setTime(jObj.getInt("date"));
        } catch (JSONException e) {
            Log.d(TAG, "setFromJSON: parsing failed");
            e.printStackTrace();
        }
    }
}
