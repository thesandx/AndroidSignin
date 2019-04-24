package com.example.loginprefrence;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginprefrence.parse.ParseServer;


public class HomeActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button submitBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Email = findViewById(R.id.EmailEdit);
        Password = findViewById(R.id.PassEdit);
        submitBtn = findViewById(R.id.SubmitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAttempt();

                //Toast.makeText(HomeActivity.this,"Save Success",Toast.LENGTH_LONG).show();


            }
        });

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


            case R.id.Recyclerviewmenu:
                System.out.println("clicked on saveEmail");
                Intent intent = new Intent(this, UserListActivity.class);
                startActivity(intent);





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


    private void loginAttempt() {
        Log.d("HomeActivity", "inside Login Attempt");

        //to show error,now set it to null by default
        Email.setError(null);
        Password.setError(null);


        //get value from edittext
        String email = Email.getText().toString();
        String password = Password.getText().toString();

        boolean cancel = false;

        View focusView = null;


        //check for valid email id

        if (TextUtils.isEmpty(email)) {
            Email.setError("it can't be left empty");
            focusView = Email;
            cancel = true;
        }

        //check for valid password

        if (TextUtils.isEmpty(password)) {
            Email.setError("it can't be left empty");
            focusView = Email;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {

            System.out.println("Submit button clicked");
            ParseServer saveEmail = new ParseServer();
            saveEmail.setLogin(email,password);


            }


           // Toast.makeText(HomeActivity.this, "Saved", Toast.LENGTH_SHORT).show();

        }

    }
