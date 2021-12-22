package BlackJack;

import CardGame.Hand;
import Display.TestInput;
import Display.TestOutput;
import Player.Player;
import Player.PlayerType;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AdapterDesignTests {
    private class BlackJackGame {
        BlackJack gameInstance;
        TestInput input;
        TestOutput output;

        public BlackJackGame(BlackJack gameInstance, TestInput input, TestOutput output) {
            this.gameInstance = gameInstance;
            this.input = input;
            this.output = output;
        }
    }

    private BlackJackGame getGameInstanceForTest() {
        BlackJack gameInstance = new BlackJack();
        TestInput input = new TestInput();
        TestOutput output = new TestOutput();

        gameInstance.setUserInput(input);
        gameInstance.setUserOutput(output);

        return new BlackJackGame(gameInstance, input, output);
    }


    @org.junit.jupiter.api.Test
    public void help() {
        BlackJackGame gameInstanceForTest = getGameInstanceForTest();
        BlackJack game = gameInstanceForTest.gameInstance;
        TestOutput output = gameInstanceForTest.output;

        // Generate help and output it
        game.help();

        // Check output matches expected value
        String helpMessage = output.getOutputValue();
        assertEquals("Please select one of the following options\n" +
                "Twist\n" +
                "Stick\n" +
                "Play New Game\n" +
                "End\n", helpMessage);
    }

    @org.junit.jupiter.api.Test
    public void outputHand() {
        BlackJackGame gameInstanceForTest = getGameInstanceForTest();
        BlackJack game = gameInstanceForTest.gameInstance;
        TestOutput output = gameInstanceForTest.output;

        // Create a hand and output it
        String cards = "H4,H8,S3,CA,DQ";
        game.outputHand(new Hand(cards));

        // Check each outputted card matches expected card
        String outputValueWithoutWhitespace = output.getOutputValue().replaceAll("\\s", "");
        assertEquals(cards, outputValueWithoutWhitespace);
    }

    @org.junit.jupiter.api.Test
    public void getPlayerAction() {
        // Initialise game
        BlackJackGame gameInstanceForTest = getGameInstanceForTest();
        BlackJack game = gameInstanceForTest.gameInstance;
        TestOutput output = gameInstanceForTest.output;
        TestInput input = gameInstanceForTest.input;

        // Initialise set of possible actions
        Map<String, String> actions = Map.of(
                "T", "Twist",
                "S", "Stick",
                "P", "Play New Game",
                "E", "End"
        );

        // Select each action and verify output
        for(Map.Entry<String, String> entry : actions.entrySet()) {
            // Get key and value
            String key = entry.getKey();
            String value = entry.getValue();

            // Create player to get actions for
            Player genericPlayer = new Player(PlayerType.USER, "player", 0);

            // Call getPlayerAction function for the current action
            input.addStringValue(key);
            game.getPlayerAction(genericPlayer);

            // Check help output
            assertEquals("Please select one of the following options\n" +
                    "Twist\n" +
                    "Stick\n" +
                    "Play New Game\n" +
                    "End\n", output.getOutputValue());

            // Check that confirmation of action selection is outputted
            assertEquals("You chose " + value, output.getOutputValue());
        }

    }

    @org.junit.jupiter.api.Test
    public void displayPlayer() {
        // Initialise game
        BlackJackGame gameInstanceForTest = getGameInstanceForTest();
        BlackJack game = gameInstanceForTest.gameInstance;
        TestOutput output = gameInstanceForTest.output;

        // Create player
        PlayerType playerType = PlayerType.USER;
        String playerName = "User name", cards = "H4, H8, S3, CA, DQ";
        int levelOfRisk = 0;
        Player testPlayer = new Player(playerType, playerName, levelOfRisk);

        // Set player hand
        Hand playerHand = new Hand(cards.replaceAll("\\s", ""));
        testPlayer.setHand(playerHand);

        // Output player and check result matches expected
        String info = "Player";
        game.displayPlayer(testPlayer, info);
        assertEquals(info + " name is " + playerName, output.getOutputValue());
        assertEquals(info + " score is " + 36, output.getOutputValue());
        assertEquals(info + " hand is " + cards, output.getOutputValue());
    }

    @org.junit.jupiter.api.Test
    public void initiatePlayers() {
        // Initialise game
        BlackJackGame gameInstanceForTest = getGameInstanceForTest();
        BlackJack game = gameInstanceForTest.gameInstance;
        TestInput input = gameInstanceForTest.input;

        input.addIntegerValue(4);
        input.addStringValue("Player name");

        assertEquals(3, game.initiatePlayers().size());
    }

}
