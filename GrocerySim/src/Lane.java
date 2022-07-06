import java.util.ArrayList;

public class Lane extends ArrayList<Event> implements Comparable<Lane> {
    private final int laneNumber;
    private Event event;

    public Lane(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public Event getEvent() {
        double waitTime = 0;
        double checkoutTime;
        if (this instanceof ExpressLane) {
            for (int i = 0; i < this.size(); i++) {
                checkoutTime = this.get(i).getTime() + 1 + (this.get(i).getCustomer().getNumberOfItems() * .10);
                // checkoutTime is calculated based on the type of lane
                double totalTime = checkoutTime + waitTime;
                // adds the customers time to checkout plus how long they had to wait for the
                // customers in front of them to check out
                double frontOfTheLine = this.get(i).getCustomer().getFinishShoppingTime() + waitTime;
                // adds the customers wait time and the time they finished shopping to determine
                // when they get to the front of the line
                FinishCheckoutEvent finishCheckout = new FinishCheckoutEvent(this.get(i).getCustomer(), totalTime);
                finishCheckout.setfrontOfTheLine(frontOfTheLine);
                finishCheckout.setLaneNumber(this.getLaneNumber());
                finishCheckout.setWaitTime(waitTime);
                finishCheckout.setLaneSize(this.size());
                waitTime += checkoutTime - this.get(i).getCustomer().getFinishShoppingTime();
                // if the customers are in the same lane then it sums the previous customers
                // required time to checkout
                event = finishCheckout;

            }

        } else {

            for (int i = 0; i < this.size(); i++) {
                checkoutTime = this.get(i).getTime() + 2 + (this.get(i).getCustomer().getNumberOfItems() * .05);
                // checkoutTime is calculated based on the type of lane
                double totalTime = checkoutTime + waitTime;
                // adds the customers time to checkout plus how long they had to wait for the
                // customers in front of them to check out
                double frontOfTheLine = this.get(i).getCustomer().getFinishShoppingTime() + waitTime;
                // adds the customers wait time and the time they finished shopping to determine
                // when they get to the front of the line
                FinishCheckoutEvent finishCheckout = new FinishCheckoutEvent(this.get(i).getCustomer(), totalTime);
                finishCheckout.setfrontOfTheLine(frontOfTheLine);
                finishCheckout.setLaneNumber(this.getLaneNumber());
                finishCheckout.setWaitTime(waitTime);
                finishCheckout.setLaneSize(this.size());
                waitTime += checkoutTime - this.get(i).getCustomer().getFinishShoppingTime();
                // if the customers are in the same lane then it sums the previous customers
                // required time to checkout
                event = finishCheckout;

            }

        }

        return event;
    }

    @Override
    public int compareTo(Lane other) {
        if (this.size() < other.size()) {
            return -1;
        } else if (this.size() > other.size()) {
            return 1;
        } else if (this instanceof ExpressLane && !(other instanceof ExpressLane)) {
            return -1;
        } else if (other instanceof ExpressLane && !(this instanceof ExpressLane)) {
            return 1;
        } else {
            return 0;
        }

    }
}

class ExpressLane extends Lane {

    public ExpressLane(int laneNumber) {
        super(laneNumber);
    }

}

class RegularLane extends Lane {

    public RegularLane(int laneNumber) {
        super(laneNumber);
    }

}