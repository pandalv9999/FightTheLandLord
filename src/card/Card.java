package card;

/**
 * The class implements the basic unit of the game: card
 * A class can be compared to other card with specific order in the game. Therefore it is a
 * Comparable objects.
 */

public class Card implements Comparable<Card> {

  private final Suit suit;
  private final Rank rank;

  public Card(Suit suit, Rank rank) { // shall we guard for invalid combination?
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Get the suit of card.
   * @return Card's suit.
   */

  public Suit getSuit() { return suit; }

  /**
   * Get the rank of card.
   * @return Card's rank.
   */

  public Rank getRank() { return rank; }

  /**
   * Check if the card is Joker
   * @return True if the card is Joker, false otherwise.
   */

  public boolean isJoker() { return suit == Suit.Joker; }

  /**
   * Provides a human readable representation of the Card:
   * "Suit_Rank".
   * @return A String as described.
   */

  @Override
  public String toString() {
    return suit.toString() + "_" + rank.name();
  }

  /**
   * A compare method for other Card.
   * @param o Other card.
   * @return The compare result. -1 -> current card is smaller
   *                              1 -> other card is smaller
   *                              0 -> Impossible situation.
   */

  @Override
  public int compareTo(Card o) {
    if (rank.getRank() < o.rank.getRank()) {
      return -1;
    } else if (rank.getRank() > o.rank.getRank()) {
      return 1;
    } else {
      return Integer.compare(suit.getRank(), o.suit.getRank());
    }
  }
}
