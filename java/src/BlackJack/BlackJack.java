package BlackJack;
import CardGame.Hand;
import CardGame.Card;
import Player.Player;
import CardGame.CardGame;

import java.util.HashMap;
import java.util.Map;

public class BlackJack extends CardGame {
    private int maxScore = 21;
    public int noOfCards = 2;

    public int getMaxScore(){
        return maxScore;
    }

    public void help(){
        userOutput.output("Please select one of the following options:");
        for (BlackJackActions action : BlackJackActions.values()) {
            userOutput.output(action.display());
        }
    }

    public BlackJackActions getPlayerAction(Player player){
        String userChoice = " ";
        BlackJackActions userAction;
        help();
        if (player.hasHand()) {
            userOutput.outputHand(player.getHand());
        }
        userChoice = userInput.getInputString();
        userAction = BlackJackActions.getAction(userChoice.substring(0,1).toUpperCase());
        userOutput.output("You chose " + userAction.display());
        return userAction;
    }

    private void userPlays(Player player){
        BlackJackActions userAction = BlackJackActions.PLAY;

        while (getScore(player.getHand()) <= maxScore && userAction != BlackJackActions.STICK){
            userAction = getPlayerAction(player);
            if (userAction == BlackJackActions.TWIST){
                userOutput.output("You twisted");
                player.getHand().add(deck.playACard());
            }

        }

    }

    public void computerPlays(Player player){
        while (getScore(player.getHand()) <= player.levelOfRisk){
            player.getHand().add(deck.playACard());
        }
    }

    public void play() {
        this.setNoOfCards(2);
        this.initiate();
        userPlays(this.players.get(0));
        for (int counter=1; counter < players.size();counter++){
            computerPlays(players.get(counter));
        }
        determineWinner();
    }

    public void determineWinner(){
        Integer winningScore = 0;
        String winningName = "";
        int currentScore = 0;
        Hand winningHand = new Hand();
        for (Player player : players){
            currentScore = getScore(player.getHand());
            if (currentScore <= maxScore && currentScore > winningScore){
                winningName = player.getName();
                winningScore = currentScore;
                winningHand = player.getHand();
            } else if ( currentScore > maxScore){
                userOutput.output(player.getName() + " you are bust");
            }
        }
        userOutput.output("The winner is " + winningName);
        userOutput.outputHand(winningHand);

    }


    public int getScore(Hand hand){
        int score = 0;
        for (Card card: hand.getHandOfCards()){
            score += card.getRank().getValue();
        }
        return score;
    }

    public static void main(String[ ] args) {
        BlackJack blackJack = new BlackJack();
        blackJack.play();
    }


}
