package examples.pltw.backendlesssetup;

import android.app.Application;

import com.backendless.Backendless;

public class BackendApplication extends Application {
    public static final String APPLICATION_ID = "45B5B7B9-EAFE-6134-FFB2-242D6A646D00";
    public static final String API_KEY = "80F3A634-410F-904C-FFA4-DA688D090E00";
    public static final String SERVER_URL = "https://api.backendless.com";



    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);


    }
}
