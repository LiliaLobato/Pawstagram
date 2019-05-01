package com.example.lilialobato.pawstagramV3.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilialobato.pawstagramV3.MainActivity;
import com.example.lilialobato.pawstagramV3.R;
import com.example.lilialobato.pawstagramV3.helper.SharedPrefrenceManger;

public class SignUpActivity extends AppCompatActivity {


    LinearLayout mLoginContainer;
    AnimationDrawable mAnimationDrawable;

    EditText email_et, username_et, password_et, password_confirm_et;
    Button sign_up_btn;
    TextView go_to_login_btn;
    ProgressDialog mProgrssDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        //Animation config
        mLoginContainer = (LinearLayout) findViewById(R.id.login_container);
        mAnimationDrawable = (AnimationDrawable) mLoginContainer.getBackground();
        mAnimationDrawable.setEnterFadeDuration(5000);
        mAnimationDrawable.setExitFadeDuration(2000);


        //login design varibales
        email_et = (EditText) findViewById(R.id.user_email);
        username_et = (EditText) findViewById(R.id.user_name);
        password_et = (EditText) findViewById(R.id.user_password);
        password_confirm_et = (EditText) findViewById(R.id.user_password_confirm);
        sign_up_btn = (Button) findViewById(R.id.sign_up_btn);
        go_to_login_btn = (TextView) findViewById(R.id.go_to_login_btn);
        mProgrssDialog = new ProgressDialog(this);


        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        go_to_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });


    }


    private void register() {


        mProgrssDialog.setTitle("Creating your account");
        mProgrssDialog.setMessage("Please wait....");
        mProgrssDialog.show();

        final String email = email_et.getText().toString();
        final String username = username_et.getText().toString();
        final String password = password_et.getText().toString();
        String password_confirm = password_confirm_et.getText().toString();


        if (!email.contains("@")) {
            email_et.setError("This is not a valid email");
            email_et.requestFocus();
            mProgrssDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            username_et.setError("Please fill in this field");
            username_et.requestFocus();
            mProgrssDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_et.setError("Please fill in this field");
            password_et.requestFocus();
            mProgrssDialog.dismiss();
            return;
        }

        if (TextUtils.isEmpty(password_confirm)) {
            password_confirm_et.setError("Please fill in this field");
            password_confirm_et.requestFocus();
            mProgrssDialog.dismiss();
            return;
        }

        if (!password.equals(password_confirm)) {
            password_et.setError("Password charachters don't match!");
            password_et.requestFocus();
            mProgrssDialog.dismiss();
            return;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mAnimationDrawable != null && !mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        boolean isUserLoggedIn = SharedPrefrenceManger.getInstance(getApplicationContext()).isUserLogggedIn();

        if (isUserLoggedIn) {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        } else {

        }
    }


}
