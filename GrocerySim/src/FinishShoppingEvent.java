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
        return String.format("%.2f", this.getTime()) + " Finished Shopping Customer "
                + this.getCustomer().getCustomerNumber() + "\n" + items + " chose Lane " + this.getLaneNumber() + " ("
                + this.getLaneSize() + ")";

    }
}