package com.vinrish.brooklyn.learn;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.model.E_learning;
import com.vinrish.brooklyn.model.Notices;

import java.util.ArrayList;
import java.util.List;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.myLearnViewholder> {

    private List<E_learning> list = new ArrayList<>();
    LearnAdapter(List<E_learning> list) {

        this.list = list;
    }
    @NonNull
    @Override
    public myLearnViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list,parent,false);
        return new myLearnViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myLearnViewholder holder, int position) {

        holder.ima.setImageBitmap(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myLearnViewholder extends RecyclerView.ViewHolder {

        ImageView ima;
        TextView name,description;
        public myLearnViewholder(@NonNull View itemView) {
            super(itemView);

            ima = itemView.findViewById(R.id.ima);
            name =itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);

        }
    }
}
