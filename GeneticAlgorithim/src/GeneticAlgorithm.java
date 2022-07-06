import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GeneticAlgorithm extends Thread {

    private static final int POP_SIZE = 200;
    private static final int NUM_EPOCHS = 300;
    private static final int NUM_THREADS = 4;
    private final ArrayList<Chromosome> newPop = new ArrayList<>();
    private final ArrayList<Chromosome> population;
    private final int numToAdd;

    public GeneticAlgorithm(ArrayList<Chromosome> population, int numToAdd) {
        this.population = population;
        this.numToAdd = numToAdd;
        for (int i = 0; i < population.size(); i++) {
            newPop.add(new Chromosome(population.get(i)));
        } // creates a copy of population for each thread

    }

    /**
     * @param filename
     * @return Reads in a data file, creates and then returns an ArrayList of
     *         Item objects.
     */
    public static ArrayList<Item> readData(String filename) {
        try {
            ArrayList<Item> items = new ArrayList<>();
            FileReader inputFile = new FileReader(filename);
            Scanner fileIn = new Scanner(inputFile);
            while (fileIn.hasNext()) {
                String splitter[] = fileIn.nextLine().split(", ");
                items.add(new Item(splitter[0], Double.parseDouble(splitter[1]), Integer.parseInt(splitter[2])));
            } // reads in a file and splits at ", " to make item objects
            fileIn.close();
            return items;
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        } // if the file does not exist a new empty arraylist is returned

    }

    /**
     * @param items
     * @return Creates and returns an ArrayList of population Chromosome objects
     *         that each contain the items, with their included field randomly set
     *         to
     *         true or false.
     */
    public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items) {
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i = 0; i < POP_SIZE; i++) {
            population.add(new Chromosome(items));

        } // for the populationSize create and add new chromosomes using the items
          // arraylist
        return population;

    }

    /**
     * Cycles through each thread performing the steps for genetic algorithm
     * then each thread adds a specified number of chromosomes to an array list
     */
    @Override
    public void run() {
        Random rng = new Random();
        for (int generations = 0; generations < NUM_EPOCHS / NUM_THREADS; generations++) {
            ArrayList<Chromosome> nextGen = new ArrayList<>();
            nextGen.addAll(newPop);
            int size = nextGen.size();
            // an int needs to be used for size because the arraylists size is increased
            // everytime a new child is added to it
            for (int i = 0; i < size; i += 2) {
                Chromosome child = nextGen.get(i).crossover(nextGen.get(i + 1));
                nextGen.add(child);
            } // loops over nextGen in twos and then calls the crossover method on the first
              // and second chromosome of each increment
            for (Chromosome x : nextGen) {
                int random = rng.nextInt(5);
                if (random == 1) {
                    x.mutate();
                }
            } // loops through nextGen and generates a random number if the random number
              // generated is 1 then it calls the mutate method on that chromosome
            Collections.sort(nextGen);
            // sorts the chromosomes in nextGen
            newPop.clear();
            for (int add = 0; add < POP_SIZE; add++) {
                newPop.add(nextGen.get(add));
            } // adds the first ten chromosomes in nextGen to newPop
            nextGen.clear();
        }
        if (POP_SIZE < NUM_THREADS) {
            if (population.size() < POP_SIZE) {
                population.add(newPop.get(0));
            }
        } else {
            for (int i = 0; i < numToAdd; i++) {
                population.add(newPop.get(i));
            } // Adds in the designated number of chromosomes from each thread
        } // if the population size is less than the number of threads then the first
          // chromosome of each thread is added in until the population size
          // is reached, otherwise each thread adds in its designated number of
          // chromosomes

    }

    // 21594 milliseconds Fitness 7500,20242 milliseconds Fitness 7600 8 threads 100
    // pop size 10000 epochs more_items.txt
    // 27073 milliseconds Fitness 7600,26080 milliseconds Fitness 7600 7 threads 100
    // pop size 10000 epochs more_items.txt
    // 31802 milliseconds Fitness 7500,28959 milliseconds Fitness 7600 6 threads 100
    // pop size 10000 epochs more_items.txt
    // 35419 milliseconds Fitness 7600,36763 milliseconds Fitness 7600 5 threads 100
    // pop size 10000 epochs more_items.txt
    // 43792 milliseconds Fitness 7500,44616 milliseconds Fitness 7500 4 threads 100
    // pop size 10000 epochs more_items.txt
    // 58182 milliseconds Fitness 7500,56856 milliseconds Fitness 7600 3 threads 100
    // pop size 10000 epochs more_items.txt
    // 71222 milliseconds Fitness 7500,86192 milliseconds Fitness 7600 2 threads 100
    // pop size 10000 epochs more_items.txt
    // 169127 milliseconds Fitness 7500,165663 milliseconds Fitness 7600 1 thread
    // 100 pop size 10000 epochs more_items.txt
    // 5414 milliseconds Fitness 7600,3656 milliseconds Fitness 7600 8 threads 100
    // pop size 2000 epochs more_items.txt
    // 5986 milliseconds Fitness 7500,4281 milliseconds Fitness 7600 7 threads 100
    // pop size 2000 epochs more_items.txt
    // 6770 milliseconds Fitness 7500,4768 milliseconds Fitness 7500 6 threads 100
    // pop size 2000 epochs more_items.txt
    // 7570 milliseconds Fitness 7500,6515 milliseconds Fitness 7500 5 threads 100
    // pop size 2000 epochs more_items.txt
    // 9498 milliseconds Fitness 7500,7109 milliseconds Fitness 7500 4 threads 100
    // pop size 2000 epochs more_items.txt
    // 12148 milliseconds Fitness 7600,7428 milliseconds Fitness 7600 3 threads 100
    // pop size 2000 epochs more_items.txt
    // 17165 milliseconds Fitness 7600,14479 milliseconds Fitness 7600 2 threads 100
    // pop size 2000 epochs more_items.txt
    // 23665 milliseconds Fitness 7500,23327 milliseconds Fitness 7600 1 thread 100
    // pop size 2000 epochs more_items.txt
    /**
     * @param args
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, Exception {
        ArrayList<Item> items = readData("more_items.txt");
        ArrayList<Chromosome> population = initializePopulation(items);
        ArrayList<Thread> threads = new ArrayList<>();
        if (NUM_THREADS == 0 || POP_SIZE == 0) {
            System.out.println("Population size and number of threads must be greater than 0");
            System.exit(0);
        }
        if (POP_SIZE == 1) {
            System.out.println("Population size not large enough for cross over");
            System.exit(0);
        }
        int remainder = (POP_SIZE - ((POP_SIZE / NUM_THREADS) * NUM_THREADS)) + POP_SIZE / NUM_THREADS;
        // the amount of chromosomes the last thread adds in
        for (int i = 0; i < NUM_THREADS; i++) {
            if (i == NUM_THREADS - 1) {
                threads.add(new GeneticAlgorithm(population, remainder));
            } else {
                threads.add(new GeneticAlgorithm(population, POP_SIZE / NUM_THREADS));
            }
        } // each thread is created with an equal amount of chromosomes to add until the
          // last thread which adds the remainder of the required population
        population.clear();
        // clears the intitial population
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds ");
        Collections.sort(population);
        // sorts the chromosomes in population
        System.out.println(population.get(0).toString());
        // Prints out the fittest chromosome in population
    }
}
