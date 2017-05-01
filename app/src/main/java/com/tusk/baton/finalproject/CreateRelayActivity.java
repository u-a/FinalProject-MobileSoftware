package com.tusk.baton.finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;

import static com.tusk.baton.finalproject.UserProfileActivity.REQUEST_IMAGE;

public class CreateRelayActivity extends AppCompatActivity implements View.OnClickListener{

    static final String TAG = "CreateRelayActivity~~";
    private boolean visible[];
    private LinearLayout layoutView[];
    private LinearLayout masterLayout;
    private Button addLeg, create, addPic;
    private EditText titleText;
    private EditText[] locationText;
    private EditText[] timeText;
    private int numVisible;
    RelayList thisRelayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_relay);
        initArrays();
        masterLayout = (LinearLayout) findViewById(R.id.masterLayout);
        addLeg = (Button) findViewById(R.id.addLegButton);
        addLeg.setOnClickListener(this);
        create = (Button) findViewById(R.id.createButton);
        create.setOnClickListener(this);
        addPic = (Button) findViewById(R.id.pictureButton);
        addPic.setOnClickListener(this);
        thisRelayList = RelayList.getInstance();
        titleText = (EditText)findViewById(R.id.titleText);
        numVisible = 2;
    }

    private void initArrays() {
        visible = new boolean[] {true, true, false, false};
        layoutView = new LinearLayout[4];
        layoutView[0] = (LinearLayout) findViewById(R.id.leg1Layout);
        layoutView[1] = (LinearLayout) findViewById(R.id.leg2Layout);
        layoutView[2] = (LinearLayout) findViewById(R.id.leg3Layout);
        layoutView[3] = (LinearLayout) findViewById(R.id.leg4Layout);

        titleText = (EditText) findViewById(R.id.titleText);
        locationText = new EditText[4];
        locationText[0] = (EditText) findViewById(R.id.location1Text);
        locationText[1] = (EditText) findViewById(R.id.location2Text);
        locationText[2] = (EditText) findViewById(R.id.location3Text);
        locationText[3] = (EditText) findViewById(R.id.location4Text);
        timeText = new EditText[4];
        timeText[0] = (EditText) findViewById(R.id.time1Text);
        timeText[1] = (EditText) findViewById(R.id.time2Text);
        timeText[2] = (EditText) findViewById(R.id.time3Text);
        timeText[3] = (EditText) findViewById(R.id.time4Text);

        updateVisibilities();
    }

    private void updateVisibilities() {
        for (int i = 0; i < visible.length; i++) {
            int tempVisiblility;
            if (visible[i]) tempVisiblility = View.VISIBLE;
            else tempVisiblility = View.GONE;
            layoutView[i].setVisibility(tempVisiblility);
        }
//        masterLayout.invalidate();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        if (v.getId() == addLeg.getId()) {
            for(int i = 1; i < visible.length; i++) {
                if (visible[i] == false && visible[i-1] == true) {
                    visible[i] = true;
                    numVisible++;
                    break;
                }
            }
            updateVisibilities();
            masterLayout.invalidate();
        }

        if (v.getId() == create.getId()) {
            int trueCount = 0;
            for (int i = 0; i < visible.length; i++) {
                if (visible[i]) {
                    trueCount++;
                }
            }

            Leg[] myLegs = new Leg[trueCount];
//            for(int z = 0; z < trueCount; z++){
//                myLegs[z].setMyLocation(Double.parseDouble(locationText[z].getText().toString()));
//            }




//            String[] legs = new String[trueCount];
//            for (int i = 0; i < trueCount; i++) {
//                legs[i] =
//            }
        }

        if(v.getId() == addPic.getId()){

        }
    }
}
