package com.example.lilialobato.pawstagramV3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.CheckLikedImageActivity;
import com.example.lilialobato.pawstagramV3.R;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.adapter.LikeArrayAdapter;
import com.example.lilialobato.pawstagramV3.models.Like;
import com.example.lilialobato.pawstagramV3.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LikesFragment extends Fragment {


    ListView likes_lv;
    ArrayList<Like> arrayLikesList;
     LikeArrayAdapter likeArrayAdapter;

    public LikesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_likes, container, false);

        likes_lv = view.findViewById(R.id.likes_lv);
        arrayLikesList = new ArrayList<Like>();
        likeArrayAdapter = new LikeArrayAdapter(getContext(),R.layout.like_single_item,arrayLikesList);

          likes_lv.setAdapter(likeArrayAdapter);

        //like Home fragment

        //get all story id associated with current user_id from likes db


        //then

        //get story data from stories database

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        likes_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Like like = arrayLikesList.get(position);


                if(like != null) {
                    Intent intent = new Intent(getContext(), CheckLikedImageActivity.class);
                    startActivity(intent);
                }

            }
        });



    }




}
