
public class Item {

    private final String name;
    // A label for the item, such as “Jewelry” or “Kindle”
    private final double weight;
    // The weight of the item in pounds
    private final int value;
    // The value of the item rounded to the nearest dollar
    private boolean included;
    // Indicates whether the item should be taken or not

    public Item(String name, double weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        included = false;
    } // Initializes the Item’s fields to the values that are passed
    // in; the included field is initialized to false

    public Item(Item other) {
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
        included = other.included;
    } // Initializes this item’s fields to the be the same as the other item’s

    public double getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public boolean isIncluded() {
        return included;
    }

    public void setIncluded(boolean included) {
        this.included = included;
    }

    /**
     * To string method that displays the name, weight and value items of items
     * with their included field set to true .
     */
    @Override
    public String toString() {
        if (isIncluded()) {
            return " " + name + " " + "(" + weight + " lbs" + " $" + value + "),";
        } else {
            return "";
        }
    }
}
