package com.vinrish.brooklyn.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vinrish.brooklyn.MySingleton;
import com.vinrish.brooklyn.R;
import com.vinrish.brooklyn.model.Event;
import com.vinrish.brooklyn.model.Notices;
import com.vinrish.brooklyn.notices.NewsAdapter;

import java.util.Arrays;
import java.util.List;

public class Events extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EventsAdapter eventsAdapter;
    private static final String news_url = "http://10.42.0.1/vince/news.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        getEvents();
    }

    private void getEvents() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, news_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                List<Event> list =Arrays.asList(gson.fromJson(response,Event[].class));
                eventsAdapter = new EventsAdapter(list);
                recyclerView.setAdapter(eventsAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
