package com.example.charl.bandapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity
{
    EditText userName,userPass,userConfirmPass;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        userName=(EditText)findViewById(R.id.uNameSignUp);
        userPass=(EditText)findViewById(R.id.pwdSignUp);
        userConfirmPass=(EditText)findViewById(R.id.pwdSignUpConfirm);

        btnCreateAccount=(Button)findViewById(R.id.btnSignUp);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String uName= userName.getText().toString();
                String uPwd = userPass.getText().toString();
                String confirmPassword=userConfirmPass.getText().toString();


                if(uName.equals("")||uPwd.equals("")||confirmPassword.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field is Empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!uPwd.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    loginDataBaseAdapter.insertEntry(uName, uPwd);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}