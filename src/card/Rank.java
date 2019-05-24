package card;

public enum Rank {
  Three(1), Four(2), Five(3), Six(4), Seven(5), Eight(6), Nine(7), Ten(8),
  Jack(9), Queen(10), King(11), Ace(12), Two(13), LowerJoker(14), HigherJoker(15);

  private final int rank;
  Rank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }
}
