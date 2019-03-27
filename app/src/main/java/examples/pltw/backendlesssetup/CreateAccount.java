package examples.pltw.backendlesssetup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    EditText etName, etSurname, etMail, etPassword, etRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText)findViewById(R.id.etRePassword);
    }

    public void btnCreateAccount(View v)
    {
        
    }
}
