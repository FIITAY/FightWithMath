package itay.finci.org.fightwithmath;



import java.io.FileInputStream;

public class ScoreManager {
    private static ScoreManager ourInstance = new ScoreManager();

    public static ScoreManager getInstance() {
        return ourInstance;
    }
    private long score;

    private ScoreManager() {
        score = 0;
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
}
