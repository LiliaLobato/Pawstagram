package com.example.lilialobato.pawstagramV3.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lilialobato.pawstagramV3.models.User;


public class SharedPrefrenceManger {

    private static final String FILENAME = "INSTAGRAMLOGIN";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String IMAGE = "image";
    private static final String ID = "id";

    private static SharedPrefrenceManger mSharedPrefreneManger;
    private static Context mContext;

    private SharedPrefrenceManger(Context context) {
        this.mContext = context;
    }


    public static synchronized SharedPrefrenceManger getInstance(Context context){

        if(mSharedPrefreneManger == null){
            mSharedPrefreneManger = new SharedPrefrenceManger(context);
        }
        return mSharedPrefreneManger;
    }


    public boolean isUserLogggedIn(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(USERNAME,null) != null){
            return true;
        }

        return false;
    }


    public void logUserOut(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }


    public User getUserData(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        User user = new User(sharedPreferences.getInt(ID,-1),sharedPreferences.getString(EMAIL,null)
                ,sharedPreferences.getString(USERNAME,null),sharedPreferences.getString(IMAGE,null));
        return user;
    }


}
