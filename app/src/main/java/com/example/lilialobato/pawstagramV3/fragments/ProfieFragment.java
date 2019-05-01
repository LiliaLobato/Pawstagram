package com.example.lilialobato.pawstagramV3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.R;
import com.example.lilialobato.pawstagramV3.SettingsActivity;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.adapter.ImageArrayAdapter;
import com.example.lilialobato.pawstagramV3.models.Image;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfieFragment extends Fragment {

    TextView edit_profile,posts_num_tv,following_num_tv,followers_num_tv,display_name_tv,description;
    CircleImageView user_profile_image;
    GridView images_grid_layout;
    ArrayList<Image> arrayListImages;
    ImageArrayAdapter imageArrayAdapter;
    User user;
    int user_id,posts,following,followers;
    String email,image;


    public ProfieFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profie, container, false);

        edit_profile = view.findViewById(R.id.follow_this_profile);
        posts_num_tv =  (TextView) view.findViewById(R.id.posts_num_tv);
        following_num_tv =  (TextView) view.findViewById(R.id.following_num_tv);
        followers_num_tv =  (TextView) view.findViewById(R.id.followers_num_tv);
        display_name_tv =  (TextView) view.findViewById(R.id.display_name_tv);
        description =  (TextView) view.findViewById(R.id.description);
        user_profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        images_grid_layout = (GridView) view.findViewById(R.id.images_grid_layout);

        edit_profile.setText("Edit Profile");


         user = SharedPrefrenceManger.getInstance(getContext()).getUserData();
         user_id = user.getId();

        arrayListImages = new ArrayList<>();

        imageArrayAdapter = new ImageArrayAdapter(getContext(),0,arrayListImages);
        images_grid_layout.setAdapter(imageArrayAdapter);



        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSettings();
            }
        });
    }

    private void goToSettings(){
        Intent settingsIntenet = new Intent(getContext(),SettingsActivity.class);

        getContext().startActivity(settingsIntenet);

    }

}
