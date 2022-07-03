
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cards {

    /**
     * @param player
     * @param discardPile
     * @param drawPile Allows a human player to pick up and discard cards
     */
    public static void humanMove(Player player, Deque<Card> discardPile, ArrayList<Card> drawPile) {
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        if (discardPile.isEmpty()) {
            player.showHand();
            System.out.println("The discard pile is currently empty -- you must draw a card");
            player.draw(drawPile);
            player.showHand();
            System.out.println("Which one do you want to discard?");
            int choice = 0;
            while (!valid) {
                try {
                    choice = in.nextInt();
                    if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
                        valid = true;
                    } else {
                        System.out.println("You must discard a card from your hand");
                        player.showHand();
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("You must discard a card from your hand");
                    player.showHand();
                    System.out.println("Which one do you want to discard?");
                    in.next();
                }//catches and then clears incorrect scanner input
            }// continually asks for input while the input given is not 1 2 3 4 or 5, once a valid input is given valid is set to true
            player.discard(choice, discardPile); 
        } else {
            player.showHand();
            System.out.println("The top card in the discard pile is " + discardPile.peek());
            System.out.println("Do you want to pick up the " + discardPile.peek() + "(1)" + " or draw a card (2)");
            int choice = 0;
            while (!valid) {
                try {
                    choice = in.nextInt();
                    if (choice == 1 || choice == 2) {
                        valid = true;
                    } else {
                        System.out.println("Do you want to pick up the " + discardPile.peek() + "(1)" + " or draw a card (2)");
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Do you want to pick up the " + discardPile.peek() + "(1)" + " or draw a card (2)");
                    in.next();
                }//catches and then clears incorrect scanner input
            }// continually asks for input while the input given is not 1 or 2, once a valid input is given valid is set to true
            if (choice == 1) {
                player.draw(discardPile);
            }// if the players chooses one they draw from the discardpile
            if (choice == 2) {
                if (drawPile.isEmpty()) {
                    drawPile.addAll(discardPile);
                    Collections.shuffle(drawPile);
                    // shuffles new draw pile
                    player.draw(drawPile);
                } else {
                    player.draw(drawPile);
                }
            }// if the player chooses two then they draw from the draw pile if the drawpile is empty the discardpile becomes the drawpile 
            player.showHand();
            System.out.println("Which one do you want to discard?");
            while (valid) {
                try {
                    choice = in.nextInt();
                    if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5) {
                        valid = false;
                    } else {
                        System.out.println("You must discard a card from your hand");
                        player.showHand();
                        System.out.println("Which one do you want to discard?");
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("You must discard a card from your hand");
                    player.showHand();
                    in.next();
                }//catches and then clears incorrect scanner input
            }// continually asks for input while the input given is not 1 2 3 4 or 5, once a valid input is given valid is set to false
            player.discard(choice, discardPile); 
        }// if the discard pile is empty they automatically draw from the draw pile otherwise they can draw from either the draw pile or discard pile
        if (player.winner()) {
            player.showHand();
            System.exit(0);
        }// the winning player is outputed along with their hand and then the game is exited

    }

    /**
     *
     * @param computer
     * @param discardPile
     * @param drawPile Automates a turn for a computer player
     */
    public static void computerMove(Player computer, Deque<Card> discardPile, ArrayList<Card> drawPile) {
        if (discardPile.isEmpty()) {
            System.out.println(computer.getPlayerType() + " " + computer.getPlayerNumber() + " The discard pile is empty I will draw a new card");
            computer.computerDraw(drawPile);
            computer.computerDiscard(discardPile); 
         } else {
            if (computer.getHand().get(0).getRank().equals(discardPile.peek().getRank()) || computer.getHand().get(1).getRank().equals(discardPile.peek().getRank()) || computer.getHand().get(2).getRank().equals(discardPile.peek().getRank()) || computer.getHand().get(3).getRank().equals(discardPile.peek().getRank())) {
                computer.computerDraw(discardPile);
                // if the card on top of the discard pile matches one of their cards they pick it up
            } else if (Math.random() < .4) {
                computer.computerDraw(discardPile);
                // if the random is below .4 they draw a card from the discard pile, to avoid the discard pile stagnating
            } else {
                if (drawPile.isEmpty()) {
                    drawPile.addAll(discardPile);
                    Collections.shuffle(drawPile);
                    System.out.println(computer.getPlayerType() + " " + computer.getPlayerNumber() + " I will draw a new card");
                    computer.computerDraw(drawPile);
                } else {
                    System.out.println(computer.getPlayerType() + " " + computer.getPlayerNumber() + " I will draw a new card");
                    computer.computerDraw(drawPile);
                }// if the draw pile is empty the discard pile becomes the new draw pile, then they draw a card from the new draw pile otherwise they just 
                // draw a card from the draw pile
            }
            computer.computerDiscard(discardPile); 
        } // if the discard pile is empty they automatically draw from the draw pile otherwise they can draw from either the draw pile or discard pile
        if (computer.winner()) {
            computer.showHand();
            System.exit(0);
        }// the winning player is outputed along with their hand and then the game is exited
    }

    /**
     * @param players
     * @param discardPile
     * @param drawPile
     * @param index Starting at the given index every player, human and
     * computer, takes a turn until a winner has been found
     */
    public static void game(ArrayList<Player> players, Deque<Card> discardPile, ArrayList<Card> drawPile, int index) {
        for (int i = index; i < players.size(); i++) {
            if (players.get(i) instanceof Computer) {
                computerMove(players.get(i), discardPile, drawPile);
            } else {
                humanMove(players.get(i), discardPile, drawPile);
            }
        }
        for (int i = 0; i < index; i++) {
            if (players.get(i) instanceof Computer) {
                computerMove(players.get(i), discardPile, drawPile);
            } else {
                humanMove(players.get(i), discardPile, drawPile);
            }
        }
    }// if the player object is a computer then it performs a computers move otherwise the player object performs a human players move

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Card> drawPile = deck.getCards();
        Collections.shuffle(drawPile);
        // creates a drawpile then shuffles it
        ArrayList<Player> players = new ArrayList<>();
        Deque<Card> discardPile = new ArrayDeque<>();
        Scanner in = new Scanner(System.in);
        System.out.println("How many players? 1-4 players required");
        boolean valid = false;
        int numPlayers = 0;
        while (!valid) {
            try {
                numPlayers = in.nextInt();

                if (numPlayers <= 4 && numPlayers != 0) {
                    valid = true;
                } else {
                    System.out.println("How many players? 1-4 players required.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("How many players? 1-4 players required.");
                in.next();
            }//catches and then clears incorrect scanner input
        }// continually asks for input while the input given is not 1 2 3 or 4, once a valid input is given valid is set to false
        for (int i = 0; i < 4; i++) {
            if (i < numPlayers) {
                players.add(new Human("Player", i + 1));
            } else {
                players.add(new Computer("Computer", i + 1));
            }

        }// creates the players for the game, adds the indicated number of human players and then adds the remaining players as computers

        players.forEach(player -> {
            player.setHand(deck.deal(drawPile));
        });
        // deals four cards into each players hand
        Random random = new Random();
        int index = random.nextInt(4);
        // index to decide which player goes first
        while (true) {
            game(players, discardPile, drawPile, index);
        }
    }
}
