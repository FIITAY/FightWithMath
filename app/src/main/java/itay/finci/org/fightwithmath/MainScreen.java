package itay.finci.org.fightwithmath;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tmScore = (TextView) findViewById(R.id.mtScore);
        tmScore.setText("" + ScoreManager.getInstance().getScore());

        buttonintent();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void buttonintent(){
        /**
         * this function will take all the buttons and make the intent for them when they presed.
         */
        //credit button start
        Button bCredit = (Button) findViewById(R.id.bCredit);
        bCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Credit.class));

            }
        });
        //credit button end
        //playgame button start
        Button bPlay = (Button) findViewById(R.id.bPlay);
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });
        //playgame button end
        //shop button start

        Button bShop = (Button) findViewById(R.id.bShop);
        bShop.setVisibility(View.GONE);

        bShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShopActivity.class));
            }
        });
        //shop button end
        //purpose button start
        Button bHelp = (Button) findViewById(R.id.bHelp);
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PurposeActivity.class));
            }
        });
        //purpose button end


    }
}
