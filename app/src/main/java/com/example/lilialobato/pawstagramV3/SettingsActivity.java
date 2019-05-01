package com.example.lilialobato.pawstagramV3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity {


    TextView edit_image,done_edit;
    EditText username_et,desc_et,email_et;
    CircleImageView profile_image;
    ImageView back_arrow;

    final int CHANGE_PROFILE_IMAGE = 1;
    ProgressDialog mProgrssDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        edit_image = findViewById(R.id.edit_image);
        done_edit = findViewById(R.id.done_edit);
        username_et =  findViewById(R.id.username_et);
        desc_et =  findViewById(R.id.desc_et);
        email_et =  findViewById(R.id.email_et);
        profile_image = findViewById(R.id.profile_image);
        back_arrow = findViewById(R.id.back_arrow);

         mProgrssDialog = new ProgressDialog(this);

        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewProfileImage();
            }
        });


        done_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });



    }




    private void updateUserData(){
        mProgrssDialog.setTitle("Update User Data");
        mProgrssDialog.setMessage("Please wait....");
        mProgrssDialog.show();
    }


    private void getNewProfileImage(){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHANGE_PROFILE_IMAGE);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CHANGE_PROFILE_IMAGE){

            if(resultCode == RESULT_OK){

                Uri uri = data.getData();
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    if(bitmap != null){
                        Toast.makeText(SettingsActivity.this,"Image",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SettingsActivity.this,"Couldn't get bitmap of image",Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }else if(resultCode == RESULT_CANCELED){


            }else{


            }

        }

    }


}
