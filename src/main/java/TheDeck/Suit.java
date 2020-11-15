package TheDeck;

public enum Suit {

  HEART("Heart"),
  DIAMOND("Diamond"),
  SPADE("Spade"),
  CLUB("Club");

  private final String suit;

  Suit(String suit){ this.suit = suit; }

  public String getSuit() {
    return suit;
  }

}
