package com.example.lilialobato.pawstagramV3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.adapter.CommentListAdapter;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.models.Comment;
import com.example.lilialobato.pawstagramV3.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommentsActivity extends AppCompatActivity {

    ImageView back_arrow;
    ListView comment_lv;
    EditText comment_et;
    ImageButton comment_send_btn;
    ArrayList<Comment> arrayListComments;
    CommentListAdapter commentListAdapter;
    ProgressDialog mProgrssDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        back_arrow = (ImageView) findViewById(R.id.back_arrow);
        comment_lv = (ListView) findViewById(R.id.comment_lv);
        comment_et = (EditText) findViewById(R.id.comment_et);
        comment_send_btn = (ImageButton) findViewById(R.id.comment_send_btn);

        arrayListComments = new ArrayList<Comment>();
        commentListAdapter = new CommentListAdapter(getApplicationContext(),R.layout.comment_single_item,arrayListComments);
        comment_lv.setAdapter(commentListAdapter);

        mProgrssDialog = new ProgressDialog(this);
        mProgrssDialog.setTitle("News Feed");
        mProgrssDialog.setMessage("Updating News Feed....");


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToFeed();
            }
        });


    }

    private void goBackToFeed(){

        startActivity(new Intent(this,MainActivity.class));


    }
}
