package com.tusk.baton.finalproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapterRunner extends RecyclerView.Adapter<MyAdapterRunner.MyViewHolder> {

    private List<Runner> runnerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, id;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            id = (TextView) view.findViewById(R.id.runner_id);
        }
    }


    public MyAdapterRunner(List<Runner> runnerList) {
        this.runnerList = runnerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.runners_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Runner runner = runnerList.get(position);
        holder.name.setText(runner.getName());
        holder.id.setText(runner.getId());
    }

    @Override
    public int getItemCount() {
        return runnerList.size();
    }
}