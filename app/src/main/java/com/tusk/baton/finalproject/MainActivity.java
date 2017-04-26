package com.tusk.baton.finalproject;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private TextView mTextMessage;
    private TextView TextMessage;
    private CardView eventCard;
    private ScrollView scrollView;
    private ViewGroup cardStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        eventCard = (CardView) findViewById(R.id.card_view);
//        mTextMessage = (TextView) eventCard.findViewById(R.id.info_text);
//        cardStack = (ViewGroup) findViewById(R.id.cardStack);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        cardStack = (LinearLayout) findViewById(R.id.cardStack);

        for(int i = 0; i < 6; i++){
            System.out.println(i);
            View child = getLayoutInflater().inflate(R.layout.event_layout, null);
            CardView eventCard;
            eventCard = (CardView) child.findViewById(R.id.card_view);
            mTextMessage = (TextView) eventCard.findViewById(R.id.info_text);
            mTextMessage.setText("Card Number: " + i);
            cardStack.addView(child);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("navigation");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

}
