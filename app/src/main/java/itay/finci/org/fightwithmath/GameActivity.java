package itay.finci.org.fightwithmath;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

public class GameActivity extends AppCompatActivity {
    CountDownTimer coutdown;
    TextView timerView,tEquls;
    Button bSolve;
    EditText etAwnser;
    double answer,trueAnser;
    long timersec;
    int placeindata;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        timerView = (TextView) findViewById(R.id.timer1);
        etAwnser=(EditText) findViewById(R.id.etAwnser);
        tEquls =(TextView) findViewById(R.id.tEquls) ;

        //setup equles database
         Equition[] equs =  {
                new Equition("2(x+5)=14", 2),
                new Equition("3(x+6)=21", 1),
                new Equition("4(x+11)=56", 3),
                new Equition("9(x+2)=72", 6),
                new Equition("7(x+1)=49", 6),
                new Equition("3(x+1)=3", 0),
                new Equition("15(x+4)=60", 0),
                new Equition("4(x+2)=12", 1),
                new Equition("7(x+11)=91", 2),
                new Equition("5(x+3)=125", 22),
                new Equition("3(x-4)=9", 7),
                new Equition("5(x-1)=15", 4),
                new Equition("12(x-3)=24", 4),
                new Equition("7(x-2)=7", 3),
                new Equition("4(x-3)=20", 8),
                new Equition("9(x-1)=9", 2),
                new Equition("3(x-5)=0", 5),
                new Equition("43(x-8)=0", 8),
                new Equition("17(x—1)=17", 2)};
        //SETUP randomizer of with 1 to choose
        random = new Random();
        placeindata = random.nextInt(equs.length);
        //setup equles in tEquls
        tEquls.setText(equs[placeindata].getBody());
        //setup awnser in trueanser
        trueAnser= equs[placeindata].getAnswer();

        coutdown = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText(""+millisUntilFinished / 1000);
                timersec = millisUntilFinished;
                if (millisUntilFinished/1000 < 10){
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

                int duration = Toast.LENGTH_LONG;
                answer = Double.parseDouble(etAwnser.getText().toString());
                if(answer == trueAnser){
                    CharSequence text = getText(R.string.RIghtAwnser)+ " "+getText(R.string.RightAwnser2);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    CharSequence text = getText(R.string.falseawnser);
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }

}
