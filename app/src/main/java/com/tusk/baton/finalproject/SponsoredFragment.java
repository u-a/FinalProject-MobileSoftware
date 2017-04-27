package com.tusk.baton.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SponsoredFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SponsoredFragment extends Fragment implements View.OnClickListener, ViewRelayFragment.OnFragmentInteractionListener {

    private OnFragmentInteractionListener mListener;
    private TextView mTextMessage;
    RelayList relayList;
    LinearLayout cardStack;
    View view;
    View child, child1, child2;
    HashMap<String, Fragment> fragmentHashMap;

    public SponsoredFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        relayList = RelayList.getInstance();
        view = inflater.inflate(R.layout.fragment_my_relays, container, false);
        cardStack = (LinearLayout) view.findViewById(R.id.cardStack);

        // add 1st relay
        String[] legs = {"Info session @4pm", "Break for dinner with teams @6pm", "Dessert @ 7:30pm"};
        String[] runners = {"Tom", "Sushant", "Karthik", "Ushan", "Sam", "Seyam"};
        relayList.addRelay("GM Meet and Greet", R.drawable.gm, legs, runners,0 );
        child = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child = relayList.getCard(child,  "GM Meet and Greet");
        mTextMessage = (TextView) child.findViewById(R.id.info_text);
        cardStack.addView(child);
        child.setOnClickListener(this);

        // 2nd relay
        String[] legs1 = {"Info session @ 12pm", "Lunch with employees @1:30", "Group Interviews @ 3:30"};
        String[] runners1 = {"Tom", "Sushant", "Karthik", "Ushan", "Chuck Norris", "Bob the Builder", "Dave"};
        relayList.addRelay("Intel Luncheon", R.drawable.intel, legs1, runners1,0 );
        System.out.println("IMAGE FOR INTEL: " + R.drawable.intel);
        child1 = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child1 = relayList.getCard(child1,  "Intel Luncheon");
        mTextMessage = (TextView) child1.findViewById(R.id.info_text);
        cardStack.addView(child1);
        child1.setOnClickListener(this);

        // 2nd relay
        String[] legs2 = {"Info session @ 12pm", "Lunch with employees @1:30", "Individual Interviews @ 3:30"};
        String[] runners2 = {"Tom", "Sushant", "Karthik", "Ushan", "Chuck Norris", "Elon Musk"};
        relayList.addRelay("SpaceX Interview Day", R.drawable.spacex, legs2, runners2,0 );
        child2 = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child2 = relayList.getCard(child2,  "SpaceX Interview Day");
        mTextMessage = (TextView) child2.findViewById(R.id.info_text);
        cardStack.addView(child2);
        child2.setOnClickListener(this);

        ViewRelayFragment frag6 = new ViewRelayFragment();
        fragmentHashMap = new HashMap<>();
        fragmentHashMap.put(getResourceString(R.string.view_relay), frag6);
        return view;
    }

    public String getResourceString(int inRID) {
        return getResources().getString(inRID);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        System.out.println(v.getId() + " " +  child1.getId());
        // GM
        if(v == child){
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            bundle.putString("title", "GM Meet and Greet");
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }
        //INTEL
        else if(v== child1){
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            bundle.putString("title", "Intel Luncheon");
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }
        //SPACEX
        else if(v == child2){
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            bundle.putString("title", "SpaceX Interview Day");
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }
    }

    private void setFragment(Fragment inFrag) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.linActMain, inFrag, inFrag.getTag());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
