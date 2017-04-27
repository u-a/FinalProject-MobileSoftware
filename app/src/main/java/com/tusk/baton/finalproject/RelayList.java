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
import java.util.HashMap;

/**
 * Created by tomoestreich on 4/26/2017.
 */

public class RelayList extends AppCompatActivity {
    private CardView eventCard;
    private ImageView eventPic;
    private TextView mTextMessage;

    private static RelayList instance = null;


    //maybe turn into a hashmahp later
    //private ArrayList<Relay> relayList;
    private HashMap<String, Relay> relayHashMap;

    protected RelayList(){

        relayHashMap = new HashMap<>();
    }

    public HashMap<String, Relay> getHash(){
        return relayHashMap;
    }

    public static RelayList getInstance(){
        if(instance == null){
            instance = new RelayList();
        }
        return instance;
    }

    public void addRelay(String title, int picture, String[] legs, String[] runners, int privacy ){
        Relay newRelay = new Relay(title,  picture, legs, runners, privacy );
        relayHashMap.put(title, newRelay);
    }

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
}
