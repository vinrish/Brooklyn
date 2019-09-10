package com.vinrish.brooklyn.notices;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.model.Notices;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.myViewholder> {

    private List<Notices> list = new ArrayList<>();
    NewsAdapter(List<Notices> list) {

        this.list = list;
    }

    @Override
    public myViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_view,parent,false);
        return new myViewholder(view);
    }

    @Override
    public void onBindViewHolder(myViewholder holder, int position) {

        holder.id.setText(Integer.toString(list.get(position).getId()));
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {

        TextView id,name,email;
        public myViewholder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email_news);
        }
    }
}
