package com.example.loginprefrence;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassWord;
    private CheckBox rememberMe;
    private Button signinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Here we will validate saved preferences

            Log.d("MainActivity", "inside oncreate");

            setContentView(R.layout.activity_main);


            if(isLoggedin()){
                Log.d("MainActivity", "already login starting homeActivity");

                startHomeActivity();
            }

        Log.d("MainActivity", "not logged in enter username/password");

        mEmail = findViewById(R.id.email);

            mPassWord = findViewById(R.id.password);
            signinbtn = findViewById(R.id.sign_in_button);
            rememberMe = findViewById(R.id.rememberMe);

            signinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MainActivity", "inside onclick");

                    loginAttempt();

                }
            });

        }


    private void loginAttempt() {
        Log.d("MainActivity", "inside Login Attempt");

        //to show error,now set it to null by default
        mEmail.setError(null);
        mPassWord.setError(null);


        //get value from edittext
        String email = mEmail.getText().toString();
        String password = mPassWord.getText().toString();

        boolean cancel = false;

        View focusView = null;

        //check for valid password

        if (TextUtils.isEmpty(password)) {
            mPassWord.setError("can't left blank");

            focusView = mPassWord;
            cancel = true;

        }

        //check for valid email id

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("it can't be left empty");
            focusView = mEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {

            mEmail.setError("wrong email address");
            focusView = mEmail;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            //save data in local shared prefrences
           if (rememberMe.isChecked()) {
               Log.d("MainActivity", "saving login Address");

               saveLoginDetails(email, password,true);
            }

            startHomeActivity();
          //  Toast.makeText(MainActivity.this, "Signing in...", Toast.LENGTH_SHORT).show();

        }

    }

    private void startHomeActivity () {
        Log.d("MainActivity", "starting home Activity");


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveLoginDetails(String email, String password,Boolean logged) {
        Log.d("MainActivity", "saving login details");

        new PrefManager(this).saveLoginDetails(email, password);

    }

    private boolean isLoggedin() {
        Log.d("Thesandx","inside isloggedin");

      return new PrefManager(this).isLoggedIn();

     // Toast.makeText(MainActivity.this,email,Toast.LENGTH_SHORT).show();
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }

}
