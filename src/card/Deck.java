package card;

import java.util.List;

/**
 * The class defines a interface of deck we used to play Fight the LandLord.
 * A deck consists of 54 cards, including 52 regular card, A lower Joker, and a HigherJoker.
 */

public interface Deck {

  /**
   * The method deal the top card out.
   * @return The top card on the current remaining deck.
   */

  Card dealCard();

  /**
   * The method randomly permutate the given deck.
   */

  void shuffle();

  /**
   * The method reset the deck inorder to start a new game.
   */

  void reset();

  /**
   * The method deals cards for three player for a Fight the Landlord game.
   * Result is a list of lists of Cards.
   * The first, second, and third list, of length 17, are starting hands for three players.
   * The fourth list, of length 3, are the landlord cards. Those cards will only revealed when
   * One of the player bits the landlord successfully, and join that player's hand.
   * @return A Partition of the deck for a game.
   */

  List<List<Card>> dealCardForThreePlayer();

}
