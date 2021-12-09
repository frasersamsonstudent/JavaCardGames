package CardGame;
import Display.ConsoleOutput;
import Display.ConsoleInput;
import Display.Output;
import Player.Player;
import Player.PlayerType;

import java.util.ArrayList;

public class CardGame {

    protected Integer noOfCards = 2;
    protected ConsoleInput userInput;
    protected Output userOutput;
    protected Deck deck;

    public void setNoOfCards(Integer noOfCards) {
        this.noOfCards = noOfCards;
    }

    public CardGame(String deckOverride){
        this.userInput = new ConsoleInput();
        this.userOutput = new ConsoleOutput();
        this.deck = new Deck(deckOverride);
    }
    public CardGame(){
        this.userInput = new ConsoleInput();
        this.userOutput = new ConsoleOutput();
        this.deck = new Deck();
    }

    protected Player createHumanPlayer(){
        String name = getHumanName();
        return new Player(PlayerType.USER,name,0);
    }

    protected String getHumanName() {
        userOutput.output("What is your name");
        String name = userInput.getInputString();
        return name;
    }

    protected void createComputerPlayers(Integer noOfPlayers, ArrayList<Player> players) {
        Player dealer = new Player(PlayerType.DEALER,"Dealer 1",17);
        players.add(dealer);
        noOfPlayers -= 1; //Remove the dealer
        for (int counter=2;counter < noOfPlayers;counter++){
            players.add(new Player(PlayerType.COMPUTER,"Comp" + counter,0));
        }
    }

    protected ArrayList<Player> initiatePlayers(){
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(createHumanPlayer());
        createComputerPlayers(getNumberOfPlayers(), players);
        return players;
    }

    protected int getNumberOfPlayers(){
        userOutput.output("How many players, minimum of two?");
        return userInput.getInputInt();
    }

    protected void dealCards(Deck deck, ArrayList<Player> players){
        boolean allCards = false;
        int noOfCards;
        if (this.noOfCards == 0) {
            noOfCards = (int) Math.floor(deck.size()/players.size());
            allCards = true;
        } else {
            noOfCards = this.noOfCards;
        }
        for (Player player: players){
            Hand hand = new Hand();
            hand = dealHand(hand,noOfCards, deck);
            player.setHand(hand);
        }
        if (allCards){
            for (Player player: players){
                if (deck.size() > 0){
                    player.getHand().add(deck.playACard());
                }
            }
        }
    }

    protected Hand dealHand(Hand hand, int noOfCards, Deck deck){

        for (int cardCounter=0;cardCounter<noOfCards;cardCounter++){
            if (deck.size() > 0) {
                hand.add(deck.playACard());
            }
        }
        return hand;
    }

    protected void playerPlaysHand(Player player, Deck deck){
        if (player.getPlayerType() == PlayerType.USER){
            userPlays(player, deck);
        } else {
            computerPlays(player, deck);
        }
    }

    protected void userPlays(Player player, Deck deck){

    }

    protected void computerPlays(Player player, Deck deck){

    }

    protected void play(){
        ArrayList<Player> players = initiatePlayers();
        setNoOfCards(noOfCards);
        dealCards(deck, players);
        Integer counterOfPlayers = 0;
        while (!playerHasWon(players.get(counterOfPlayers))){
            playerPlaysHand(players.get(counterOfPlayers), deck);
            counterOfPlayers = (counterOfPlayers + 1) % players.size();
        }
        Player winningPlayer = determineWinner(players);
        displayPlayer(winningPlayer, "Winner");
        if (winningPlayer.getPlayerType() != PlayerType.USER) {
            displayPlayer(players.get(0),"Your");
        }
    }

    protected Player determineWinner(ArrayList<Player> players){
        Integer winningScore = 0;
        Player winningPlayer = null;
        int currentScore = 0;
        for (Player player : players){
            currentScore = getScore(player.getHand());
            if (currentScore > winningScore) {
                winningScore = currentScore;
                winningPlayer = player;
            }
        }
        return winningPlayer;
    }

    protected void displayPlayer(Player player, String info){
        userOutput.output(info + " name is " + player.getName());
        userOutput.output(info + " score is " + getScore(player.getHand()));
        userOutput.output(info + " hand is " + player.getHand().toString());
    }

    protected int getScore(Hand hand){
        return 0;
    }

    protected boolean playerHasWon(Player player){
        return player.getWinner();
    }

    public static void main(String[ ] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
    }
}
