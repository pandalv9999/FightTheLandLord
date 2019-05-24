package card;

public enum Suit {
  Diamond(1) {
    @Override
    public String toString() {
      return "♦";
    }
  },

  Club(2) {
    @Override
    public String toString() {
      return "♣";
    }
  },

  Heart(3) {
    @Override
    public String toString() {
      return "♥";
    }
  },

  Spade(4) {
    @Override
    public String toString() {
      return "♠";
    }

  },

  Joker(5) {
    @Override
    public String toString() {
      return "JOKER";
    }
  };

  private final int rank;

  Suit(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return this.rank;
  }

}

