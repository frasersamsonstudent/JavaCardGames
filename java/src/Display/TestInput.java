package Display;

import java.util.ArrayList;
import java.util.List;

public class TestInput implements Input {
    List<String> stringValues = new ArrayList<>();
    List<Integer> integerValues = new ArrayList<>();

    @Override
    public String getInputString() {
        return stringValues.remove(0);
    }

    @Override
    public int getInputInt() {
        return integerValues.remove(0);
    }

    public void addStringValue(String s) {
        stringValues.add(s);
    }

    public void addIntegerValue(Integer num) {
        integerValues.add(num);
    }

}
