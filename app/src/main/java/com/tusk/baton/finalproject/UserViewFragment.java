package com.tusk.baton.finalproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class UserViewFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private TextView userDescription;
    private TextView userTitle;
    ImageView profilePicture;
    private CardView userProfileCard;



    Button editButton;
    User user;



    public UserViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_view, container, false);

        user = User.getInstance();

//        System.out.println("Name:~ " + user.getName());

        editButton = (Button) view.findViewById(R.id.edit_button);
        editButton.setOnClickListener(this);

        userProfileCard = (CardView) view.findViewById(R.id.user_profile_card);
        userDescription = (TextView) view.findViewById(R.id.user_description);
        userTitle = (TextView) view.findViewById(R.id.user_title);

        profilePicture = (ImageView) view.findViewById(R.id.profile_picture);

        setUI();

        if (user.pic != null){
            profilePicture.setImageBitmap(user.pic);
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void setUI(){
        System.out.println("SetText:~ ");
        userTitle.setText(user.getUsername());
        userDescription.setText("Name: " + user.getName() + " \nPhone: " + user.getPhoneNumber());
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
        System.out.println("Clicked Edit:~ ");

        if (v.getId() == editButton.getId()){
            System.out.println("Clicked Edit:~ ");
            mListener.launchCameraIntent();

        }
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
        void launchCameraIntent ();
    }




}
