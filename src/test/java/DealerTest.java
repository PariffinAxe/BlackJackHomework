import Person.Dealer;
import TheDeck.Card;
import TheDeck.Suit;
import TheDeck.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DealerTest {

  private Dealer dealer;
  private Card card1;
  private Card card2;

  @Before
  public void before(){
    dealer = new Dealer("Bob", 1000.0);
    card1 = new Card(Suit.HEART, Value.ACE);
    card2 = new Card(Suit.SPADE, Value.JACK);
  }

  @Test
  public void hasName(){ assertEquals("Bob", dealer.getName()); }

  @Test
  public void hasWallet(){ assertEquals(1000.0, dealer.getWallet(), 0.01); }

  @Test
  public void hasHand(){ assertEquals(0, dealer.getHand().size()); }

  @Test
  public void canBeDealtCard(){
    dealer.dealtCard(0, card1);
    dealer.getHand().size();
  }

  @Test
  public void canReturnCards(){
    dealer.dealtCard(0, card1);
    dealer.dealtCard(0, card2);
    ArrayList<Card> cards = dealer.returnCards();
    assertEquals(0, dealer.getHand().size());
    assertEquals("Ace", cards.get(0).getName());
    assertEquals("Spade", cards.get(1).getSuit());
  }

  @Test
  public void canGetScore(){
    dealer.dealtCard(0, card1);
    dealer.dealtCard(0, card2);
    assertEquals(21, dealer.getScore(0));
  }

  @Test
  public void canGoBust(){
    dealer.dealtCard(0, card1);
    dealer.dealtCard(0, card2);
    dealer.dealtCard(0, card1);
    dealer.dealtCard(0, card2);
    assertEquals(0, dealer.getScore(0));
  }

  @Test
  public void canGetScoreWithHardAce(){
    dealer.dealtCard(0, card1);
    dealer.dealtCard(0, card2);
    dealer.dealtCard(0, card1);
    assertEquals(12, dealer.getScore(0));
  }
}
