package com.tusk.baton.finalproject;

import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoestreich on 4/26/2017.
 */

public class ViewRelayFragment extends Fragment {
    private static final String TAG = "ViewRelayFragment~~";
    private OnFragmentInteractionListener mListener;
    View view;
    LinearLayout linearRelay;
    View child;
    RelayList relayList;
    Relay tempRelay;
    TextView titleView, legView, runnerView;
    ImageView imageView;

    private List<Runner> runnerList = new ArrayList<>();
    private List<Leg> legList = new ArrayList<>();

    private RecyclerView recyclerViewRunner;
    private RecyclerView recyclerViewLeg;
    private MyAdapterRunner mAdapterRunner;
    private MyAdapterLeg mAdapterLeg;

    private Button createRelayButton;

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
        Log.d(TAG, "onCreateView: title="+title);

        createRelayButton = (Button) getActivity().findViewById(R.id.createRelayButton);
        createRelayButton.setVisibility(View.GONE);

        child = getLayoutInflater(savedInstanceState).inflate(R.layout.relay_view_layout, null);
        titleView = (TextView) child.findViewById(R.id.title_text);
        imageView = (ImageView) child.findViewById(R.id.eventImage);

        tempRelay = relayList.getRelay(title);
        titleView.setText(title);

        legList = tempRelay.getLegs();
        runnerList = tempRelay.getRunners();

        recyclerViewRunner = (RecyclerView) child.findViewById(R.id.recycler_view);
        recyclerViewLeg= (RecyclerView) child.findViewById(R.id.recycler_view_legs);

        mAdapterRunner = new MyAdapterRunner(runnerList);
        mAdapterLeg = new MyAdapterLeg(legList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManagerLeg = new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerViewRunner.setLayoutManager(mLayoutManager);
        recyclerViewRunner.addItemDecoration(new LineDivider(getActivity().getApplicationContext()));
        recyclerViewRunner.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRunner.setAdapter(mAdapterRunner);

        recyclerViewLeg.setLayoutManager(mLayoutManagerLeg);
        recyclerViewLeg.addItemDecoration(new LineDivider(getActivity().getApplicationContext()));
        recyclerViewLeg.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLeg.setAdapter(mAdapterLeg);

        mAdapterLeg.notifyDataSetChanged();
        mAdapterRunner.notifyDataSetChanged();

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
