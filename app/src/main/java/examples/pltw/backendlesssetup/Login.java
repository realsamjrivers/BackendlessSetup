package examples.pltw.backendlesssetup;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import dmax.dialog.SpotsDialog;

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
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.equals("") || password.equals(""))
        {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressDialog = new SpotsDialog(Login.this, R.style.Custom);
            ProgressDialog.show();

        Backendless.UserService.login(username, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {

                Toast.makeText(Login.this, backendlessuser.getEmail() + "Successful login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, ContactList.class);
                startActivity(intent);
                intent.putExtra("user", backendlessUser.getEmail());
                progressDialog.dismiss();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(Login.this, "Error: " + BackendlessFault.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }



    public void btnCreateAccount(View v)
    {
        Intent intent = new Intent(Login.this, CreateAccount.class);
        startActivity(intent);
    }
    public void btnResetPassword(View v)
    {
        if (connectionAvailable())
        {
            LayoutInflater inflater = getLayoutInflater();
            final View view = inflater.inflate(R.layout.password_reset, null);

            etMail = (EditText) view.findViewById(R.id.etMail);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Reset password");
            dialog.setView(view);
            dialog.setIcon(R.minimap.reset);

            dialog.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.setNegativeButton("CANCEL", new DialogInterface().OnClickListener) {
                    }
                }
                        @Override
                                public void onClick(DialogInterface dialog, int which){

                                Login.this.progressDialog = new SpotsDialog(Login.this, R.style.Custom);
                                Login.this.progressDialog.setTitle("Busy sending reset instructions... please...wait");
                                Login.this.progressDialog.show();

                                Backendless.UserService.restorePassword(etMail.getText().toString().trim(), new AsyncCallback<Void>() {
                                    @Override
                                    public void handleResponse(Void response) {
                                        Toast.makeText(Login.this, "Reset instructions has been sent to" +
                                                etMail.getText().toString().trim(), Toast.LENGTH_SHORT.show());
                                        Login.this.progressDialog.dismiss();
                                    }

                                    @Override
                                    public void handleFault(BackendlessFault backendlessFault) {

                                        Toast.makeText(Login.this, "Error:" + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                                        Login.this.progressDialog.dismiss();

                                    }
                                });

                        }


            });

            dialog.show();
        }
    }



    private boolean connectionAvailable()
    {
        boolean connected = false;

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                connected = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//               // connected to the mobile provider's data plan
                connected = true;
            } else {
                // not connected to the internet
                connected = false;

            }

            return connected;


        }


        return connected;
    }
















}




