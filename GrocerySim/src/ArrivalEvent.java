class ArrivalEvent extends Event {

    public ArrivalEvent(Customer customer, double time) {
        super(customer, time);
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.getTime()) + " Arrival Customer " + this.getCustomer().getCustomerNumber();

    }
}
