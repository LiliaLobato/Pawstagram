package com.example.lilialobato.pawstagramV3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lilialobato.pawstagramV3.helper.SquareImageView;
import com.squareup.picasso.Picasso;

public class CheckLikedImageActivity extends AppCompatActivity {

    TextView story_username_tv;
    SquareImageView story_image;
    ImageView back_arrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_liked_image);


        story_username_tv = (TextView) findViewById(R.id.story_username_tv);
        story_image = (SquareImageView) findViewById(R.id.story_image);
        back_arrow = (ImageView) findViewById(R.id.back_arrow);


    }



}
