import Person.Dealer;
import Person.Player;
import TheDeck.Card;
import TheDeck.Suit;
import TheDeck.Value;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableTest {

  private Table table;
  private Dealer dealer;
  private Dealer dealer2;
  private Player mike;
  private Player joe;
  private Card card1;
  private Card card2;
  private Card card3;
  private Card card4;

  @Before
  public void before(){
    dealer = new Dealer("Bob", 1000.0);
    dealer2 = new Dealer("James", 1200.0);
    mike = new Player("Mike", 80.0);
    joe = new Player("Joe", 45.0);
    table = new Table(2, 5.0, 20.0);
    card1 = new Card(Suit.HEART, Value.EIGHT);
    card2 = new Card(Suit.SPADE, Value.EIGHT);
    card3 = new Card(Suit.CLUB, Value.NINE);
    card4 = new Card(Suit.CLUB, Value.ACE);
  }

  @Test
  public void hasCards(){ assertEquals(104, table.getDeck().getSize()); }

  @Test
  public void hasNoPlayers(){ assertEquals(0, table.getPeople().size()); }

  @Test
  public void canAddDealer(){
    table.addPerson(dealer);
    assertEquals(1, table.getPeople().size());
  }

  @Test
  public void cantAddMultipleDealers(){
    table.addPerson(dealer);
    table.addPerson(dealer2);
    assertEquals(1, table.getPeople().size());
  }

  @Test
  public void canAddPlayers(){
    table.addPerson(dealer);
    table.addPerson(mike);
    table.addPerson(joe);
    assertEquals(3, table.getPeople().size());
  }

  @Test
  public void cantAddPlayersToTableNoDealer(){
    table.addPerson(mike);
    assertEquals(0, table.getPeople().size());
  }

  @Test
  public void playerHasMinimumBet(){
    table.addPerson(dealer);
    table.addPerson(mike);
    assertEquals(5.0, mike.getCurrentBet(), 0.01);
  }

  @Test
  public void canChangeToBetween(){
    table.addPerson(dealer);
    table.addPerson(mike);
    table.setBet(mike, 10.0);
    assertEquals(10.0, mike.getCurrentBet(), 0.01);
  }

  @Test
  public void cantBetAboveMaximum(){
    table.addPerson(dealer);
    table.addPerson(mike);
    table.setBet(mike, 25.0);
    assertEquals(20.0, mike.getCurrentBet(), 0.01);
  }

  @Test
  public void cantBetBelowMinimum(){
    table.addPerson(dealer);
    table.addPerson(mike);
    table.setBet(mike, 3.0);
    assertEquals(5.0, mike.getCurrentBet(), 0.01);
  }

  @Test
  public void canDealNewRound(){
    table.addPerson(dealer);
    table.addPerson(mike);
    table.newRound();
    assertEquals(100, table.getDeck().getSize());
    assertEquals(2, dealer.getHand().size());
    assertEquals(2, mike.getHand(0).size());
    assertEquals(0, mike.getHand(2).size());
  }

  @Test
  public void cantDealIfOnlyDealer(){
    table.addPerson(dealer);
    table.newRound();
    assertEquals(104, table.getDeck().getSize());
  }

  @Test
  public void canSplitSameValue(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card2);
    table.splitHand(mike, 0);
    assertEquals(2, mike.getHand(0).size());
    assertEquals(2, mike.getHand(1).size());
  }

  @Test
  public void cantSplitIfDifferent(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card3);
    table.splitHand(mike, 0);
    assertEquals(2, mike.getHand(0).size());
    assertEquals(0, mike.getHand(1).size());
  }

  @Test
  public void cantSplitIfAlready4Hands(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card2);
    mike.dealtCard(1, card1);
    mike.dealtCard(1, card2);
    mike.dealtCard(2, card1);
    mike.dealtCard(2, card2);
    mike.dealtCard(3, card1);
    mike.dealtCard(3, card2);
    table.splitHand(mike, 2);
    assertEquals(4, mike.getNoHands());
  }

  @Test
  public void canTwist(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card2);
    table.twist(mike, 0);
    assertEquals(3, mike.getHand(0).size());
  }

  @Test
  public void cantTwistIfScoreTooHigh(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card2);
    mike.dealtCard(0, card3);
    table.twist(mike, 0);
    assertEquals(3, mike.getHand(0).size());
  }

  @Test
  public void canTwistOnSoft27(){
    mike.dealtCard(0, card1);
    mike.dealtCard(0, card2);
    mike.dealtCard(0, card4);
    table.twist(mike, 0);
    assertEquals(4, mike.getHand(0).size());
  }



}
