package Person;

import TheDeck.Card;

import java.util.ArrayList;
import java.util.Collection;


public class Dealer extends Person {

  private ArrayList<Card> cards = new ArrayList<Card>();

  public Dealer(String name, double wallet){
    super(name, wallet);
  }

  public void dealtCard(int i, Card card) {
    cards.add(card);
  }

  public ArrayList<Card> returnCards() {
    ArrayList<Card> returningCards = new ArrayList<Card>();
    for (Card card : cards){
      returningCards.add(card);
    }
    cards.clear();
    return returningCards;
  }

  public ArrayList<Card> getHand() { return cards; }

  public int getScore(int handNo){
    ArrayList<Integer> possibleScores = new ArrayList<Integer>();
    possibleScores.add(0);
    for (Card card : getHand()){
      for (int i = 0 ; i < possibleScores.size() ; i++){
        possibleScores.set(i, possibleScores.get(i) + card.getPrimValue());
      }
      if (card.getName().equals("Ace")){
        possibleScores.add(possibleScores.get(possibleScores.size()-1) - 10);
      }
    }
    int bestScore = 0;
    for (int score : possibleScores){
      if (score <= 21){
        bestScore = score;
        break;
      }
    }
    return bestScore;
  }
}
