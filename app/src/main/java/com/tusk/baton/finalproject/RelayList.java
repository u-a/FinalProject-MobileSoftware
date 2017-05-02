package com.tusk.baton.finalproject;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    private static final String[] legTitles3 = {"Info session @4pm", "Break for dinner with teams @6pm", "Dessert @ 7:30pm"};
    private static final String[] legTitles4 = {"Info session @ 12pm", "Lunch with employees @1:30", "Group Interviews @ 3:30"};
    private static final String[] legTitles5 = {"Info session @ 12pm", "Lunch with employees @1:30", "Individual Interviews @ 3:30"};
    private static final String[] runners = {"Richard Cheese", "Tom", "Sushant", "Karthik", "Ushan", "Elmo", "Barney", "Chuck Norris", "Bruce Lee", "Bob the Builder"
            , "Dave", "Pablo Esobar", "Lionel Messi", "Sam", "Seyam", "Bradley Cooper", "Henrik Lundqvist" };
    private static final int[] privacy = {Resources.PRIVACY_PUBLIC, Resources.PRIVACY_PRIVATE, Resources.PRIVACY_SPONSORED, Resources.PRIVACY_SPONSORED,Resources.PRIVACY_SPONSORED};

    //remove static modifier??
    public static HashMap<String,    String[]> relayValuesStatic;

    private static RelayList instance = null;


    //private ArrayList<Relay> relayList;
    private HashMap<String, Relay> relayHashMap;

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
                Leg l = new Leg(null, new Date(), "", legArr[j], "");
                r.addLeg(l);
            }

            //add 6 random runners
            for (int j = 0; j < 6; j++) {
                int index = (int) Math.floor(Math.random() * 17);
                Runner tempRunner = new Runner((relayValuesStatic.get("Runners"))[index],"",0,null);
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
