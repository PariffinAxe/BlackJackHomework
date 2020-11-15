import TheDeck.Deck;
import TheDeck.Card;
import TheDeck.Suit;
import TheDeck.Value;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeckTest {

  private Deck deck;
  private Card card;

  @Before
  public void before(){
    deck = new Deck();
    card = new Card(Suit.HEART, Value.ACE);
  }

  @Test
  public void emptyDeck(){ assertEquals(0, deck.getSize()); }

  @Test
  public void canAddCards(){
    deck.addDeck();
    assertEquals(52, deck.getSize());
  }

  @Test
  public void canShuffleCards(){
    deck.addDeck();
    deck.shuffleDeck();
    for (Card card : deck.getAllCards()){
      assertEquals(true, deck.getCards().contains(card));
    }
  }

  @Test
  public void canDealCard(){
    deck.addDeck();
    Card card = deck.dealCard();
    assertEquals(51, deck.getSize());
    assertEquals("Heart", card.getSuit());
    assertEquals(11, card.getPrimValue());
  }

  @Test
  public void canAddCard(){
    deck.addCard(card);
    assertEquals(1, deck.getSize());
  }


}
