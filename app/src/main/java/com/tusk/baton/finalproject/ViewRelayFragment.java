package com.tusk.baton.finalproject;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tomoestreich on 4/26/2017.
 */

public class ViewRelayFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    View view;
    LinearLayout linearRelay;
    View child;
    RelayList relayList;
    Relay tempRelay;
    TextView titleView, legView, runnerView;
    ImageView imageView;

    public ViewRelayFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_relay_view, container, false);
        linearRelay = (LinearLayout) view.findViewById(R.id.linearRelay);
        relayList = RelayList.getInstance();
        tempRelay = new Relay();
        Bundle bundle;
        bundle = getArguments();
        String title = bundle.getString("title");
        System.out.println(title);

        child = getLayoutInflater(savedInstanceState).inflate(R.layout.relay_view_layout, null);
        titleView = (TextView) child.findViewById(R.id.title_text);
        legView = (TextView) child.findViewById(R.id.leg_text);
        runnerView = (TextView) child.findViewById(R.id.runner_text);
        imageView = (ImageView) child.findViewById(R.id.eventImage);
        tempRelay = relayList.getRelay(title);
        titleView.setText(title);

        String[] leg = tempRelay.getLegs();
        String legFinal = "";
        for(int i = 0; i < leg.length; i++){
            legFinal = legFinal + "\n" + leg[i] + "\n";
        }
        legView.setText(legFinal);

        String[] run = tempRelay.getRunners();
        String runFinal = "";
        for(int i = 0; i < run.length; i++){
            runFinal = runFinal + "\n" + run[i] + "\n";
        }
        runnerView.setText(runFinal);

        imageView.setImageResource(tempRelay.getPicture());

        linearRelay.addView(child);
        return view;
    }

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
        linearRelay.removeAllViews();
        mListener = null;
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
