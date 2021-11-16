package BlackJack;

import java.util.HashMap;
import java.util.Map;

public enum BlackJackActions {
    TWIST("Twist"),
    STICK("Stick"),
    PLAY("Play New Game"),
    END("End");

    private String fullName;
    private static final Map<String, BlackJackActions> lookup = new HashMap<String, BlackJackActions>();

    BlackJackActions(String fullName) {
        this.fullName = fullName;
    }

    static {
        for (BlackJackActions action : BlackJackActions.values()) {
            lookup.put(action.fullName.substring(0, 1), action);
        }
    }

    public String display() {
        return fullName;
    }

    public static BlackJackActions getAction(String shortCode) {
        return lookup.get(shortCode);
    }

}
