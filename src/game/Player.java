package game;

import card.Card;
import java.util.List;

public interface Player {

  List<Card> getHands();

  List<Card> selectCards(List<Integer> indices);

  PlayerImpl.Plays playCards(PlayerImpl.Plays last, List<Card> selectedCard);
}
