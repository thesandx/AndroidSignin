package com.example.loginprefrence;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.loginprefrence.parse.ParseServer;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import com.example.loginprefrence.*;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    /**    Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .server("http://10.0.2.2:1337/parse")
                .build()
        );

        ParseObject login = new ParseObject("LoginDetails");
        login.put("score", 1337);
        login.put("playerName", "Sean Plott");
        login.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    Toast.makeText(getBaseContext(),"Save Success",Toast.LENGTH_LONG).show();

                    System.out.println("saved successfully");

                } else {

                    System.out.println("error while saving");

                }

            }
        });   **/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuoptions,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout:
                startLoginActivity();
                return  true;

            case R.id.saveEmail:
                System.out.println("clicked on saveEmail");
                ParseServer saveEmail = new ParseServer();
                saveEmail.setLogin("kumarjha.sandeep","1234");
                Toast.makeText(getBaseContext(),"Save Success",Toast.LENGTH_LONG).show();



            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void startLoginActivity(){
        //first set the prefrences false;
        logOutuser();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void logOutuser(){
        new PrefManager(this).logout();
    }
}
