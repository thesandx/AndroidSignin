package com.example.loginprefrence;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context context;

    PrefManager(Context context){
        this.context = context;
    }

    public void saveLoginDetails(String email,String password){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email",email);
        editor.putString("PassWord",password);
        editor.putBoolean("Logged",true);
        editor.apply();

    }

    public void logout(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Logged",false);
        editor.apply();



    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Logged",false);

    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Email", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty;
    }


}
