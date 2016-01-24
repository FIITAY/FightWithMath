package itay.finci.org.fightwithmath;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Credit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView t = (TextView) findViewById(R.id.tVersion);
        try {
            t.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
