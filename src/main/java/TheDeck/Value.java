package TheDeck;

public enum Value {

  ACE(11, 1, "Ace"),
  TWO(2, 2, "2"),
  THREE(3, 3, "3"),
  FOUR(4, 4, "4"),
  FIVE(5, 5, "5"),
  SIX(6, 6, "6"),
  SEVEN(7, 7, "7"),
  EIGHT(8, 8, "8"),
  NINE(9, 9, "9"),
  TEN(10, 10, "10"),
  JACK(10, 10, "Jack"),
  QUEEN(10, 10, "Queen"),
  KING(10, 10, "King");

  private final int primValue;
  private final int secondValue;
  private final String name;

  Value(int primValue, int secondValue, String name){
    this.primValue = primValue;
    this.secondValue = secondValue;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getPrimValue() {
    return primValue;
  }

  public int getSecondValue() {
    return secondValue;
  }
}
