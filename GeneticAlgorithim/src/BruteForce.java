
import java.util.ArrayList;
import java.util.Iterator;

public class BruteForce {

    /**
     * @param items
     * @return
     * @throws InvalidArguementException Creates and returns the most optimal
     * combination of items from an array list of items
     */
    public static ArrayList<Item> getOptimalSet(ArrayList<Item> items) throws InvalidArguementException {
        if (items.size() > 10) {
            throw new InvalidArguementException("To many items for brute force solution");
        } // throws invalid arguement if there are more than 10 items
        ArrayList<Item> optimalSet = new ArrayList<>();
        ArrayList<ArrayList<Item>> powerSet = powerSet(items);
        // creates a power set
        findToMuchWeight(powerSet);
        // removes arraylists with to much weight
        findHighestValue(powerSet);
        // removes sub optimal arraylists
        for (ArrayList<Item> x : powerSet) {
            for (Item item : x) {
                item.setIncluded(true);
                optimalSet.add(item);
            }
            break;
        }// for the first array list the included field for the items are set true and then those items are added to optimal set
        return optimalSet;
    }

    /**
     * @param <Item>
     * @param items
     * @return Creates and returns array lists of items
     */
    public static <Item> ArrayList<ArrayList<Item>> powerSet(ArrayList<Item> items) {
        ArrayList<ArrayList<Item>> powerSet = new ArrayList<>();
        if (items.isEmpty()) {
            powerSet.add(new ArrayList<>());
            return powerSet;
        } // prevents infinite recursion
        ArrayList<Item> list = new ArrayList<>(items);
        Item item = list.get(0);
        ArrayList<Item> remainder = new ArrayList<>(list.subList(1, list.size()));
        // creates an item for the head and then an array list for the remaining items
        powerSet(remainder).forEach(combinationOne -> {
            ArrayList<Item> combinationTwo = new ArrayList<>();
            combinationTwo.add(item);
            combinationTwo.addAll(combinationOne);
            powerSet.add(combinationTwo);
            powerSet.add(combinationOne);
        });// recursivlely creates array lists of items from an arraylist of items
        return powerSet;
    }

    /**
     * @param powerSet Finds the highest value combination of items and then
     * removes any combination that is sub optimal
     */
    public static void findHighestValue(ArrayList<ArrayList<Item>> powerSet) {
        int sum = 0;
        int highestValue = 0;
        Iterator<ArrayList<Item>> it = powerSet.iterator();
        while (it.hasNext()) {
            ArrayList<Item> items = (ArrayList<Item>) it.next();
            // type casts iterator to a set of items
            for (Item item : items) {
                sum += item.getValue();
                if (highestValue < sum) {
                    highestValue = sum;
                }
                // sums up the value for each array list, if that array list's value is higher than highest value then it's value becomes highest value
            }
            sum = 0;
            // sets sum to zero to calculate the next array list's value
        }

        it = powerSet.iterator();
        while (it.hasNext()) {
            ArrayList<Item> items = (ArrayList<Item>) it.next();
            // type casts iterator to an array list of items
            for (Item item : items) {
                sum += item.getValue();
            }
            if (sum < highestValue) {
                it.remove();
            }// if the sets sum is lower than highst value then the array list is removed
            sum = 0;
        }
    }

    /**
     * @param powerSet Finds array list with items that have to much weight and
     * removes them
     */
    public static void findToMuchWeight(ArrayList<ArrayList<Item>> powerSet) {
        Iterator<ArrayList<Item>> it = powerSet.iterator();
        double totalWeight = 0;
        while (it.hasNext()) {
            ArrayList<Item> items = (ArrayList<Item>) it.next();
            // type casts iterator to an array list of items
            for (Item item : items) {
                totalWeight += item.getWeight();
            }// calculate the weight for each array list of items
            if (totalWeight > 10) {
                it.remove();
            }// if the array list's total weight is above 10 then it is removed
            totalWeight = 0;
            // total weight set to 0 to calculate the next array list's total weight
        }

    }
}

class InvalidArguementException extends Exception {

    public InvalidArguementException(String message) {
        super(message);
    }
}
