package com.vinrish.brooklyn.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.Utils.Constants;
import com.vinrish.brooklyn.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;


    public MyAdapter(List<ListItem> listItem, Context context) {
        this.context = context;
        this.listItems = listItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.the_email.setText(listItem.getEmail());
        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.imageView);
        holder.my_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on \""+listItem.getHead()+"\'s\" design", Toast.LENGTH_SHORT).show();
                holder.email_layout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public TextView cost, the_email;
        public ImageView imageView;
        public LinearLayout my_card, cost_layout, email_layout;
        public Button like, dislike;
        public ProgressBar loading;


        public ViewHolder(View itemView) {
            super(itemView);

            loading = (ProgressBar) itemView.findViewById(R.id.progress);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            the_email = (TextView) itemView.findViewById(R.id.the_email);

            imageView = (ImageView) itemView.findViewById(R.id.imageViewItem);

            my_card = (LinearLayout) itemView.findViewById(R.id.my_card);
            email_layout = (LinearLayout) itemView.findViewById(R.id.email_layout);
        }
    }
}
