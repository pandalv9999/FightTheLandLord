package game;

import card.Card;
import card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The enum defines the several types for each player to play their cards.
 */

public enum CardsType {
  Solo, Pair, Trio, Bomb, Rocket,
  SoloChain, PairSisters, TrioChain,
  TrioKicksSolo, TrioKicksPair, AirplaneKicksSolo, AirplaneKicksPair,
  FourKicksDualSolo, FourKicksDualPair;

  /**
   * The method determines whether a given card play is Solo.
   * A Solo is a card play that contains only one cards, from 3 to higher Joker.
   * @param card The given card play.
   * @return True if the play is Solo.
   */

  public static boolean isSolo(List<Card> card) {
    return isSolo(card, null);
  }

  /**
   * The method determines whether a given card play is Solo.
   * A Solo is a card play that contains only one cards, from 3 to higher Joker.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is Solo.
   */

  public static boolean isSolo(List<Card> card, Watcher watcher) {
    if (card == null) {
      return false;
    }
    if (card.size() == 1) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(0));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is SoloChain.
   * A SoloChain is a card play consists 5 or more chaining of Solo card
   * Two and Jokers cannot chain with other cards.
   * @param card The given card play.
   * @return True if the play is SoloChain.
   */

  public static boolean isSoloChain(List<Card> card) {
    return isSoloChain(card, null);
  }

  /**
   * The method determines whether a given card play is SoloChain.
   * A SoloChain is a card play consists 5 or more chaining of Solo card
   * Two and Jokers cannot chain with other cards.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is SoloChain.
   */

  public static boolean isSoloChain(List<Card> card, Watcher watcher) {
    if (card == null || card.size() < 5 || card.size() > 12) {
      return false;
    }
    Collections.sort(card);
    Rank lastRank = null;
    for (Card curr : card) {
      if (curr.getRank() == Rank.Two) {
        return false;
      }
      if (lastRank == null) {
        lastRank = curr.getRank();
      } else if (curr.getRank().getRank() - 1 != lastRank.getRank()) {
        return false;
      } else {
        lastRank = curr.getRank();
      }
    }
    if (lastRank != null && lastRank.getRank() < 13) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(card.size() - 1));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is Pair
   * A Pair is a card play that consists of two cards, and they both belongs to the same rank.
   * A Pair of Joker is not a pair. It is a Rocket.
   * @param card The given card play.
   * @return True if the play is Pair.
   */

  public static boolean isPair(List<Card> card) {
    return isPair(card, null);
  }

  /**
   * The method determines whether a given card play is Pair
   * A Pair is a card play that consists of two cards, and they both belongs to the same rank.
   * A Pair of Joker is not a pair. It is a Rocket.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is Pair.
   */

