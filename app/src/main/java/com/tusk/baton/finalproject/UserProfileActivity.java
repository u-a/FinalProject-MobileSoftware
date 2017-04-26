package com.tusk.baton.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar myToolbar;
    private TextView userDescription;
    private TextView userTitle;
    private ImageView profilePicture;
    Button editButton;

    User user;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initUI();
        setUI();
//        setUser();

    }

    private void initUI() {
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        user = User.getInstance();
        editButton = (Button) findViewById(R.id.edit_button);
        editButton.setOnClickListener(this);

        userDescription = (TextView) findViewById(R.id.user_description);
        userTitle = (TextView) findViewById(R.id.user_title);
        profilePicture = (ImageView) findViewById(R.id.profile_picture);



    }

    private void setUI(){
        userTitle.setText(user.getUsername());
        userDescription.setText("Name: " + user.getName() + " \nPhone: " + user.getPhoneNumber());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == editButton.getId()){

        }
    }

//    private void setUser(){
//        String[] userInfo = new String[3];
//        userInfo[0] = i.getStringExtra("name");
//        userInfo[1] = i.getStringExtra("user_name");
//        userInfo[2] = i.getStringExtra("phone_number");
//
//        user.setName(userInfo[0]);
//        user.setUsername(userInfo[1]);
//        user.setPhoneNumber(userInfo[2]);
//    }

}
