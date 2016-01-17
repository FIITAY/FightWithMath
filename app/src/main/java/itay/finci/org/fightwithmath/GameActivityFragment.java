package itay.finci.org.fightwithmath;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameActivityFragment extends Fragment {
    private TextView timerView;
    private long timersec;
    private CountDownTimer coutdown;
    public GameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        return view;
    }
}