  public static boolean isPair(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 2 || card.get(0).isJoker()) {
      return false;
    }
    if (card.get(0).getRank() == card.get(1).getRank()) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(0));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is PairSisters
   * A PairSister is a card play that consists of three or more consecutive pairs.
   * Two and Joker cannot chain with other cards.
   * @param card The given card play.
   * @return True if the play is PairSister.
   */

  public static boolean isPairSisters(List<Card> card) {
    return isPairSisters(card, null);
  }

  /**
   * The method determines whether a given card play is PairSisters
   * A PairSister is a card play that consists of three or more consecutive pairs.
   * Two and Joker cannot chain with other cards.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is PairSister.
   */

  public static boolean isPairSisters(List<Card> card, Watcher watcher) {
    if (card == null || card.size() < 6 || card.size() > 20 || card.size() % 2 != 0) {
      return false;
    }
    Collections.sort(card);
    Rank lastRank = card.get(0).getRank();
    for (int i = 1; i < card.size(); i++) {
      if (card.get(i).getRank() == Rank.Two) {
        return false;
      }
      if (i % 2 == 1) {
        if (card.get(i).getRank() != lastRank) {
          return false;
        }
      } else {
        if (card.get(i).getRank().getRank() - 1 != lastRank.getRank()) {
          return false;
        }
        lastRank = card.get(i).getRank();
      }
    }
    if (lastRank != null && lastRank.getRank() < 13) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(card.size() - 1));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is Trio
   * A Trio is a card play that consists of three cards, and they both belongs to the same rank.
   * @param card The given card play.
   * @return True if the play is Trio.
   */

  public static boolean isTrio(List<Card> card) {
    return isTrio(card ,null);
  }

  /**
   * The method determines whether a given card play is Trio
   * A Trio is a card play that consists of three cards, and they both belongs to the same rank.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is Trio.
   */

  public static boolean isTrio(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 3) {
      return false;
    }
    if (CardsType.isListContainsIdenticalElement(card)) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(0));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is TrioChain
   * A TrioChain is a card play that consists of two or more consecutive Trios.
   * Two cannot chain with other cards.
   * @param card The given card play.
   * @return True if the play is TrioChain.
   */

  public static boolean isTrioChain(List<Card> card) {
    return isTrioChain(card, null);
  }

  /**
   * The method determines whether a given card play is TrioChain
   * A TrioChain is a card play that consists of two or more consecutive Trios.
   * Two cannot chain with other cards.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is TrioChain.
   */

  public static boolean isTrioChain(List<Card> card, Watcher watcher) {
    if (card == null || card.size() < 6 || card.size() > 20 || card.size() % 3 != 0) {
      return false;
    }
    Collections.sort(card);
    Rank lastRank = card.get(0).getRank();
    for (int i = 1; i < card.size(); i++) {
      if (i % 3 != 0) {
        if (card.get(i).getRank() != lastRank) {
          return false;
        }
      } else {
        if (card.get(i).getRank().getRank() - 1 != lastRank.getRank()) {
          return false;
        }
        lastRank = card.get(i).getRank();
      }
    }
    if (lastRank != null && lastRank.getRank() < 13) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(card.size() - 1));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is TrioKicksSolo
   * A TrioKicksSolo is a card play that consists of a Trio, and one Solo card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @return True if the play is TrioKicksSolo.
   */

  public static boolean isTrioKicksSolo(List<Card> card) {
    return isTrioKicksSolo(card, null);
  }

  /**
   * The method determines whether a given card play is TrioKicksSolo
   * A TrioKicksSolo is a card play that consists of a Trio, and one Solo card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is TrioKicksSolo.
   */

  public static boolean isTrioKicksSolo(List<Card> card, Watcher watcher) { // Bugs!
    if (card == null || card.size() != 4) {
      return false;
    }
    Collections.sort(card);
    Card probe = card.get(0);
    List<Card> first = new ArrayList<>();
    List<Card> second = new ArrayList<>();
    for (Card curr : card) {
      if (curr.getRank() == probe.getRank()) {
        first.add(curr);
      } else {
        second.add(curr);
      }
    }
    if (!CardsType.isListContainsIdenticalElement(first)
            || !CardsType.isListContainsIdenticalElement(second)) {
      return false;
    }
    if ((first.size() == 1 && second.size() == 3) || (first.size() == 3 && second.size() == 1)) {
      if (watcher != null) {
        watcher.setPrimeCard(first.size() == 3 ? first.get(0) : second.get(0));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is TrioKicksPair
   * A TrioKicksPair is a card play that consists of a Trio, and one pair of card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @return True if the play is TrioKicksPair.
   */

  public static boolean isTrioKicksPair(List<Card> card) {
    return isTrioKicksPair(card, null);
  }

  /**
   * The method determines whether a given card play is TrioKicksPair
   * A TrioKicksPair is a card play that consists of a Trio, and one pair of card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is TrioKicksPair.
   */

  public static boolean isTrioKicksPair(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 5) {
      return false;
    }
    Collections.sort(card);
    Card probe = card.get(0);
    List<Card> first = new ArrayList<>();
    List<Card> second = new ArrayList<>();
    for (Card curr : card) {
      if (curr.getRank() == probe.getRank()) {
        first.add(curr);
      } else {
        second.add(curr);
      }
    }
    if (!CardsType.isListContainsIdenticalElement(first)
            || !CardsType.isListContainsIdenticalElement(second)) {
      return false;
    }
    if ((first.size() == 2 && second.size() == 3) || (first.size() == 3 && second.size() == 2)) {
      if (watcher != null) {
        watcher.setPrimeCard(first.size() == 3 ? first.get(0) : second.get(0));
      }
      return true;
    }
    return false;
  }

  /**
   * The method determines whether a given card play is AirplaneKicksSolo.
   * A AirplaneKicksSolo is a card play that consists of a Trio Chain, with equal numbers
   * (equal to the length of chain, i, e, 333444 can have two Solo kickers) Solo card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @return True if the play is AirplaneKicksSolo.
   */

  public static boolean isAirplaneKicksSolo(List<Card> card) {
    return isAirplaneKicksSolo(card, null);
  }

  /**
   * The method determines whether a given card play is AirplaneKicksSolo.
   * A AirplaneKicksSolo is a card play that consists of a Trio Chain, with equal numbers
   * (equal to the length of chain, i, e, 333444 can have two Solo kickers) Solo card as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is AirplaneKicksSolo.
   */

  public static boolean isAirplaneKicksSolo(List<Card> card, Watcher watcher) {
    if (card == null || card.size() % 4 != 0) {
      return false;
    }
    Map<Rank, List<Card>> occMap = new HashMap<>();
    for (Card curr : card) {
      if (!occMap.containsKey(curr.getRank())) {
        occMap.put(curr.getRank(), new ArrayList<>());
      }
      occMap.get(curr.getRank()).add(curr);
    }
    List<Card> primeList = new ArrayList<>();
    List<Card> kickerList = new ArrayList<>();
    for (Rank rank : occMap.keySet()) {
      List<Card> values = occMap.get(rank);
      if (values.size() >= 3) {
        for (int i = 0; i < 3; i++) {
          primeList.add(values.get(values.size() - 1));
          values.remove(values.size() - 1);
        }
      }
      kickerList.addAll(values);
    }
    if (kickerList.size() * 3 != primeList.size()) {
      return false;
    }
    return isTrioChain(primeList, watcher);
  }

  /**
   * The method determines whether a given card play is AirplaneKicksPair.
   * A AirplaneKicksPair is a card play that consists of a Trio Chain, with equal numbers
   * (equal to the length of chain, i, e, 333444 can have two Pair kickers) Pairs as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @return True if the play is AirplaneKicksPair.
   */

  public static boolean isAirplaneKicksPair(List<Card> card) {
    return isAirplaneKicksPair(card, null);
  }

  /**
   * The method determines whether a given card play is AirplaneKicksPair.
   * A AirplaneKicksPair is a card play that consists of a Trio Chain, with equal numbers
   * (equal to the length of chain, i, e, 333444 can have two Pair kickers) Pairs as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is AirplaneKicksPair.
   */

  public static boolean isAirplaneKicksPair(List<Card> card, Watcher watcher) {
    if (card == null || card.size() % 5 != 0) {
      return false;
    }
    Map<Rank, List<Card>> occMap = new HashMap<>();
    for (Card curr : card) {
      if (!occMap.containsKey(curr.getRank())) {
        occMap.put(curr.getRank(), new ArrayList<>());
      }
      occMap.get(curr.getRank()).add(curr);
    }
    List<Card> primeList = new ArrayList<>();
    List<Card> kickerList = new ArrayList<>();
    for (Rank rank : occMap.keySet()) {
      List<Card> values = occMap.get(rank);
      if (values.size() == 3) {
        for (int i = 0; i < 3; i++) {
          primeList.add(values.get(values.size() - 1));
          values.remove(values.size() - 1);
        }
      } else if (values.size() == 2) {
        if (isPair(values)) {
          kickerList.addAll(values);
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    if (kickerList.size() * 3 != primeList.size() * 2) {
      return false;
    }
    return isTrioChain(primeList, watcher);
  }

  /**
   * The method determines whether a given card play is Bomb.
   * A Bomb is a card play that consists of four cards, and they both belongs to the same rank.
   * A Bomb can be player after any types of play, except for a greater bomb or Rocket.
   * @param card The given card play.
   * @return True if the play is Bomb.
   */

  public static boolean isBomb(List<Card> card) {
    return isBomb(card, null);
  }

  /**
   * The method determines whether a given card play is Bomb.
   * A Bomb is a card play that consists of four cards, and they both belongs to the same rank.
   * A Bomb can be player after any types of play, except for a greater bomb or Rocket.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is Bomb.
   */

  public static boolean isBomb(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 4) {
      return false;
    }
    if (CardsType.isListContainsIdenticalElement(card)) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(0));
      }
    }
    return false;
  }

  /**
   * The method determines whether a given card play is FourKicksDualSolo.
   * A FourKicksDualSolo is a card play that consists of a Bomb, and Two Solo cards as kickers.
   * The two kickers can be of different rank or of the same rank.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * A FourKicksDualSolo is not a bomb.
   * @param card The given card play.
   * @return True if the play is FourKicksDualSolo.
   */

  public static boolean isFourKicksDualSolo(List<Card> card) {
    return isFourKicksDualSolo(card, null);
  }

  /**
   * The method determines whether a given card play is FourKicksDualSolo.
   * A FourKicksDualSolo is a card play that consists of a Bomb, and Two Solo cards as kickers.
   * The two kickers can be of different rank or of the same rank.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * A FourKicksDualSolo is not a bomb.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is FourKicksDualSolo.
   */

  public static boolean isFourKicksDualSolo(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 6) {
      return false;
    }
    Map<Rank, List<Card>> occMap = new HashMap<>();
    for (Card curr : card) {
      if (!occMap.containsKey(curr.getRank())) {
        occMap.put(curr.getRank(), new ArrayList<>());
      }
      occMap.get(curr.getRank()).add(curr);
    }
    List<Card> primeList = new ArrayList<>();
    List<Card> kickerList = new ArrayList<>();
    for (Rank rank : occMap.keySet()) {
      List<Card> values = occMap.get(rank);
      if (values.size() == 4) {
        primeList.addAll(values);
      } else if (values.size() == 1) {
        kickerList.addAll(values);
      } else {
        return false;
      }
    }
    if (primeList.size() != 4 || kickerList.size() != 2) {
      return false;
    }
    if (isListContainsIdenticalElement(primeList)) {
      if (watcher != null) {
        watcher.setPrimeCard(primeList.get(0));
      }
    }
    return false;
  }

  /**
   * The method determines whether a given card play is FourKicksDuaPair.
   * A FourKicksDualPair is a card play that consists of a Bomb, and Two Pair cards as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * A FourKicksDualPair is not a bomb.
   * @param card The given card play.
   * @return True if the play is FourKicksDualPair.
   */

  public static boolean isFourKicksDualPair(List<Card> card) {
    return isFourKicksDualPair(card, null);
  }

  /**
   * The method determines whether a given card play is FourKicksDuaPair.
   * A FourKicksDualPair is a card play that consists of a Bomb, and Two Pair cards as kickers.
   * A kickers is a card that attached to some specific prime card plays. It does not count for
   * comparision.
   * A FourKicksDualPair is not a bomb.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is FourKicksDualPair.
   */

  public static boolean isFourKicksDualPair(List<Card> card, Watcher watcher) {
    if (card == null || card.size() != 8) {
      return false;
    }
    Map<Rank, List<Card>> occMap = new HashMap<>();
    for (Card curr : card) {
      if (!occMap.containsKey(curr.getRank())) {
        occMap.put(curr.getRank(), new ArrayList<>());
      }
      occMap.get(curr.getRank()).add(curr);
    }
    List<Card> primeList = new ArrayList<>();
    List<Card> kickerList = new ArrayList<>();
    for (Rank rank : occMap.keySet()) {
      List<Card> values = occMap.get(rank);
      if (values.size() == 4) {
        primeList.addAll(values);
      } else if (values.size() == 2) {
        if (!isPair(values)) {
          return false;
        }
        kickerList.addAll(values);
      } else {
        return false;
      }
    }
    if (primeList.size() != 4 || kickerList.size() != 4) {
      return false;
    }
    if (isListContainsIdenticalElement(primeList)) {
      if (watcher != null) {
        watcher.setPrimeCard(primeList.get(0));
      }
    }
    return false;
  }

  /**
   * The method determines whether a given card play is Rocket.
   * A Rocket is a card play that consists of two Jokers: One lower Joker and One higher Joker.
   * A Rocket can be player after any types of play. It is the greatest card play in the game.
   * A Rocket is considered a bomb.
   * @param card The given card play.
   * @return True if the play is Rocket.
   */

  public static boolean isRocket(List<Card> card) {
    return isRocket(card, null);
  }

  /**
   * The method determines whether a given card play is Rocket.
   * A Rocket is a card play that consists of two Jokers: One lower Joker and One higher Joker.
   * A Rocket can be player after any types of play. It is the greatest card play in the game.
   * A Rocket is considered a bomb.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return True if the play is Rocket.
   */

  public static boolean isRocket(List<Card> card, Watcher watcher) {
    if (card == null) {
      return false;
    }
    if (card.size() == 2 && card.get(0).isJoker() && card.get(1).isJoker()) {
      if (watcher != null) {
        watcher.setPrimeCard(card.get(1));
      }
    }
    return false;
  }

  /**
   * The method help to determine the cardType of a given card play.
   * @param card The given card play.
   * @return CardType or null if the card play is not valid.
   */

  public static CardsType determineType(List<Card> card) {
    return determineType(card, null);
  }

  /**
   * The method help to determine the cardType of a given card play.
   * @param card The given card play.
   * @param watcher A auxiliary class to determine the prime card.
   * @return CardType or null if the card play is not valid.
   */

  public static CardsType determineType(List<Card> card, Watcher watcher) {
    if (isSolo(card, watcher)) {
      return CardsType.Solo;
    } else if (isPair(card, watcher)) {
      return CardsType.Pair;
    } else if (isTrio(card, watcher)) {
      return CardsType.Trio;
    } else if (isBomb(card, watcher)) {
      return CardsType.Bomb;
    } else if (isRocket(card, watcher)) {
      return CardsType.Rocket;
    } else if (isSoloChain(card, watcher)) {
      return CardsType.SoloChain;
    } else if (isPairSisters(card, watcher)) {
      return CardsType.PairSisters;
    } else if (isTrioChain(card, watcher)) {
      return CardsType.TrioChain;
    } else if (isTrioKicksSolo(card, watcher)) {
      return CardsType.TrioKicksSolo;
    } else if (isTrioKicksPair(card, watcher)) {
      return CardsType.TrioKicksPair;
    } else if (isAirplaneKicksSolo(card, watcher)) {
      return CardsType.AirplaneKicksSolo;
    } else if (isAirplaneKicksPair(card, watcher)) {
      return CardsType.AirplaneKicksPair;
    } else if (isFourKicksDualSolo(card, watcher)) {
      return CardsType.FourKicksDualSolo;
    } else if (isFourKicksDualPair(card, watcher)) {
      return CardsType.FourKicksDualPair;
    } else {
      return null;
    }
  }

  private static boolean isListContainsIdenticalElement(List<Card> list) {
    if (list == null || list.size() == 0) {
      return false;
    } else if (list.size() == 1) {
      return true;
    }
    Card prob = list.get(0);
    for (Card curr : list) {
      if (curr.getRank() != prob.getRank()) {
        return false;
      }
    }
    return true;
  }
}

/**
 * The class is a helper class that tries to record down the prime card of any hands.
 * A prime card is the card in a hands of specific card type that use to compare with other hand of
 * the same card type.
 */

class Watcher {

  private Card primeCard = null;

  void setPrimeCard(Card card) {
    this.primeCard = card;
  }

  Card getPrimeCard() {
    return primeCard;
  }
}
