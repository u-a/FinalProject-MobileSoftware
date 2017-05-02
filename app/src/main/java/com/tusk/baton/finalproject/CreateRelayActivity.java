package com.tusk.baton.finalproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.location.Location;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateRelayActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener, TimePickerDialog.OnTimeSetListener{

    static final String TAG = "CreateRelayActivity~~";
    RelayList relayList;
    private boolean visible[];
    private LinearLayout layoutView[];
    private LinearLayout masterLayout;
    private Button addLeg, create;
    private EditText titleText;
    private EditText[] locationText;
    private EditText[] timeText;
    private EditText[] dateText;
    int index = -1;
    int indexDate = -1;

    Date[] legDates = new Date[]{null,null,null,null};
    Date[] legTimes = new Date[] {null,null,null,null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_relay);
        initArrays();
        updateVisibilities();
        initViews();
        relayList = RelayList.getInstance();
    }

    private void initViews() {
        masterLayout = (LinearLayout) findViewById(R.id.masterLayout);
        addLeg = (Button) findViewById(R.id.addLegButton);
        addLeg.setOnClickListener(this);
        create = (Button) findViewById(R.id.createButton);
        create.setOnClickListener(this);
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
        timeText[0].setOnClickListener(this);
        timeText[1].setOnClickListener(this);
        timeText[2].setOnClickListener(this);
        timeText[3].setOnClickListener(this);
        dateText = new EditText[4];
        dateText[0] = (EditText) findViewById(R.id.date1Text);
        dateText[1] = (EditText) findViewById(R.id.date2Text);
        dateText[2] = (EditText) findViewById(R.id.date3Text);
        dateText[3] = (EditText) findViewById(R.id.date4Text);
        dateText[0].setOnClickListener(this);
        dateText[1].setOnClickListener(this);
        dateText[2].setOnClickListener(this);
        dateText[3].setOnClickListener(this);

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
                    break;
                }
            }
            updateVisibilities();
            masterLayout.invalidate();
        }

        if (v.getId() == create.getId()) {
            int trueCount = 0;

            Relay r = new Relay();
            r.setPrivacy(Resources.PRIVACY_PRIVATE);
            r.setPicture(R.drawable.blankprofile);              //CHANGE THIS TO CAMERA INPUT
            r.setTitle(titleText.getText().toString());
            r.addRunner(new Runner("Ush", "asdf", 0, new Location("")));


            for (int i = 0; i < visible.length; i++) {
                if (visible[i]) {
                    String locationInput = locationText[i].getText().toString();
                    Location loc;
                    if (Resources.getLocationFromString(locationInput)!=null) {
                        loc = new Location(Resources.getLocationFromString(locationInput));
                    }
                    else{
                        loc = new Location(Resources.getLocationFromString("ushan's house"));
                    }

                    Log.d(TAG, "onClick: textfield1 loc="+locationText[i].getText().toString() + " time=" + timeText[i].getText().toString());

                        if (legTimes[i] != null && legDates[i] != null){
                            Date newLegDate = new Date(legDates[i].getYear(),legDates[i].getMonth(), legDates[i].getDate(),
                            legTimes[i].getHours(),legTimes[i].getMinutes());
//                            newLegDate.setTime(legTimes[i].getTime());

                            r.addLeg(new Leg(loc, newLegDate, "", locationInput, ""));
                        }else{
                            r.addLeg(new Leg(loc, new Date(), "", locationInput, ""));
                        }
                }
            }
            relayList.addRelay(r);
            setResult(RESULT_OK);
            finish();
        }
        else if(v.getId() == timeText[0].getId()){
            index = 0;
            showTimePickerDialog(0);
        }
        else if(v.getId() == timeText[1].getId()){
            index = 1;
            showTimePickerDialog(1);
        }
        else if(v.getId() == timeText[2].getId()){
            index = 2;
            showTimePickerDialog(2);
        }
        else if(v.getId() == timeText[3].getId()){
            index = 3;
            showTimePickerDialog(3);
        }
        else if(v.getId() == dateText[0].getId()){
            indexDate = 0;
            showDatePickerDialog();
        }
        else if(v.getId() == dateText[1].getId()){
            indexDate = 1;
            showDatePickerDialog();
        }
        else if(v.getId() == dateText[2].getId()){
            indexDate = 2;
            showDatePickerDialog();
        }
        else if(v.getId() == dateText[3].getId()){
            indexDate = 3;
            showDatePickerDialog();
        }
    }

    public void showTimePickerDialog(int index) {
        DialogFragment newFragment = new TimePickerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("index", index);
//        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("index", index);
//        newFragment.setArguments(bundle);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String s = hourOfDay + ":" + minute;
        SimpleDateFormat f1 = new SimpleDateFormat("HH:mm"); //HH for hour of the day (0 - 23)
        Date d = null;
        try {
            d = f1.parse(s);
            legTimes[index] = d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f2 = new SimpleDateFormat("hh:mma");
         // "12:18am"
        timeText[index].setText(f2.format(d).toLowerCase());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String s = (month+1 )+ "/" + dayOfMonth;
        SimpleDateFormat f1 = new SimpleDateFormat("MM/dd"); //HH for hour of the day (0 - 23)
        Date d = null;
        try {
            d = f1.parse(s);
            legDates[index] = d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat f2 = new SimpleDateFormat("MMM dd");
        // "12:18am"
        dateText[indexDate].setText(f2.format(d));    }

    public static class TimePickerFragment  extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), (CreateRelayActivity)getActivity(), hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (CreateRelayActivity)getActivity(), year, month, day);
        }
    }

}
