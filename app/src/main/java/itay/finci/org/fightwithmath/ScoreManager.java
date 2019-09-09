package itay.finci.org.fightwithmath;


import com.google.android.gms.common.api.GoogleApiClient;

public class ScoreManager {
    private static ScoreManager ourInstance = new ScoreManager();

    public static ScoreManager getInstance() {
        return ourInstance;
    }
    private long score;
    private GoogleApiClient mgoogleApiClient;

    private ScoreManager() {
        score = 0;
        mgoogleApiClient = null;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void addScore(long score) {
        this.score += score;
    }

    public GoogleApiClient getMgoogleApiClient() {
        return mgoogleApiClient;
    }

    public void setMgoogleApiClient(GoogleApiClient mgoogleApiClient) {
        this.mgoogleApiClient = mgoogleApiClient;
    }
}
