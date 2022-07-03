
public class Card {

    private final String rank;
    private final String suit;

    public Card(String suit, String rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    /**
     * @return to string for the cards that uses the usual card terminology ex
     * two of hearts
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
