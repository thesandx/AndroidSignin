package com.example.loginprefrence.parse;

import android.app.Application;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class ParseServer  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .server("http://10.0.2.2:1337/parse")
                .build()
        );

        System.out.println("parse initialized");


    }


    public void setLogin(String email,String password){
        System.out.println("Inside setLogin");
        ParseObject login = new ParseObject("LoginDetailsTest");
        login.put("Email",email);
        login.put("Password",password);

        System.out.println("Put all the data");

        login.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    System.out.println("saved successfully");

                } else {

                    System.out.println("error while saving");

                }

            }
        });


    }
}
