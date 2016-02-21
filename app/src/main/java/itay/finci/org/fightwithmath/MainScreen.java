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

public class MainScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String FILE_NAME="score.txt";
    // Client used to interact with Google APIs
    private GoogleApiClient mGoogleApiClient;
    // request codes we use when invoking an external activity
    private static final int RC_RESOLVE = 5000;
    private static final int RC_UNUSED = 5001;
    private static final int RC_SIGN_IN = 9001;
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;
    final String TAG = "Fwm";
    TextView tHello;
    long leadbordscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        BufferedInputStream o;
        Scanner sc;
        try {
            String s = "" + ScoreManager.getInstance().getScore();
            byte[] arr = new byte[100];
            sc = new Scanner(new FileInputStream(new File(getFilesDir(), FILE_NAME)));
            int score = sc.nextInt();
            ScoreManager.getInstance().setScore(score);
            sc.close();
        }catch (FileNotFoundException e ){
            e.printStackTrace();
            ScoreManager.getInstance().setScore(0);
        }catch (NumberFormatException e) {
            e.printStackTrace();
            ScoreManager.getInstance().setScore(0);
        }

        TextView tmScore = (TextView) findViewById(R.id.mtScore);
        tmScore.setText("" + ScoreManager.getInstance().getScore());
        // Create the Google API Client with access to Plus and Games

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
        ScoreManager.getInstance().setMgoogleApiClient(mGoogleApiClient);
        tHello = (TextView) findViewById(R.id.tHello);
        buttonintent();
    }
    @Override
     protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart(): connecting");
        mGoogleApiClient.connect();


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

    private void loadScoreOfLeaderBoard() {
        Games.Leaderboards.loadCurrentPlayerLeaderboardScore(mGoogleApiClient, getString(R.string.leaderboard_top_score), LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC).setResultCallback(new ResultCallback<Leaderboards.LoadPlayerScoreResult>() {
            @Override
            public void onResult(final Leaderboards.LoadPlayerScoreResult scoreResult) {
                if (isScoreResultValid(scoreResult)) {
                    // here you can get the score like this
                      leadbordscore = scoreResult.getScore().getRawScore();
                }
            }
        });
    }
    private boolean isScoreResultValid(final Leaderboards.LoadPlayerScoreResult scoreResult) {
        return scoreResult != null && GamesStatusCodes.STATUS_OK == scoreResult.getStatus().getStatusCode() && scoreResult.getScore() != null;
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

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected(): connected to Google APIs");

        Player p = Games.Players.getCurrentPlayer(mGoogleApiClient);
        String displayName;
        if (p == null) {
            Log.w(TAG, "mGamesClient.getCurrentPlayer() is NULL!");
            displayName = "???";
        } else {
            displayName = p.getDisplayName();
        }
        tHello.setText(getString(R.string.sHello) + displayName);

        loadScoreOfLeaderBoard();

        if(leadbordscore > ScoreManager.getInstance().getScore()){
            ScoreManager.getInstance().setScore(leadbordscore);
            BufferedOutputStream o;
            try {
                o = new BufferedOutputStream(new FileOutputStream(new File(getFilesDir(),FILE_NAME)));
                String s = "" + ScoreManager.getInstance().getScore()+ "";
                o.write(s.getBytes());
                o.close();
            }catch (FileNotFoundException e ){

            }catch (IOException e){

            }
        }else{
            Games.Leaderboards.submitScore(mGoogleApiClient, (String) getText(R.string.leaderboard_top_score), ScoreManager.getInstance().getScore());
        }
    }
    @Override
      public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended(): attempting to connect");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            // already resolving
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, String.valueOf(R.string.signin_other_error))) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button

    }


}
