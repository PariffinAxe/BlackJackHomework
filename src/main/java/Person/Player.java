package Person;

import TheDeck.Card;

import java.util.ArrayList;

public class Player extends Person {

  private ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
  private double currentBet = 0.0;

  public Player(String name, double wallet){
    super(name, wallet);
    for (int i = 0 ; i < 4 ; i++){
      ArrayList<Card> hand = new ArrayList<Card>();
      hands.add(hand);
    }
  }

  public int getNoHands() { return hands.size(); }

  public ArrayList<Card> getHand(int i) {
    return hands.get(i);
  }

  public double getCurrentBet() {
    return currentBet;
  }

  public void setCurrentBet(double currentBet) {
    this.currentBet = currentBet;
  }

  public void dealtCard(int i, Card card) {
    hands.get(i).add(card);
  }

  public ArrayList<Card> returnCards() {
    ArrayList<Card> returningCards = new ArrayList<Card>();
    for (ArrayList<Card> hand : hands){
      for (Card card : hand){
        returningCards.add(card);
      }
      hand.clear();
    }
    return returningCards;
  }

  public int getScore(int handNo){
    ArrayList<Integer> possibleScores = new ArrayList<Integer>();
    possibleScores.add(0);
    for (Card card : getHand(handNo)){
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
