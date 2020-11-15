import Person.Player;
import TheDeck.Card;
import TheDeck.Suit;
import TheDeck.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

  private Player player;
  private Card card1;
  private Card card2;

  @Before
  public void before(){
    player = new Player("Mike", 100.0);
    card1 = new Card(Suit.HEART, Value.ACE);
    card2 = new Card(Suit.SPADE, Value.JACK);
  }

  @Test
  public void hasName(){ assertEquals("Mike", player.getName()); }

  @Test
  public void hasWallet(){ assertEquals(100.0, player.getWallet(), 0.01); }

  @Test
  public void hasHands(){ assertEquals(4, player.getNoHands()); }

  @Test
  public void hasCurrentBet(){ assertEquals(0.0, player.getCurrentBet(), 0.01);}

  @Test
  public void changeCurrentBet(){
    player.setCurrentBet(5.5);
    assertEquals(5.5, player.getCurrentBet(), 0.01);
  }

  @Test
  public void handsEmpty(){
    assertEquals(0, player.getHand(0).size());
    assertEquals(0, player.getHand(1).size());
    assertEquals(0, player.getHand(2).size());
    assertEquals(0, player.getHand(3).size());
  }

  @Test
  public void canBeDealtCard(){
    player.dealtCard(0, card1);
    player.dealtCard(3, card2);
    assertEquals("Heart", player.getHand(0).get(0).getSuit());
    assertEquals(10, player.getHand(3).get(0).getPrimValue());
    assertEquals(1, player.getHand(0).size());
    assertEquals(1, player.getHand(3).size());

  }

  @Test
  public void canReturnCards(){
    player.dealtCard(0, card1);
    player.dealtCard(3, card2);
    ArrayList<Card> cards = player.returnCards();
    assertEquals(0, player.getHand(0).size());
    assertEquals(0, player.getHand(3).size());
    assertEquals("Ace", cards.get(0).getName());
    assertEquals("Spade", cards.get(1).getSuit());
  }

  @Test
  public void canGetScore(){
    player.dealtCard(0, card1);
    player.dealtCard(0, card2);
    assertEquals(21, player.getScore(0));
  }

  @Test
  public void canGoBust(){
    player.dealtCard(0, card1);
    player.dealtCard(0, card2);
    player.dealtCard(0, card1);
    player.dealtCard(0, card2);
    assertEquals(0, player.getScore(0));
  }

  @Test
  public void canGetScoreWithHardAce(){
    player.dealtCard(0, card1);
    player.dealtCard(0, card2);
    player.dealtCard(0, card1);
    assertEquals(12, player.getScore(0));
  }




}
