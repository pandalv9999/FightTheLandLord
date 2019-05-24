package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class implement a specific deck.
 * There is only one instance of deck is required, so singleton pattern is applied.
 */

public class DeckImpl implements Deck {
  private static DeckImpl ourInstance = new DeckImpl();

  private final List<Card> deck;
  private int dealIndex = 0;

  public static DeckImpl getInstance() {
    return ourInstance;
  }

  private DeckImpl() {
    deck = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      if (suit == Suit.Joker) {
        continue;
      }
      for (Rank rank : Rank.values()) {
        if (rank == Rank.LowerJoker || rank == Rank.HigherJoker) {
          continue;
        }
        deck.add(new Card(suit, rank));
      }
    }
    deck.add(new Card(Suit.Joker, Rank.LowerJoker));
    deck.add(new Card(Suit.Joker, Rank.HigherJoker));
  }

  @Override
  public Card dealCard() {
    return remainCards() == 0 ? null : deck.get(dealIndex++);
  }

  @Override
  public void shuffle() { Collections.shuffle(deck); }

  @Override
  public void reset() {
    shuffle();
    dealIndex = 0;
  }

  @Override
  public List<List<Card>> dealCardForThreePlayer() {
    List<List<Card>> result = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Card> currentPlayer = new ArrayList<>();
      for (int j = 0; j < 17; j++) {
        currentPlayer.add(dealCard());
      }
      Collections.sort(currentPlayer);
      result.add(currentPlayer);
    }
    List<Card> landLord = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      landLord.add(dealCard());
    }
    Collections.sort(landLord);
    result.add(landLord);
    return result;
  }

  private int remainCards() { return deck.size() - dealIndex; }

}


