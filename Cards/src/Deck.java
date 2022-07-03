
import java.util.ArrayList;

public class Deck {

    private final static String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private final static String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
    private final ArrayList<Card> cards = new ArrayList<Card>();

    public Deck() {
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * @param deck
     * @return Deals four cards into every players hand
     */
    public ArrayList<Card> deal(ArrayList<Card> deck) {
        ArrayList<Card> hand = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            hand.add(deck.remove(i));
        }
        System.out.println(hand);
        return hand;
    }

}
