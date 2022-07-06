import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    public static long dummy = 0;
    private static Random rng;
    // Used for random number generation

    public Chromosome() {
    } // No arg constructor for implementing other methods

    public Chromosome(Chromosome other) { // MC
        for (Item i : other) {
            this.add((i));
        }
    }// copy constructor

    /**
     * @param items Adds a copy of each of the items passed in to this
     *              Chromosome. Uses a random number to decide whether each item’s
     *              included
     *              field is set to true or false.
     */
    public Chromosome(ArrayList<Item> items) {

        rng = new Random();
        for (int i = 0; i < items.size(); i++) {
            int random = rng.nextInt(2);

            if (random == 1) {

                items.get(i).setIncluded(true);

            } else {
                items.get(i).setIncluded(false);
            }

            add(new Item(items.get(i)));

        } // if the random number that is generated is one then the included field is set
          // to true
          // otherwise the included field is set to false, a copy of the item is then
          // added to the chromosome

    }

    /**
     * @param other
     * @return Creates and returns a new child chromosome by performing
     *         crossover on this chromosome and the other one that is passed in
     *         (i.e.for
     *         each item, use a random number to decide which parent’s item should
     *         be
     *         copied and added to the child).
     */
    public Chromosome crossover(Chromosome other) {
        Chromosome child = new Chromosome();
        rng = new Random();
        for (int i = 0; i < this.size(); i++) {

            int random = rng.nextInt(2);
            if (random == 1) {
                child.add(new Item(this.get(i)));
            }
            if (random == 0) {
                child.add(new Item(other.get(i)));
            }

        } // creates a random number that is either 0 or 1 if the number is one a copy of
          // the chromosome's item is added if the number is 0
          // then a copy of the other chromosome's item is added
        return child;
    }

    /**
     * Performs the mutation operation on this chromosome (i.e. for each item in
     * this chromosome, use a random number to decide whether or not to flip
     * it’s included field from true to false or vice versa).
     */
    public void mutate() {
        rng = new Random();
        for (Item i : this) {

            int random = rng.nextInt(10);
            if (random == 1) {

                if (i.isIncluded()) {

                    i.setIncluded(false);

                } else {

                    i.setIncluded(true);

                }
            }

        } // if the random number is one then the included field is set to true otherwise
          // the included field is set to false
    }

    /**
     * @return Returns the fitness of this chromosome.If the sum of all of the
     *         included items’ weights are greater than 10, the fitness is zero.
     *         Otherwise, the fitness is equal to the sum of all of the included
     *         items’
     *         values.
     */
    public int getFitness() {
        dummy = 0;
        for (int i = 0; i < this.size() * 1000; i++) {
            dummy += i;
        }
        int fitness = 0;
        double totalWeight = 0;
        int totalValue = 0;
        for (Item i : this) {
            if (i.isIncluded()) {
                totalWeight = totalWeight + i.getWeight();
                totalValue = totalValue + i.getValue();
            }
        } // if the included value is true calculate the totalWeight and totalValue
        if (totalWeight > 10) {
            fitness = 0;
        } else {
            fitness = totalValue;
        } // if the totalWeight is above ten then fitness is 0 otherwise fitness is the
          // totalValue of the items

        return fitness;
    }

    /**
     * @param other Returns -1 if this chromosome’s fitness is greater than the
     *              other’s, +1 if this chromosome’s fitness is less than the other
     *              one’s,
     *              and 0 if their fitness is the same.
     */
    @Override
    public int compareTo(Chromosome other) {
        if (other.getFitness() < this.getFitness()) {
            return -1;
        } else if (other.getFitness() > this.getFitness()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @Overide Displays the name, weight and value of all items in this
     *          chromosome whose included value is true, followed by the fitness of
     *          this
     *          chromosome.
     */
    @Override
    public String toString() {
        return super.toString().replace("[", "").replace("]", "").replace(", ", "").replaceAll(",$", "") + " -> "
                + getFitness();
    }

}
