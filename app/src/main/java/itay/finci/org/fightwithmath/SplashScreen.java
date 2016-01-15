package itay.finci.org.fightwithmath;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class SplashScreen extends AppCompatActivity {

    private Handler handler;
    private Runnable callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setBackgroundDrawableResource(R.drawable.splashscreen);
        handler = new Handler();
        callback = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainScreen.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        handler.postDelayed(callback, 2500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(callback);
    }

}
