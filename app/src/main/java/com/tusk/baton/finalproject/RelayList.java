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

    //maybe turn into a hashmahp later
    //private ArrayList<Relay> relayList;
    private HashMap<String, Relay> relayHashMap;

    public RelayList(){
        relayHashMap = new HashMap<>();
    }

    public void addRelay(String title, Bitmap picture, Leg[] legs, Runner[] runners, int privacy ){
        Relay newRelay = new Relay(title,  picture, legs, runners, privacy );
        relayHashMap.put(title, newRelay);
    }

    public void addRelay(Relay inRelay) {
        relayHashMap.put(inRelay.getTitle(), inRelay);
    }

    public Relay getRelay(String title){
        return relayHashMap.get(title);
    }

    public View getCard(View child, String key){
        CardView eventCard;
        eventCard = (CardView) child.findViewById(R.id.card_view);
        mTextMessage = (TextView) eventCard.findViewById(R.id.info_text);
        Relay tempRelay = relayHashMap.get(key);
        mTextMessage.setText(tempRelay.getTitle());
        eventPic = (ImageView) eventCard.findViewById(R.id.eventImage);
        eventPic.setImageBitmap(tempRelay.getPicture());
        return child;
    }
}
