package com.example.lilialobato.pawstagramV3.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.R;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.adapter.StoryListAdapter;
import com.example.lilialobato.pawstagramV3.models.Story;
import com.example.lilialobato.pawstagramV3.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ListView feed_lv;
    ArrayList<Story> arrayListStories;
    StoryListAdapter storyListAdapter;
    ProgressDialog mProgrssDialog;
    SwipeRefreshLayout swipeRefreshLayout;
    int offset;
    int limit = 2;
    int location;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_home, container, false);
        feed_lv = view.findViewById(R.id.feed_lv);


      swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
      offset = 0;
      location = 0;

        arrayListStories = new ArrayList<Story>();

        mProgrssDialog = new ProgressDialog(getContext());
        mProgrssDialog.setTitle("News Feed");
        mProgrssDialog.setMessage("Updating News Feed....");

        storyListAdapter = new StoryListAdapter(getContext(),R.layout.feed_single_item,arrayListStories);
        feed_lv.setAdapter(storyListAdapter);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = offset + limit;
                location = 0;
                swipeRefreshLayout.setRefreshing(false); // hide refresh icon;
            }
        });

    }










}
