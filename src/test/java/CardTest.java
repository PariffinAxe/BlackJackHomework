import TheDeck.Card;
import TheDeck.Suit;
import TheDeck.Value;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

  private Card card;

  @Before
  public void before(){ card = new Card(Suit.HEART, Value.ACE); }

  @Test
  public void hasSuit(){ assertEquals("Heart", card.getSuit()); }

  @Test
  public void hasPrimValue(){ assertEquals(11, card.getPrimValue()); }

  @Test
  public void hasSecondValue(){ assertEquals(1, card.getSecondValue()); }

  @Test
  public void hasValueName(){ assertEquals("Ace", card.getName()); }
}
