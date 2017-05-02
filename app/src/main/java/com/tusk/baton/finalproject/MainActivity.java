package com.tusk.baton.finalproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.regions.Regions;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyRelaysFragment.OnFragmentInteractionListener, ViewRelayFragment.OnFragmentInteractionListener, TrendingRelaysFragment.OnFragmentInteractionListener, TrendingLocationsFragment.OnFragmentInteractionListener, SponsoredFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener, View.OnClickListener{

    private ViewGroup insertFrag;
    private RelayList relayClass;
    private static final String TAG = "MainActivity~~";
    HashMap<String, Fragment> fragmentHashMap;

    BottomNavigationView navigation;
    private Location currentLocation;
    private Toolbar myToolbar;
    private Intent userProfileIntent;
    private User user;
    private GPSManager gpsManager;
    private Button createRelayButton;
    public Dataset dataset;
    private CognitoSyncManager syncClient;

    MyRelaysFragment frag1;
    TrendingRelaysFragment frag2;
    TrendingLocationsFragment frag3;
    SponsoredFragment frag4;
    SearchFragment frag5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relayClass = RelayList.getInstance();

        createUser();
        //initializes all the main activity UI elements
        //example: toolbar at the top and navigationbar
        //Same as above for the 5 fragments
        initFragments();
        initUI();

        setupUserProfileIntent();

        gpsManager = new GPSManager(this);
        Log.d(TAG, "onCreate: GPSManager initialized");

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
//
//        dataset = syncClient.openOrCreateDataset("myDataset");
//        HashMap<String, String> testMap = new HashMap<>();
//        testMap.put("Yo","Yo");
//        testMap.put("New", "Push");
//        dataset.put("myKey", "hallo");
//        dataset.putAll(testMap);
//        dataset.synchronize(new DefaultSyncCallback() {
//            @Override
//            public void onSuccess(Dataset dataset, List newRecords) {
//                //Your handler code here
//
//
//            }
//        });

        try {
            CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                    getApplicationContext(),
                    "us-west-2:ac6c2702-ddb5-4331-b515-a7097beae30c", // Identity Pool ID
                    Regions.US_WEST_2 // Region
            );

            syncClient = new CognitoSyncManager(
                    getApplicationContext(),
                    Regions.US_WEST_2, // Region
                    credentialsProvider);

            for (Relay iRelay : relayClass.getHashMap().values()) {
//                relayClass.pushToDB(relayClass.getRelay("TOTS Tuesay"), syncClient);
                relayClass.pushToDB(iRelay.getTitle(), iRelay.getJSONString(),syncClient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initFragments() {
        frag1 = new MyRelaysFragment();
        frag2 = new TrendingRelaysFragment();
        frag3 = new TrendingLocationsFragment();
        frag4 = new SponsoredFragment();
        frag5 = new SearchFragment();

        fragmentHashMap = new HashMap<>();
        fragmentHashMap.put(getResourceString(R.string.my_relays), frag1);
        fragmentHashMap.put(getResourceString(R.string.trending_relays), frag2);
        fragmentHashMap.put(getResourceString(R.string.trending_locations), frag3);
        fragmentHashMap.put(getResourceString(R.string.sponsored), frag4);
        fragmentHashMap.put(getResourceString(R.string.search), frag5);
        setFragment(frag1);

    }


    private void initUI() {
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_dashboard_black_24dp);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        createRelayButton = (Button) myToolbar.findViewById(R.id.createRelayButton);
        createRelayButton.setOnClickListener(this);

        insertFrag = (LinearLayout) findViewById(R.id.linActMain);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment currentFragment = null;
            createRelayButton.setVisibility(View.VISIBLE);
            switch (item.getItemId()) {
                case R.id.navigation_my_relays:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.my_relays));
                    break;
                case R.id.navigation_trending_relays:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.trending_relays));
                    break;
                case R.id.navigation_sponsored:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.sponsored));
                    HashMap<String, String> mapp = relayClass.pullFromDB(syncClient);
                    for(String s: mapp.values()){
                        Log.d(TAG, "onNavigationItemSelected: mapp="+s);
                    }
                    break;
//                case R.id.navigation_search:
//                    currentFragment = fragmentHashMap.get(getResourceString(R.string.search));
//                    break;
            }
            if (currentFragment != null) {
                setFragment(currentFragment);
                return true;
            }
            return false;
        }

    };

    private void setFragment(Fragment inFrag) {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.linActMain, inFrag, inFrag.getTag());
        ft.commit();
    }

    public String getResourceString(int inRID) {
        return getResources().getString(inRID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager #PERMISSION_GRANTED}
     *                     or {@link PackageManager #PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        gpsManager.register();

    }

    public void updateGPSLocation(Location lastKnownLocation) {
        currentLocation = lastKnownLocation;

        if(frag1 != null){
            frag1.setCurrentLoc(currentLocation);
        }
        if(frag2 != null){
            frag2.setCurrentLocation(currentLocation);

        }

        if(frag3 != null){
          frag3.setCurrentLocation(currentLocation);

        }

        if(frag4 != null){
            frag4.setCurrentLocation(currentLocation);
        }

//        if (currentLocation!=null)
//            Log.d(TAG, "updateGPSLocation: Lat="+lastKnownLocation.getLatitude() + " Long=" + lastKnownLocation.getLongitude());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gpsManager.unregister();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onButtonClicked(int i) {
        if (i == 0) {
            Log.d(TAG, "onButtonClicked: ");
            Intent intent = new Intent(this, CreateRelayActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_profile:
                Log.d(TAG, "User Profile");
                System.out.println("Pressed User Profile");
                if (user.getPictureUri() != null) {
                    System.out.println("Profile Pic:~ " + user.getPictureUri().toString());
                }
                startActivity(userProfileIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }

    private void setupUserProfileIntent() {
        userProfileIntent = new Intent(this, UserProfileActivity.class);
    }

    private void createUser() {
        user = User.getInstance();
        user.setName(getResources().getString(R.string.name));
        user.setUsername(getResources().getString(R.string.user_name));
        user.setPhoneNumber( getResources().getString(R.string.phone_number));
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == createRelayButton.getId()) {
            Log.d(TAG, "onClick: button");
            Intent intent = new Intent(this, CreateRelayActivity.class);
            startActivityForResult(intent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            FragmentManager fm = getSupportFragmentManager();
            switch (navigation.getSelectedItemId()) {
                case R.id.navigation_my_relays: {
                    fm.beginTransaction().detach(fragmentHashMap.get(getResourceString(R.string.my_relays)))
                            .attach(fragmentHashMap.get(getResourceString(R.string.my_relays))).commitAllowingStateLoss();
                    System.out.println("My relays trigge~~: " );
                }
                    break;
                case R.id.navigation_sponsored: {
                    fm.beginTransaction().detach(fragmentHashMap.get(getResourceString(R.string.sponsored)))
                            .attach(fragmentHashMap.get(getResourceString(R.string.sponsored))).commitAllowingStateLoss();

                }
                    break;
                case R.id.navigation_trending_relays: {
                    fm.beginTransaction().detach(fragmentHashMap.get(getResourceString(R.string.trending_relays)))
                            .attach(fragmentHashMap.get(getResourceString(R.string.trending_relays))).commitAllowingStateLoss();
                }
                    break;
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public MainActivity getSelf() {
        return this;
    }
}