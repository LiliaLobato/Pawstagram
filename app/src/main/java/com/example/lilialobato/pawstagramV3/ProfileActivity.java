package com.example.lilialobato.pawstagramV3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.adapter.ImageArrayAdapter;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.models.Image;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



/*This Activity runs when you're attempting to browser another user's profile*/
/*It takes user id passed with the intent to this activity */


public class ProfileActivity extends AppCompatActivity {

    TextView follow_this_profile,posts_num_tv,following_num_tv,followers_num_tv,display_name_tv,description;
    CircleImageView other_user_profile_image;
    GridView images_grid_layout;
    ArrayList<Image> arrayListImages;
    ImageArrayAdapter imageArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        follow_this_profile =  (TextView) findViewById(R.id.follow_this_profile);
        posts_num_tv =  (TextView) findViewById(R.id.posts_num_tv);
        following_num_tv =  (TextView) findViewById(R.id.following_num_tv);
        followers_num_tv =  (TextView) findViewById(R.id.followers_num_tv);
        display_name_tv =  (TextView) findViewById(R.id.display_name_tv);
        description =  (TextView) findViewById(R.id.description);
        other_user_profile_image = (CircleImageView) findViewById(R.id.profile_image);
        images_grid_layout = (GridView) findViewById(R.id.images_grid_layout);

        follow_this_profile.setEnabled(false);

         arrayListImages = new ArrayList<>();

        imageArrayAdapter = new ImageArrayAdapter(ProfileActivity.this,0,arrayListImages);
        images_grid_layout.setAdapter(imageArrayAdapter);


    }

}
