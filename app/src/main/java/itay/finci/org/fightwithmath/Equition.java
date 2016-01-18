package itay.finci.org.fightwithmath;

/**
 * Created by itay on 18/01/16.
 */
public class Equition {
    private String body;
    private  double answer;

    public Equition(String b, double a){
        body = b;
        answer = a;
    }

    public Equition() {

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }
}
