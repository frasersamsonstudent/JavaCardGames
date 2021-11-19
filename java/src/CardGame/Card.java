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

    public Card(String card){
        this.suit = Suit.getSuit(card.substring(0,1));
        this.rank = CardRank.getCardRank(card.substring(1,card.length()));
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

}
