package com.tusk.baton.finalproject;

import android.net.Uri;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MyRelaysFragment.OnFragmentInteractionListener, TrendingRelaysFragment.OnFragmentInteractionListener, TrendingLocationsFragment.OnFragmentInteractionListener, SponsoredFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    private ViewGroup insertFrag;
    private EventList relayClass;
    private static final String TAG = "Menu~: ";
    HashMap<String, Fragment> fragmentHashMap;


    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        relayClass = new EventList();
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_dashboard_black_24dp);


        MyRelaysFragment frag1 = new MyRelaysFragment();
        TrendingRelaysFragment frag2 = new TrendingRelaysFragment();
        TrendingLocationsFragment frag3 = new TrendingLocationsFragment();
        SponsoredFragment frag4 = new SponsoredFragment();
        SearchFragment frag5 = new SearchFragment();

        fragmentHashMap = new HashMap<>();
        fragmentHashMap.put(getResourceString(R.string.my_relays), frag1);
        fragmentHashMap.put(getResourceString(R.string.trending_relays), frag2);
        fragmentHashMap.put(getResourceString(R.string.trending_locations), frag3);
        fragmentHashMap.put(getResourceString(R.string.sponsored), frag4);
        fragmentHashMap.put(getResourceString(R.string.search), frag5);
        setFragment(frag1);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        insertFrag = (LinearLayout) findViewById(R.id.linActMain);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment currentFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_my_relays:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.my_relays));
                    break;
                case R.id.navigation_trending_relays:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.trending_relays));
                    break;
                case R.id.navigation_trending_locations:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.trending_locations));
                    break;
                case R.id.navigation_sponsored:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.sponsored));
                    break;
                case R.id.navigation_search:
                    currentFragment = fragmentHashMap.get(getResourceString(R.string.search));
                    break;
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
    public void onFragmentInteraction(Uri uri) {

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
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }
}
