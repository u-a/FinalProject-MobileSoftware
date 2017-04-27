package com.tusk.baton.finalproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class UserProfileActivity extends AppCompatActivity implements
        UserEditFragment.OnFragmentInteractionListener,
        UserViewFragment.OnFragmentInteractionListener
{
    private Toolbar myToolbar;
    private UserViewFragment userViewFragment;
    private UserEditFragment userEditFragment;
    static final int REQUEST_IMAGE = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;


    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    User user;
    Intent i;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initUI();

    }

    private void initUI() {
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        try {
            actionBar.setIcon(R.drawable.account);
        }
        catch (Exception e){
            System.out.println("Iconset Fail:~ " + e);
        }
        userEditFragment = new UserEditFragment();
        userViewFragment = new UserViewFragment();

        user = User.getInstance();


        setFragment(userViewFragment);

    }

    public void setFragment(Fragment inFrag) {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.user_content, inFrag, inFrag.getTag());
        ft.commit();
    }

    public void setEditFragment(){
        setFragment(userEditFragment);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void launchCameraIntent(){
        File file = new File(Environment.getExternalStorageDirectory(), "/DCIM/userProfile.jpg");
        imageUri = Uri.fromFile(file);
        System.out.println("Filename:~ " + imageUri);

        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        if (takePicIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicIntent, REQUEST_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE && resultCode== RESULT_OK ){
            user.setPictureUri(imageUri);
            if (user.getPictureUri() != null){
                setPicture();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void setPicture(){
        try {
            int filePermissions = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (filePermissions != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }

            Bitmap bitMap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            userViewFragment.profilePicture.setImageBitmap(bitMap);
            user.pic = bitMap;
            setFragment(userViewFragment);
        }
        catch (Exception e)
        {
            //handle exception
            System.out.println("Exception:~ " + e);
        }
    }
}
