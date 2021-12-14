package Display;

import CardGame.Hand;

import java.util.ArrayList;
import java.util.List;

public class TestOutput implements Output {
    List<String> outputValues = new ArrayList<>();

    @Override
    public void output(String display) {
        outputValues.add(display);
    }

    @Override
    public void output(int number) {
        outputValues.add(String.valueOf(number));
    }

    @Override
    public void output(ArrayList<String> displayOutput) {
        for(String string : displayOutput) {
            outputValues.add(string);
        }
    }

    @Override
    public void outputHand(Hand hand) {
        outputValues.add(hand.toString());
    }

    @Override
    public void outputHands(ArrayList<Hand> hands) {
        for (Hand hand : hands){
            outputValues.add(hand.toString());
        }
    }

    public String getOutputValue() {
        return outputValues.remove(0);
    }
}
