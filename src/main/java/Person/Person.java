package Person;

import TheDeck.Card;

import java.util.ArrayList;

public abstract class Person {

  private String name;
  private double wallet;

  public Person(String name, double wallet) {
    this.name = name;
    this.wallet = wallet;
  }

  public String getName(){ return name; }

  public double getWallet() { return wallet; }

  public void increaseWallet(double amount){ wallet += amount; }

  public abstract void dealtCard(int i, Card card);

  public abstract ArrayList<Card> returnCards();

  public abstract int getScore(int handNo);

}
