
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
