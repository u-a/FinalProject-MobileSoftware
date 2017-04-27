package com.tusk.baton.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateRelayActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean visible[];
    private LinearLayout layoutView[];
    private LinearLayout masterLayout;
    private Button addLeg, create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addLeg = (Button) findViewById(R.id.addLegButton);
        addLeg.setOnClickListener(this);
        create = (Button) findViewById(R.id.createButton);
        create.setOnClickListener(this);
        masterLayout = (LinearLayout) findViewById(R.id.masterLayout);
        initArrays();
    }

    private void initArrays() {
        visible = new boolean[] {true, true, false, false};
        layoutView = new LinearLayout[4];
        layoutView[0] = (LinearLayout) findViewById(R.id.leg1Layout);
        layoutView[1] = (LinearLayout) findViewById(R.id.leg2Layout);
        layoutView[2] = (LinearLayout) findViewById(R.id.leg3Layout);
        layoutView[3] = (LinearLayout) findViewById(R.id.leg4Layout);
        updateVisibilities();
    }

    private void updateVisibilities() {
        for (int i = 0; i < visible.length; i++) {
            int tempVisiblility;
            if (visible[i]) tempVisiblility = View.VISIBLE;
            else tempVisiblility = View.GONE;
            layoutView[i].setVisibility(tempVisiblility);
        }
        masterLayout.invalidate();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == addLeg.getId()) {
            for(int i = 1; i < visible.length; i++) {
                if (visible[i] == false && visible[i-1] == true) {
                    visible[i] = true;
                    break;
                }
            }
            updateVisibilities();
            masterLayout.invalidate();
        }

        if (v.getId() == create.getId()) {
            for (int i = 0; i < visible.length; i++) {
                if (visible[i]) {
                    //create relays

                }
            }
        }
    }
}
