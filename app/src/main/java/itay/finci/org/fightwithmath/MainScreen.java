package itay.finci.org.fightwithmath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MainScreen extends AppCompatActivity  {
    private static final String FILE_NAME="score.txt";
    // Client used to interact with Google API
    // request codes we use when invoking an external activity
    final String TAG = "Fwm";
    private GoogleApiClient mGoogleApiClient;
    TextView tHello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tHello = (TextView) findViewById(R.id.tHello);

        TextView tmScore = (TextView) findViewById(R.id.mtScore);
        tmScore.setText("" + ScoreManager.getInstance().getScore());
        // Create the Google API Client with access to Plus and Games
        mGoogleApiClient = ScoreManager.getInstance().getMgoogleApiClient();

        OnStartGMS(mGoogleApiClient);

        buttonintent();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }


    private  void OnStartGMS(GoogleApiClient gms){
        /**
         * getting player name and using it for text that handel the name
         */
        Player p = Games.Players.getCurrentPlayer(gms);
        String displayName;
        if (p == null) {
            Log.w(TAG, "mGamesClient.getCurrentPlayer() is NULL!");
            displayName = "???";
        } else {
            displayName = p.getDisplayName();
        }
        tHello.setText(getString(R.string.sHello) + displayName);
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

        //leaderbord buuton start
        Button bLeaderbord = (Button) findViewById(R.id.bLeaderbord);
        bLeaderbord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                        (String) getText(R.string.leaderboard_top_score)), 1);

            }
        });

        //credit button start
        Button bCredit = (Button) findViewById(R.id.bCredit);
        bCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Credit.class));

            }
        });
        //credit button end
        //play game button start
        Button bPlay = (Button) findViewById(R.id.bPlay);
        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });
        //play game button end
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
