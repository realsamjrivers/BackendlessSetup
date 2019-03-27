package examples.pltw.backendlesssetup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    EditText etUsername, etPassword;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }


    public void btnLogin(View v) {
    }

    {

    }
    public void btnCreateAccount(View v)
    {
        Intent intent = new Intent(Login.this, CreateAccount.class);
        startActivity(intent);
    }
    public void btnResetPassword(View v)
    {

    }



















}




