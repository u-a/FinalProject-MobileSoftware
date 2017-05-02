package com.tusk.baton.finalproject;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.com.google.gson.JsonElement;
import com.amazonaws.com.google.gson.JsonObject;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.regions.Regions;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.key;

/**
 * Created by tomoestreich on 4/26/2017.
 */

public class RelayList extends AppCompatActivity {
//    private CardView eventCard;
//    private ImageView eventPic;
//    private TextView mTextMessage;

    private static final String[] titles = {"TOTS Tuesay", "Soccer on the Drillfield", "GM Meet and Greet", "Intel Luncheon", "SpaceX Interview Day"};
    private static final String[] pictureTitles = {"tots", "soccer", "gm", "intel", "spacex"};
    private static final String[] legTitles1 = {"Meet at Richards house for wine and cheese", "Sing at TOTS", "Get Bennys", "Count Calories"};
    private static final String[] legTitles2 = {"Meet at the Drillfield", "Enjoy some soccer with the bros", "Deets ice cream! :)"};
    private static final String[] legTitles3 = {"Info session", "Break for dinner with teams @6pm", "Dessert"};
    private static final String[] legTitles4 = {"Info session", "Lunch with employees", "Group Interviews"};
    private static final String[] legTitles5 = {"Info session", "Lunch with employees", "Individual Interviews"};
    private static final String[] runners = {"Richard Cheese", "Tom", "Sushant", "Karthik", "Ushan", "Elmo", "Barney", "Chuck Norris", "Bruce Lee", "Bob the Builder"
            , "Dave", "Pablo Esobar", "Lionel Messi", "Sam", "Seyam", "Bradley Cooper", "Henrik Lundqvist" };
    private static final int[] privacy = {Resources.PRIVACY_PUBLIC, Resources.PRIVACY_PRIVATE, Resources.PRIVACY_SPONSORED, Resources.PRIVACY_SPONSORED,Resources.PRIVACY_SPONSORED};
    private Dataset dataset;
    //remove static modifier??
    public static HashMap<String,    String[]> relayValuesStatic;

    private HashMap<String, String> returnedMap;
    private static RelayList instance = null;


    //private ArrayList<Relay> relayList;
    public HashMap<String, Relay> relayHashMap;

    public HashMap<String, String> pullFromDB(CognitoSyncManager syncClient){


//        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
//                getApplicationContext(),
//                "us-west-2:ac6c2702-ddb5-4331-b515-a7097beae30c", // Identity Pool ID
//                Regions.US_WEST_2 // Region
//        );
//
//        CognitoSyncManager syncClient = new CognitoSyncManager(
//                getApplicationContext(),
//                Regions.US_WEST_2, // Region
//                credentialsProvider);

        dataset = syncClient.openOrCreateDataset("Testing");
//        HashMap<String, String> testMap = new HashMap<>();
//        testMap.put("Yo","Yo");
//        testMap.put("New", "Push");
//        dataset.put("myKey", "hallo");
//        dataset.putAll(testMap);
         returnedMap = (HashMap<String, String>) dataset.getAll();
        dataset.synchronize(new DefaultSyncCallback() {
            @Override
            public void onSuccess(Dataset dataset, List newRecords) {
                //Your handler code here


            }
        });

        return returnedMap;
    }
    public void pushToDB(String relayName, String JSONString, CognitoSyncManager syncClient) throws JSONException {
        String jsonFormattedString = JSONString.replace("\\", "");

        dataset = syncClient.openOrCreateDataset("Testing");
//        HashMap<String, String> testMap = new HashMap<>();
//        testMap.put("Yo","Yo");
//        testMap.put("New", "Push");
//        dataset.put("myKey", "hallo");
//        dataset.putAll(convertedMap);
        dataset.put(relayName, jsonFormattedString);
        dataset.synchronize(new DefaultSyncCallback() {
            @Override
            public void onSuccess(Dataset dataset, List newRecords) {
                //Your handler code here


            }

            @Override
            public boolean onDatasetDeleted(Dataset dataset, String datasetName) {
                Log.d("Deleted", "onDatasetDeleted was called and DefaultSyncCallback doesn't provide an implementation for it");
                return false;
            }
        });


    }

