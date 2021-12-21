package BlackJack;

import CardGame.Deck;
import CardGame.Hand;
import Player.Player;
import Player.PlayerType;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {
    Hand hand = new Hand("C5,D3,S7");

    @org.junit.jupiter.api.Test
    void scoreHand() {
        BlackJack blackJack = new BlackJack();
        assertEquals(15, blackJack.getScore(hand));
    }

    @org.junit.jupiter.api.Test
    void determineWinner() {
        BlackJack blackJack = new BlackJack();

        // Create list of players and populate
        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(new Player(PlayerType.USER, "Player", 0));
        playersList.add(new Player(PlayerType.USER, "Player", 0));

        // Set players' hands
        Hand winningHand = new Hand("HA,HK");
        Hand losingHand = new Hand("QJ");
        playersList.get(0).setHand(winningHand);
        playersList.get(1).setHand(losingHand);

        // Check output matches expected value
        assertEquals(playersList.get(0), blackJack.determineWinner(playersList));
    }


}
