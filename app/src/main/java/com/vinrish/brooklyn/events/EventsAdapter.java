package com.vinrish.brooklyn.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.model.Event;
import com.vinrish.brooklyn.model.Notices;
import com.vinrish.brooklyn.notices.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.myEventHolder> {

    private List<Event> list = new ArrayList<>();
    EventsAdapter(List<Event> list) {

        this.list = list;
    }
    @NonNull
    @Override
    public myEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_list,parent,false);
        return new myEventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myEventHolder holder, int position) {

        holder.id.setText(Integer.toString(list.get(position).getId()));
        holder.name.setText(list.get(position).getName());
        holder.event.setText(list.get(position).getEvent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myEventHolder extends RecyclerView.ViewHolder {

        TextView id,name,event;
        public myEventHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            event = itemView.findViewById(R.id.events_dat);
        }
    }
}
