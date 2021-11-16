package CardGame;

import java.util.Collections;
import java.util.HashMap;

public class Deck{
    public int numberOfCards = 52;
    private static Deck uniqueDeck;
    private static Hand deckOfCards;
    private static HashMap< int[] , Card> cardHashMap;

    private Deck(){
        deckOfCards = new Hand();
        generateDeck();
        shuffleDeck();
    }

    private static Hand generateDeck() {
        deckOfCards.clear();
        for (Suit suit: Suit.values()){
            for (CardRank rank: CardRank.values()){
                Card card = new Card(suit,rank);
                deckOfCards.add(card);
            }
        }
        return deckOfCards;
    }

    public static Deck getInstance (){
        if (uniqueDeck == null){
            uniqueDeck = new Deck();
            generateDeck();
        }

        return uniqueDeck;


    }

    public int getNumberOfCards(){
        return deckOfCards.size();
    }

    public Card playACard() {
        if (deckOfCards.size() == 0) {
            generateDeck();
            shuffleDeck();
        }
        return deckOfCards.playACard();
    }

    public Hand shuffleDeck(){
        Collections.shuffle(deckOfCards.getHandOfCards());
        return deckOfCards;
    }

    public Hand getDeck(){
        return deckOfCards;
    }

    public static void main(String[ ] args) {
        Deck deck = new Deck();
        System.out.println(deck.shuffleDeck().toString());
    }
}


