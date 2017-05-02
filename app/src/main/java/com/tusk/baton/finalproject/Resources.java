package com.tusk.baton.finalproject;

import android.location.Location;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ushan on 4/26/2017.
 */

public final class Resources {
    public static final int PRIVACY_PUBLIC = 0;
    public static final int PRIVACY_PRIVATE = 1;
    public static final int PRIVACY_SECRET = 2;
    public static final int PRIVACY_SPONSORED = 3;

    public static final int CHECKED_IN = 1;
    public static final int NOT_CHECKED_IN = 0;


    public static final String CATEGORY_GAMEDAY = "Gameday";
    public static final String CATEGORY_NIGHT_OUT = "Night Out";
    public static final String CATEGORY_BAR_CRAWL = "Bar Crawl";
    private static final String TAG = "Resources~~";

    public static Location getLocationFromString(String inString) {
        inString = inString.toLowerCase().trim();
        Location loc;
        switch (inString) {
            case "tots":
                loc = new Location("");
                loc.setLatitude(37.2245324352);
                loc.setLongitude(-80.4166983332);
                break;
            case "ushan's house":
                loc = new Location("");
                loc.setLatitude(37.213743);
                loc.setLongitude(-80.446301);
                break;
            case "lane":
                loc = new Location("");
                loc.setLatitude(37.218665792);
                loc.setLongitude(-80.41749833);
                break;
            case "mcbryde":
                loc = new Location("");
                loc.setLatitude(37.23159);
                loc.setLongitude(-80.42195);
                break;
            default:
                loc = null;
                break;
        }

        if (loc == null) {
            Log.d(TAG, "getLocationFromString: string didn't match, output loc is null");
            return null;
        }
        else return loc;
    }

}
