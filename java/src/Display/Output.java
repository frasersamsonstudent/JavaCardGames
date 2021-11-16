package Display;
import CardGame.Hand;
import java.util.ArrayList;

public interface Output {
    public void output(String display);
    public void output(int display);
    public void output(ArrayList<String> displayOutput);
    public void outputHand(Hand hand);
    public void outputHands(ArrayList<Hand> hands);
}
