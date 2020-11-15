package TheDeck;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Deck {

  private ArrayList<Card> cards = new ArrayList<Card>();

  // For testing purposes
  private ArrayList<Card> allCards = new ArrayList<Card>();


  public int getSize() {
    return cards.size();
  }

  public void addDeck() {
    Suit[] suits = Suit.values();
    Value[] values = Value.values();
    for (Suit suit : suits){
      for (Value value : values){
        Card card = new Card(suit, value);
        cards.add(card);
        allCards.add(card);
      }
    }
  }

  public void shuffleDeck() {
    ArrayList<Card> shuffledCards = new ArrayList<Card>();

    int index;
    int cardsLeft = cards.size();

    while (cardsLeft != 0){
      index = (int) Math.floor(Math.random() * cardsLeft);
      shuffledCards.add(cards.get(index));
      cards.remove(index);
      cardsLeft --;
    }

    this.cards = shuffledCards;
  }

  public ArrayList<Card> getAllCards() { return allCards; }

  public ArrayList<Card> getCards() { return cards; }

  public Card dealCard() { return cards.remove(0); }

  public void addCard(Card card) { cards.add(card); }
}
