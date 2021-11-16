package CardGame;


import CardGame.Card;

import java.util.*;

public class Hand{
    private ArrayList<Card> handOfCards;


    public Hand(){
        this.handOfCards = new ArrayList<Card>();
    }

    public Hand(String[] listOfCards){
        this.handOfCards = new ArrayList<Card>();
        for (String card : listOfCards){
            this.handOfCards.add(Card.createCard(card));
        }
    }

    public Hand(String listOfCards){
        this.handOfCards = new ArrayList<Card>();
        for (String card : listOfCards.split(",")){
            this.handOfCards.add(Card.createCard(card));
        }
    }

    public ArrayList<Card> getHandOfCards(){
        return handOfCards;
    }

    public Card getFirstCard() {
        return handOfCards.get(0);
    }

    public Boolean playACard(Card card){
        return handOfCards.remove(card);
    }


    public Card playACard(String cardShortCode){
        Card card;
        if (cardShortCode.length() == 1) {
            int indexOfCard = Integer.parseInt(cardShortCode);
            card = playACard(indexOfCard);
        } else {
            card = Card.createCard(cardShortCode);
            if (!playACard(card)) {
                card = null;
            }
        }
        return card;
    }

    public Card playACard(){
        return handOfCards.remove(handOfCards.size()-1);
    }

    public Card playACard(int userCard){
        return handOfCards.remove(userCard);
    }

    public void add(Card card){
        handOfCards.add(card);
    }

    public void set(int index, Card card){
        handOfCards.set(index,card);
    }

    public Hand copy(){
        Hand newHand = new Hand();
        for (Card card : getHandOfCards()){
            newHand.add(card);
        }
        return newHand;
    }

    public boolean isEmpty(){
        boolean result = false;
        if (handOfCards.size() == 0 ){
            result = true;
        }
        return result;
    }

    public void remove(Card card){
        handOfCards.remove(card);
    }

    public void clear(){
        handOfCards.clear();
    }

    public Integer size(){
        return handOfCards.size();
    }

    public String toString(){
        String display = "";
        String prefix = "";
        for (Card card : handOfCards){
            display += prefix + card;
            prefix = ", ";
        }
        return display;
    }

    public void sortHand(){
        Collections.sort(handOfCards, Comparator.comparing(Card::toString));
    }

    public void sortHandByFace(){

        try {
            Collections.sort(handOfCards, Comparator.comparing(Card::getRank));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static Hand createHand(String[] listOfCards){
        Hand hand = new Hand();
        for (String card : listOfCards){
            hand.add(Card.createCard(card));
        }
        return hand;
    }
}
