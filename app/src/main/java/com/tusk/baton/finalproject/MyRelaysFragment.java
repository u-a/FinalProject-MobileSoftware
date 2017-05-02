package com.tusk.baton.finalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRelaysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MyRelaysFragment extends Fragment implements View.OnClickListener, ViewRelayFragment.OnFragmentInteractionListener {

    private OnFragmentInteractionListener mListener;
    private TextView mTextMessage;
    private TextView mSubTextMessage;
    RelayList relayList;
    LinearLayout cardStack;
    HashMap<Integer, View> cardList;                //this is a hashmap to make onClick easier to implement
    View view;
//    View child1, child12;
    HashMap<String, Fragment> fragmentHashMap;
    Leg myLeg;


    public MyRelaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("on create view my relays");
        Log.d("YOOOO", "on create view my relays");
        relayList = RelayList.getInstance();
        view = inflater.inflate(R.layout.fragment_my_relays, container, false);
        cardStack = (LinearLayout) view.findViewById(R.id.cardStack);

        cardList = new HashMap<>();


        for (Relay relay: relayList.getHashMap().values()) {
//            Log.d(TAG, "onCreateView: 1");
            if (relay.getPrivacy() == Resources.PRIVACY_PRIVATE || relay.getPrivacy() == Resources.PRIVACY_SECRET) {
                View tempChild;
                tempChild = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
                tempChild = getCard(tempChild, relay);
                mTextMessage = (TextView) tempChild.findViewById(R.id.info_text);
                cardStack.addView(tempChild);
                tempChild.setOnClickListener(this);
                cardList.put(tempChild.getId(), tempChild);
            }
        }


        /*child = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child = relayList.getCard(child*//*,  "NEED TO LOOP THROUGH TH E HASHMAP"*//*);
        mTextMessage = (TextView) child.findViewById(R.id.info_text);
        mTextMessage.setText("Test, going to set this in the relayList class");
        cardStack.addView(child);
        child.setOnClickListener(this);*/
/*
        // 2nd relay 10 people
        String[] legs1 = {"Meet at the Drillfield", "Enjoy some soccer with the bros", "Deets ice cream! :)"};
        Leg[] legArr1 = {""}
        String[] runners1 = {"Tom", "Sushant", "Karthik", "Ushan", "Elmo", "Barney", "Chuck Norris", "Bruce Lee", "Bob the Builder"
                , "Dave"};
        relayList.addRelay("Soccer on the Drillfield", R.drawable.soccer, legs1, runners1,0 );
        child1 = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child1 = relayList.getCard(child1,  "Soccer on the Drillfield", "\nRunners: " + runners1.length + "\n" + "Legs: " + legs1.length + "\n");
        mTextMessage = (TextView) child1.findViewById(R.id.info_text);
        cardStack.addView(child1);
        child1.setOnClickListener(this);

        //12 people
        String[] legs22 = {"Meet at Richards house for wine and cheese", "Sing at TOTS", "Get Bennys", "Count Calories"};
        String[] runners22 = {"Richard Cheese", "Tom", "Sushant", "Karthik", "Ushan", "Elmo", "Barney", "Chuck Norris", "Bruce Lee", "Bob the Builder"
                , "Dave", "Pablo Esobar", "Lionel Messi" };

        relayList.addRelay("TOTS Tuesay", R.drawable.tots, legs22, runners22,0 );
        child12 = getLayoutInflater(savedInstanceState).inflate(R.layout.event_layout, null);
        child12 = relayList.getCard(child12,  "TOTS Tuesay", "\nRunners: " + runners22.length + "\n" + "Legs: " + legs22.length + "\n");
        mTextMessage = (TextView) child12.findViewById(R.id.info_text);
        cardStack.addView(child12);
        child12.setOnClickListener(this);

*/

        ViewRelayFragment frag6 = new ViewRelayFragment();
        fragmentHashMap = new HashMap<>();
        fragmentHashMap.put(getResourceString(R.string.view_relay), frag6);

        return view;
    }

    public View getCard(View child, Relay inRelay){
        CardView eventCard;
        eventCard = (CardView) child.findViewById(R.id.card_view);
        mTextMessage = (TextView) child.findViewById(R.id.info_text);
        mSubTextMessage = (TextView) child.findViewById(R.id.more_info_text);
//        System.out.println("HASH MAP: " + inRelay.getTitle());

        mTextMessage.setText(inRelay.getTitle());
        String mainText =  "Legs: " + inRelay.getLegs().size() + "\nRunners: " + inRelay.getRunners().size();
        mSubTextMessage.setText(mainText);
        ImageView eventPic = (ImageView) eventCard.findViewById(R.id.eventImage);
        eventPic.setImageResource(inRelay.getPicture());
        return child;
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
        if (cardList.containsKey(v.getId())) {
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            String cardTitle = ((TextView) v.findViewById(R.id.info_text)).getText().toString();
//            Log.d(TAG, "onClick: cardTitle="+cardTitle);
            bundle.putString("title", cardTitle);
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }
        /*
        if(v == child1){
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            bundle.putString("title", "Soccer on the Drillfield");
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }
        else if(v == child12){
            Fragment currentFragment = null;
            currentFragment = fragmentHashMap.get(getResourceString(R.string.view_relay));
            Bundle bundle = new Bundle();
            bundle.putString("title", "TOTS Tuesay");
            currentFragment.setArguments(bundle);
            setFragment(currentFragment);
        }*/

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

    public String getResourceString(int inRID) {
        return getResources().getString(inRID);
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

        void onButtonClicked(int i);
    }
}
