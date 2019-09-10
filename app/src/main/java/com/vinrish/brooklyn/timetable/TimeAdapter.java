package com.vinrish.brooklyn.timetable;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vinrish.brooklyn.model.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.myTimeViewholder> {

    private List<Time> list = new ArrayList<>();
    TimeAdapter(List<Time> list) {

        this.list = list;
    }
    @NonNull
    @Override
    public myTimeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myTimeViewholder holder, int position) {
        holder.ima.setImageBitmap(list.get(position).getImage_url());
        holder.description.setText(list.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myTimeViewholder extends RecyclerView.ViewHolder {
        
        ImageView ima;
        TextInputEditText description;
        public myTimeViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
