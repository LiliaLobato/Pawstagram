package com.example.lilialobato.pawstagramV3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.helper.SquareImageView;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

public class SingleStoryActivity extends AppCompatActivity {

    SquareImageView story_image;
    TextView image_title;
    ImageView back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_story);


        story_image = (SquareImageView) findViewById(R.id.story_image);
        image_title = (TextView) findViewById(R.id.image_title);
        back_arrow = (ImageView) findViewById(R.id.back_arrow);



        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profileIntent;

                     profileIntent = new Intent(SingleStoryActivity.this,MainActivity.class);

                startActivity(profileIntent);
            }
        });


    }
}
