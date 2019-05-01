package com.example.lilialobato.pawstagramV3;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lilialobato.pawstagramV3.authentication.LoginActivity;
import com.example.lilialobato.pawstagramV3.fragments.CameraFragment;
import com.example.lilialobato.pawstagramV3.fragments.HomeFragment;
import com.example.lilialobato.pawstagramV3.fragments.LikesFragment;
import com.example.lilialobato.pawstagramV3.fragments.ProfieFragment;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;
import com.example.lilialobato.pawstagramV3.models.User;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionDrawerToggle;
    NavigationView mNavigationView;
    User user;
    String mImageProfile,mEmail,mUsername ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //layout variables
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mNavigationView = findViewById(R.id.main_nav_view);


        mActionDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionDrawerToggle);
        mActionDrawerToggle.syncState();

        setSupportActionBar((Toolbar)findViewById(R.id.my_toolbar));
        getSupportActionBar().setTitle("Pawstagram");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);


         user = SharedPrefrenceManger.getInstance(this).getUserData();



        Log.i("oncreate","create");


        //Default fragment to be displayed
        changeFragmentDisplay(R.id.home);

        //listener for navigation view
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                  changeFragmentDisplay(item.getItemId());
                  return true;
            }
        });


    }


    private void changeFragmentDisplay(int item){

        Fragment fragment = null;

        if (item == R.id.home) {
            fragment = new HomeFragment();


        } else if (item == R.id.search) {
            startActivity(new Intent(MainActivity.this,SearchActivity.class));


        } else if (item == R.id.profile) {
            fragment = new ProfieFragment();



       } else if (item == R.id.likes) {
        fragment = new LikesFragment();


        } else if (item == R.id.camera) {
            fragment = new CameraFragment();



       } else if (item == R.id.log_out) {

            logUserOutIFTheyWant();

        } else {
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
        }

        //hide naviagtion drawer
        mDrawerLayout.closeDrawer(Gravity.START);

        if(fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_fragment_content,fragment);
            ft.commit();
        }


    }


    private void logUserOutIFTheyWant(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPrefrenceManger sharedPrefrenceManger = SharedPrefrenceManger.getInstance(getApplicationContext());
                sharedPrefrenceManger.logUserOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                dialog.dismiss();


            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
            }
        });

        builder.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mActionDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }else if(item.getItemId() == R.id.settings){
            Intent settingsIntent = new Intent(this,SettingsActivity.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    }
