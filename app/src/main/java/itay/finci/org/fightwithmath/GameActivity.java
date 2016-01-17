package itay.finci.org.fightwithmath;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_LONG;

public class GameActivity extends AppCompatActivity {
    CountDownTimer coutdown;
    TextView timerView;
    long timersec;
    Button bSolve;
    int colorred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timerView = (TextView) findViewById(R.id.timer1);
        coutdown = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText(""+millisUntilFinished / 1000);
                timersec = millisUntilFinished;
                if (millisUntilFinished/1000 <= 10){
                    timerView.setTextColor(Color.parseColor("#FF0808"));
                }
            }

            public void onFinish() {
                timerView.setText(R.string.timeout);
            }
        };
        coutdown.start();

        bSolve= (Button) findViewById(R.id.bSolve);
        bSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coutdown.cancel();
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.finishtimepart1)+ " "+ timersec /1000 +" "+ getString(R.string.finishtimepart2);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

}
