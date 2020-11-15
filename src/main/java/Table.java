import Person.Person;
import Person.Dealer;
import Person.Player;
import TheDeck.Card;
import TheDeck.Deck;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {

  private ArrayList<Person> people;
  private Deck deck;
  private double tableMinimum;
  private double tableMaximum;

  public Table(int noDecks, double tableMinimum, double tableMaximum){
    people = new ArrayList<Person>();
    deck = new Deck();
    for (int i = 0 ; i < noDecks ; i++){
      deck.addDeck();
    }
    deck.shuffleDeck();
    this.tableMinimum = tableMinimum;
    this.tableMaximum = tableMaximum;
  }

  public Deck getDeck() {
    return deck;
  }

  public ArrayList<Person> getPeople() {
    return people;
  }

  public double getTableMinimum() {
    return tableMinimum;
  }

  public double getTableMaximum() {
    return tableMaximum;
  }

  public void addDealer(Dealer dealer) {
    if (people.size() == 0){
      people.add(dealer);
    } else {
      System.out.println("This table already has a dealer : " + people.get(0).getName());
    }
  }

  public void addPlayer(Player player) {
    if (people.size() > 0) {
      people.add(player);
      player.setCurrentBet(tableMinimum);
    } else {
      System.out.println("Please provide a dealer before players can sit at this table");
    }
  }

  public void setBet(Player player, double bet){
    if (bet > tableMaximum){
      player.setCurrentBet(tableMaximum);
    } else if (bet < tableMinimum){
      player.setCurrentBet(tableMinimum);
    } else {
      player.setCurrentBet(bet);
    }
  }

  public void addPerson(Person person) {
    if (person instanceof Dealer){
      Dealer dealer = (Dealer) person;
      addDealer(dealer);
    } else {
      Player player = (Player) person;
      addPlayer(player);
    }
  }

  public void newRound(){
    if (people.size() > 1){
      for (Person person : people) {
        ArrayList<Card> cards = person.returnCards();
        for (Card card : cards) {
          deck.addCard(card);
        }
      }
      deck.shuffleDeck();
      for (int i = 0; i < 2; i++) {
        for (Person person : people) {
          person.dealtCard(0, deck.dealCard());
        }
      }
    } else {
      System.out.println("Please wait until players join the table before playing");
    }
  }

  public Boolean checkIfCanSplit(Player player, int handNo){
    if (player.getHand(3).size() == 0){
      if (player.getHand(handNo).size() == 2 && player.getHand(handNo).get(0).getName() == player.getHand(handNo).get(1).getName()){
        return true;
      }
    }
    return false;
  }

  public void splitHand(Player player, int handNo){
    if (checkIfCanSplit(player, handNo)){
      player.getHand(handNo + 1).add(player.getHand(handNo).get(1));
      player.getHand(handNo).remove(1);
      player.dealtCard(handNo, deck.dealCard());
      player.dealtCard(handNo + 1, deck.dealCard());
    } else {
      System.out.println("Cant split");
    }
  }

  public Boolean canTwist(Player player, int handNo){
    ArrayList<Integer> possibleScores = new ArrayList<Integer>();
    possibleScores.add(0);
    for (Card card : player.getHand(handNo)){
      for (int i = 0 ; i < possibleScores.size() ; i++){
        possibleScores.set(i, possibleScores.get(i) + card.getPrimValue());
      }
      if (card.getName().equals("Ace")){
        possibleScores.add(possibleScores.get(possibleScores.size()-1) - 10);
      }
    }
    for (int score : possibleScores){
      if (score < 21){
        return true;
      }
    }
    return false;
  }

  public void twist(Player player, int handNo){
    if (canTwist(player, handNo)){
      player.dealtCard(handNo, deck.dealCard());
    } else {
      System.out.println("cant twist");
    }
  }

  public void dealerTurn(){
    ArrayList<Integer> possibleScores = new ArrayList<Integer>();
    possibleScores.add(0);
    Dealer dealer = (Dealer) people.get(0);
    Boolean playing = true;
    while (playing){
      possibleScores.clear();
      possibleScores.add(0);
      for (Card card : dealer.getHand()) {
        for (int i = 0; i < possibleScores.size(); i++) {
          possibleScores.set(i, possibleScores.get(i) + card.getPrimValue());
        }
        if (card.getName().equals("Ace")) {
          possibleScores.add(possibleScores.get(possibleScores.size() - 1) - 10);
        }
      }
      for (int score : possibleScores){
        if (score >= 17 && score <= 21){
          playing = false;
        }
      }
      if (possibleScores.get(possibleScores.size() - 1) > 21){
        playing = false;
      }
      dealer.dealtCard(0, deck.dealCard());
    }
  }

  public HashMap<Player, HashMap<String, Integer>> checkWinners(){
    HashMap<Player, HashMap<String, Integer>> players = new HashMap<Player, HashMap<String, Integer>>();
    Dealer dealer = (Dealer) people.get(0);
    int dealerScore = dealer.getScore(0);
    for (int i = 1 ; i < people.size() ; i++){
      Player player = (Player) people.get(i);
      int wins = 0;
      int losses = 0;
      for (int j = 0 ; j < 4 ; j++){
        if (player.getScore(j) > dealerScore){
          wins++;
        } else if (player.getHand(j).size() > 0){
          losses++;
        }
      }
      HashMap<String, Integer> scores = new HashMap<String, Integer>();
      scores.put("Wins", wins);
      scores.put("Losses", losses);
      players.put(player, scores);
    }
    return players;
  }

}
