package com.tusk.baton.finalproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MyRelaysFragment.OnFragmentInteractionListener, TrendingRelaysFragment.OnFragmentInteractionListener, TrendingLocationsFragment.OnFragmentInteractionListener, SponsoredFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    HashMap<String, Fragment> fragmentHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, inFrag, inFrag.getTag());
        ft.commit();
    }

    public String getResourceString(int inRID) {
        return getResources().getString(inRID);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}