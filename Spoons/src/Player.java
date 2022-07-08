import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

public class Player {

    private ArrayList<Card> hand;
    private final int playerNumber;
    private final String playerType;

    public Player(String playerType, int playerNumber) {
        this.playerType = playerType;
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerType() {
        return playerType;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * @param discardPile Discards a card from the computer players hand into
     *                    the discard pile
     */
    public void computerDiscard(Deque<Card> discardPile) {
        HashMap<String, Integer> numOfMatches = new HashMap<>();
        hand.forEach(card -> {
            if (numOfMatches.containsKey(card.getRank())) {
                int numOfRank = numOfMatches.get(card.getRank()) + 1;
                numOfMatches.put(card.getRank(), numOfRank);
            } else {
                numOfMatches.put(card.getRank(), 1);
            }
        }); // if its the first time that rank has been added then its added with a count of
            // 1, otherwise an int is created with the current number
        // of that rank and then that rank is put into the hash map with its new count
        int lowestOccurence = 5;
        // highest possible occurence is 4 so initial lowest is set to 5
        String discard = "";
        for (String rank : numOfMatches.keySet()) {
            if (numOfMatches.get(rank) <= lowestOccurence) {
                lowestOccurence = numOfMatches.get(rank);
                discard = rank;
            }
        } // finds the the lowest occuring rank and then sets the discard string equal to
          // that rank
        if (hand.get(0).getRank().equals(discard)) {
            System.out.println(" and I will discard the " + hand.get(0));
            discardPile.offer(hand.remove(0));
        } else if (hand.get(1).getRank().equals(discard)) {
            System.out.println(" and I will discard the " + hand.get(1));
            discardPile.offer(hand.remove(1));
        } else if (hand.get(2).getRank().equals(discard)) {
            System.out.println(" and I will discard the " + hand.get(2));
            discardPile.offer(hand.remove(2));
        } else if (hand.get(3).getRank().equals(discard)) {
            System.out.println(" and I will discard the " + hand.get(3));
            discardPile.offer(hand.remove(3));
        } else if (hand.get(4).getRank().equals(discard)) {
            System.out.println(" and I will discard the " + hand.get(4));
            discardPile.offer(hand.remove(4));
        }
        // finds the first card in the players hand that matches discard and then
        // discards that card
    }

    /**
     * @param discardPile Draws the top card from the discard pile and adds it
     *                    to the computer players hand
     */
    public void computerDraw(Deque<Card> discardPile) {
        Card card = discardPile.removeFirst();
        System.out.print(this.getPlayerType() + " " + this.getPlayerNumber() + " I will pick up the " + card);
        hand.add(card);
    }

    /**
     * @param drawPile Adds the first card from the draw pile into the computer
     *                 players hand
     */
    public void computerDraw(ArrayList<Card> drawPile) {
        Card card = drawPile.remove(0);
        hand.add(card);
    }

    /**
     * @param choice
     * @param discardPile Removes a card from the human players hand depending
     *                    on which card they choose to discard and then adds that
     *                    card to the
     *                    discard pile
     */
    public void discard(int choice, Deque<Card> discardPile) {
        System.out.println("You discarded " + hand.get(choice - 1));
        discardPile.offer(hand.remove(choice - 1));
    }

    /**
     * @param discardPile Draws the top card from the discard pile and adds it
     *                    to the human players hand
     */
    public void draw(Deque<Card> discardPile) {
        Card card = discardPile.removeFirst();
        System.out.println(this.getPlayerType() + " " + this.getPlayerNumber() + " you picked up the " + card);
        hand.add(card);
    }

    /**
     * @param drawPile Draws the first card from the draw pile and adds it to
     *                 the human players hand
     */
    public void draw(ArrayList<Card> drawPile) {
        Card card = drawPile.remove(0);
        System.out.println(this.getPlayerType() + " " + this.getPlayerNumber() + " you drew the " + card);
        hand.add(card);
    }

    /**
     * Shows the human player their hand
     */
    public void showHand() {
        if (this.winner()) {
            System.out.println(this.getPlayerType() + " " + this.getPlayerNumber() + " is the winner ");
        } else {
            System.out.println(this.getPlayerType() + " " + this.getPlayerNumber() + " Your cards are: ");
        } // message changes depending on whether or not the player has won
        for (int i = 0; i < this.hand.size(); i++) {
            System.out.println((i + 1) + " " + this.hand.get(i));
        }
    }

    /**
     * @return Checks the players hand to see if they have won, if all four
     *         cards have the same rank then true is returned otherwise false is
     *         returned
     */
    public boolean winner() {
        return hand.get(0).getRank().equals(hand.get(1).getRank())
                && hand.get(0).getRank().equals(hand.get(2).getRank())
                && hand.get(0).getRank().equals(hand.get(3).getRank());
    }

}

class Human extends Player {

    public Human(String playerType, int playerNumber) {
        super(playerType, playerNumber);
    }
}

class Computer extends Player {

    public Computer(String playerType, int playerNumber) {
        super(playerType, playerNumber);
    }
}
