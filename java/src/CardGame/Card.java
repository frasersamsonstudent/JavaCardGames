package CardGame;

import CardGame.Suit;
import CardGame.CardRank;


public class Card {

    private Suit suit;

    private CardRank rank;

    public Card(Suit suit, CardRank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public CardRank getRank(){
        return rank;
    }

    public String displayCamelCase(){
        return suit.displayCamelCase() + " " + rank.displayCamelCase();
    }

    public String displayOf(){
        return rank.displayCamelCase() + " of " + suit.displayCamelCase();
    }

    public String toString(){
        return suit + rank.toString();
    }

    public static Card createCard(String shortCode) {
        Card card = new Card(Suit.getSuit(shortCode.substring(0,1)),CardRank.getCardRank(shortCode.substring(1,shortCode.length())));
        return card;
    }

}
