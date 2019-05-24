package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;

public class PlayerImpl implements Player {

  private final List<Card> hand;

  public PlayerImpl(List<Card> cards) {
    this.hand = cards;
  }

  @Override
  public List<Card> getHands() {
    return Collections.unmodifiableList(hand);
  }

  @Override
  public Plays playCards(Plays last, List<Card> selectedCard) {
    if (selectedCard == null || selectedCard.size() == 0) {
      return null;
    }
    Plays toPlay;
    boolean isValid = false;
    try {
      toPlay = new Plays(selectedCard);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Selected Card is not a valid play.");
    }
    if (toPlay.type == CardsType.Rocket) {
      isValid = true;
    } else if (toPlay.getType() == CardsType.Bomb) {

    }

    // other logic...


     else if (toPlay.getType() == last.getType() && toPlay.getSize() == last.getSize()
            && toPlay.compareTo(last) > 0) {
      isValid = true;
    }

    if (isValid) {
      removeCardsFromHand(selectedCard);
      return toPlay;
    } else {
      throw new IllegalArgumentException("Selected Card is not a valid play.");
    }

  }

  @Override
  public List<Card> selectCards(List<Integer> indices) {

    return null;
  }

  private void removeCardsFromHand(List<Card> selectedCard) {

  }

  static class Plays implements Comparable<Plays>{
    private final CardsType type;
    private final Card primeCard;
    private final List<Card> cards;
    private final int size;

    public Plays(List<Card> cards) {
      Watcher watcher = new Watcher();
      this.type = CardsType.determineType(cards, watcher);
      if (this.type == null) {
        throw new IllegalArgumentException("The given card play is not a valid play!");
      }
      this.primeCard = watcher.getPrimeCard();
      this.cards = new ArrayList<>(cards);
      this.size = cards.size();
    }

    public Card getPrimeCard() {
      return primeCard;
    }

    public int getSize() {
      return size;
    }

    public CardsType getType() {
      return type;
    }

    public List<Card> getCards() {
      return Collections.unmodifiableList(cards);
    }

    @Override
    public int compareTo(Plays other) {
      if (this.type != other.type) {
        throw new IllegalArgumentException("The two plays are not of the same type!");
      }
      return Integer.compare(this.primeCard.getRank().getRank(),
              other.primeCard.getRank().getRank());
    }
  }
}



