package com.tusk.baton.finalproject;

import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapterLeg extends RecyclerView.Adapter<MyAdapterLeg.MyViewHolder> {

    private List<Leg> legList;
    private User user;
    private Location currentLocation;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, loc;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            loc = (TextView) view.findViewById(R.id.loc);
            time = (TextView) view.findViewById(R.id.time);
        }
    }


    public MyAdapterLeg(List<Leg> legList) {
        this.legList = legList;
        user = User.getInstance();
        currentLocation = user.getMyLocation();
        Log.d("DistanceTO", "LAT " + currentLocation.getLatitude() + " Long" + currentLocation.getLongitude() );
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.legs_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Leg leg = legList.get(position);
        holder.name.setText(leg.getTitle());
        holder.loc.setText("Distance: " + ((int)leg.getLocation().distanceTo(currentLocation)) + " Meters");
        holder.time.setText(""+leg.getTime().toString().substring(0,
                leg.getTime().toString().length() - 12 ));

//        holder.time.setText(""+leg.getTime().toString());

    }

    @Override
    public int getItemCount() {
        return legList.size();
    }
}