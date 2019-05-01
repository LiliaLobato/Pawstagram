package com.example.lilialobato.pawstagramV3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lilialobato.pawstagramV3.adapter.SearchListAdapter;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.models.User;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    EditText search_et;
    ListView search_results_lv;
    ArrayList<User> arrayListUsers;
    SearchListAdapter searchListAdapter;
    int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_et = (EditText) findViewById(R.id.search_et);
        search_results_lv = (ListView) findViewById(R.id.search_results_lv);

        arrayListUsers = new ArrayList<User>();
        searchListAdapter = new SearchListAdapter(SearchActivity.this, R.layout.user_single_item, arrayListUsers);
        search_results_lv.setAdapter(searchListAdapter);


        User user = SharedPrefrenceManger.getInstance(getApplicationContext()).getUserData();
        user_id = user.getId();


    }


}
