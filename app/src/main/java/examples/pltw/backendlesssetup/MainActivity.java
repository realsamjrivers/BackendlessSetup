package examples.pltw.backendlesssetup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnReset, btnRegister, btnValidate, btnLogout, btnData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnData = (Button) findViewById(R.id.btnData);
        btnValidate = (Button) findViewById(R.id.btnValidate);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnReset = (Button) findViewById(R.id.btnReset);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BackendlessUser user = new BackendlessUser();
                user.setProperty("email", "johndoe@gmail.com");
                user.setProperty("name", "John");
                user.setProperty("surname", "Doe");
                user.setPassword("123");

                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(MainActivity.this, "New user registered successfully!", Toast.LENGTH_SHORT).show();
                        //dialog.dismiss()
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.UserService.login("johndoe@gmail.com", "123", new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(MainActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                }, true);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.UserService.restorePassword("johndoe@gmail.com", new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(MainActivity.this, "Reset instructions sent to your email inbox.", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(MainActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userObjectId = UserIdStorageFactory.instance().getStorage().get();

                Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(MainActivity.this, response.getEmail() + " " + response.getProperty("surname"), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(MainActivity.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
                    @Override
                    public void handleResponse(Boolean response) {

                        if (response)
                        {
                            Toast.makeText(MainActivity.this, "User authenticated", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                    }
                });



            }
        });




        Person person = new Person();
        person.setName("John");
        person.setSurname("Doe");

        Backendless.Data.of(Person.class).save(person, new AsyncCallback<Person>() {
            @Override
            public void handleResponse(Person response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
