
public class Customer {

   
    private final int numberOfItems;
    private final double timePerItem;
    private final int customerNumber;
    public double finishShoppingTime;
    public Customer(int numberOfItems, double timePerItem, int customerNumber) {
        this.numberOfItems = numberOfItems;
        this.timePerItem = timePerItem;
        this.customerNumber = customerNumber;

    }
    public int getNumberOfItems() {
        return numberOfItems;
    }
    public double getTimePerItem(){
        return timePerItem;
    }
     public int getCustomerNumber() {
        return customerNumber;
    }
     public void setFinishShoppingTime(double finishShoppingTime){
       this.finishShoppingTime = finishShoppingTime;  
     }
     public double getFinishShoppingTime(){
         return finishShoppingTime;
     }
}