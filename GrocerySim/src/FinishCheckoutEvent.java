class FinishCheckoutEvent extends Event {

    public FinishCheckoutEvent(Customer customer, double time) {
        super(customer, time);

    }

    @Override
    public String toString() {
        return String.format("%.2f", this.getTime()) + ": Finished Checkout Customer "
                + this.getCustomer().getCustomerNumber() + " on lane " + this.getLaneNumber() + " ("
                + this.getLaneSize() + ")" + "(" + String.format("%.2f", this.getWaitTime())
                + " minute wait, there are " + this.getLaneSize() + " people in line" + " -- finished shopping at "
                + String.format("%.2f", this.getCustomer().getFinishShoppingTime())
                + ", got to the front of the line at " + String.format("%.2f", this.getFrontOfTheLine()) + ")";

    }
}