    protected RelayList(){

        relayHashMap = new HashMap<>();
        relayValuesStatic = new HashMap<>();
        relayValuesStatic.put("Titles", titles);
        relayValuesStatic.put("PicturesTitles", pictureTitles);
        relayValuesStatic.put("LegTitles1", legTitles1);
        relayValuesStatic.put("LegTitles2", legTitles2);
        relayValuesStatic.put("LegTitles3", legTitles3);
        relayValuesStatic.put("LegTitles4", legTitles4);
        relayValuesStatic.put("LegTitles5", legTitles5);
        relayValuesStatic.put("Runners", runners);
        populateRelayList();
        returnedMap = new HashMap<>();
    }


    public HashMap<String, Relay> getHashMap(){
        return relayHashMap;
    }

    public static RelayList getInstance(){
        if(instance == null){
            instance = new RelayList();
        }
        return instance;
    }

    private int getIdFromImageName(String inString) {
//        int resID = getResources().getIdentifier(inString , "drawable", getPackageName());
//        return resID;
        switch (inString) {
            case "tots":
                return R.drawable.tots;
            case "soccer":
                return R.drawable.soccer;
            case "gm":
                return R.drawable.gm;
            case "intel":
                return R.drawable.intel;
            case "spacex":
                return R.drawable.spacex;
            default:
                return 0;
        }
    }

    private void populateRelayList() {
        for (int i = 0; i < titles.length; i++) {
            Relay r = new Relay();

            //populate relay fields
            r.setTitle((relayValuesStatic.get("Titles"))[i]);
            r.setPicture(getIdFromImageName((relayValuesStatic.get("PicturesTitles"))[i]));
            r.setPrivacy(privacy[i]);

            //add the appropriate legs
            String legArrName = "LegTitles"+(i+1);
            String[] legArr = relayValuesStatic.get(legArrName);
            for (int j = 0; j < legArr.length; j++) {
                Location loc = new Location("");
                loc.setLatitude(0);
                loc.setLongitude(0);
                Leg l = new Leg(loc, new Date(), "", legArr[j], "");
                r.addLeg(l);
            }

            //add 6 random runners
            for (int j = 0; j < 6; j++) {
                Location loc = new Location("");
                loc.setLatitude(0);
                loc.setLongitude(0);
                int index = (int) Math.floor(Math.random() * 17);
                Runner tempRunner = new Runner((relayValuesStatic.get("Runners"))[index],"",0,loc);
                r.addRunner(tempRunner);
            }
            relayHashMap.put((relayValuesStatic.get("Titles"))[i], r);
        }

    }

//    public void addRelay(String title, int picture, Leg[] legs, Runner[] runners, int privacy ){
//        Relay newRelay = new Relay(title,  picture, legs, runners, privacy );
//        relayHashMap.put(title, newRelay);
//    }

    public void addRelay(Relay inRelay) {
        relayHashMap.put(inRelay.getTitle(), inRelay);
    }

    public Relay getRelay(String title){
        if(relayHashMap.containsKey(title)) {
            System.out.println("title in the list class: " + title);

            return relayHashMap.get(title);
        }
        return null;
    }

    /*
    public View getCard(View child, String key, String extra){
        CardView eventCard;
        eventCard = (CardView) child.findViewById(R.id.card_view);
        mTextMessage = (TextView) child.findViewById(R.id.info_text);
        Relay tempRelay = relayHashMap.get(key);
        System.out.println("HASH MAP: " + tempRelay.getTitle());
        mTextMessage.setText(key + extra);
        eventPic = (ImageView) eventCard.findViewById(R.id.eventImage);
        eventPic.setImageResource(tempRelay.getPicture());
        return child;
    }
    */
}
