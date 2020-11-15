package TheDeck;

public class Card {

  private Suit suit;
  private Value value;

  public Card(Suit suit, Value value){
    this.suit = suit;
    this.value = value;
  }

  public String getSuit() { return suit.getSuit(); }


  public int getPrimValue() { return value.getPrimValue(); }

  public int getSecondValue() { return value.getSecondValue(); }

  public String getName() { return value.getName(); }
}
