package examples.pltw.backendlesssetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

public class Splash extends AppCompatActivity {

    TextView tvLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvLoading = (TextView) findViewById(R.id.tvLoading);

        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

            if (!aBoolean)
            {
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                Splash.this.finish();
            }
            else
            {
                tvLoading.setText("Busy logging you in... please wait...");

                String userObjectId = UserIdStorageFactory.instance.getStorage().get();

                Backendless.Data.of(BackendlessUser.class).findById(userObjectId, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {

                        Intent intent = new Intent(Splash.this, ContactList.class);
                        intent.putExtra("user", BackendlessUser.getEmail());
                        startActivity(intent);
                        Splash.this.finish();

                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Toast.makeText(Splash.this, "Error: " + backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {


                Toast.makeText(Splash.this, "Error: " + backendlessFault.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                Splash.this.finish();
            }
        });
    }
}
