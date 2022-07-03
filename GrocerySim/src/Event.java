
public class Event implements Comparable<Event> {

    private final Customer customer;
    private double time;
    private int laneNumber;
    private int laneSize;
    private double waitTime;
    private double frontOfTheLine;
   
    public Event(Customer customer, double time) {
        this.customer = customer;
        this.time = time;
    }

    public double getFrontOfTheLine() {
        return frontOfTheLine;
    }

    public void setfrontOfTheLine(double frontOfTheLine) {
        this.frontOfTheLine = frontOfTheLine;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }

    public int getLaneSize() {
        return laneSize;
    }

    public void setLaneSize(int laneSize) {
        this.laneSize = laneSize;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(time, other.time);
    }
}

class ArrivalEvent extends Event {

    public ArrivalEvent(Customer customer, double time) {
        super(customer, time);
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.getTime()) + " Arrival Customer " + this.getCustomer().getCustomerNumber();

    }
}

class FinishShoppingEvent extends Event {

    private final String items;

    public FinishShoppingEvent(Customer customer, double time) {
        super(customer, time);
        if (customer.getNumberOfItems() <= 12) {
            items = "12 or fewer";
        } else {
            items = "More than 12";
        }

    }

    @Override
    public String toString() {
        return String.format("%.2f", this.getTime()) + " Finished Shopping Customer " + this.getCustomer().getCustomerNumber() + "\n" + items + " chose Lane " + this.getLaneNumber() + " (" + this.getLaneSize() + ")";

    }
}

class FinishCheckoutEvent extends Event {

    public FinishCheckoutEvent(Customer customer, double time) {
        super(customer, time);

    }

    @Override
    public String toString() {
        return String.format("%.2f", this.getTime()) + ": Finished Checkout Customer " + this.getCustomer().getCustomerNumber() + " on lane " + this.getLaneNumber() + " (" + this.getLaneSize() + ")" + "(" + String.format("%.2f", this.getWaitTime()) + " minute wait, there are " + this.getLaneSize() + " people in line" + " -- finished shopping at " + String.format("%.2f", this.getCustomer().getFinishShoppingTime()) + ", got to the front of the line at " + String.format("%.2f", this.getFrontOfTheLine())+ ")";

    }
}
