package com.tusk.baton.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tomoestreich on 4/26/2017.
 */

public class EventList extends AppCompatActivity {
    private CardView eventCard;
    private ImageView eventPic;
    private TextView mTextMessage;
    private ArrayList eventList = new ArrayList();

    public View addCard(View child){
        CardView eventCard;
        eventCard = (CardView) child.findViewById(R.id.card_view);
        mTextMessage = (TextView) eventCard.findViewById(R.id.info_text);
        eventPic = (ImageView) eventCard.findViewById(R.id.eventImage);
        return child;
    }


}
