package com.example.charl.bandapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by charl on 1/20/2017.
 */

public class StartupActivity extends Activity {

    Button btnSignIn, btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_screen);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v ){
                Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentSignUp);
            }

        });
    }


    public void signIn(View V){
        final Dialog dialog = new Dialog(StartupActivity.this);
        dialog.setContentView(R.layout.login_screen);
        dialog.setTitle("Login");

        final EditText userName = (EditText)dialog.findViewById(R.id.userLogin);
        final EditText userPwd = (EditText)dialog.findViewById(R.id.pwdLogin);
        Button btnSignIn = (Button)dialog.findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String uName = userName.getText().toString();
                String uPass = userPwd.getText().toString();

                String storedPass = loginDataBaseAdapter.getSingleEntry(uName);

                if(uPass.equals(storedPass)){
                    Toast.makeText(StartupActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(StartupActivity.this, MainActivity.class );
                    startActivity(intent);
                    dialog.dismiss();

                }
                else
                {
                    Toast.makeText(StartupActivity.this, "Username and Password do not match...kill yourself", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}
