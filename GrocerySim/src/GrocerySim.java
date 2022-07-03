import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GrocerySim {

    public static PriorityQueue<Lane> makeLanes(int express, int regular) {
        PriorityQueue<Lane> lanes = new PriorityQueue<>();
        int laneNumber = 0;
        for (int i = 0; i < express; i++) {
            ExpressLane expressLane = new ExpressLane(laneNumber++);
            lanes.add(expressLane);

        }
        for(int i =0; i<regular; i++){
            RegularLane regularLane = new RegularLane(laneNumber++);
            lanes.add(regularLane); 
        }
        return lanes;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
//     Timer timer = new Timer();  
//     TimerTask task = new TimerTask(){
//         public void run(){
//             
//         }
//     };
        ArrayList<Event> events = new ArrayList<>();
       ArrayList<Customer> customers = new ArrayList<>();
        try {
        FileReader inputFile = new FileReader("Arrival medium.txt");
        Scanner fileIn = new Scanner(inputFile);
        int customerNumber = 0;
        
            while (fileIn.hasNext()) {
                String trimmed = fileIn.nextLine().replaceAll("\\s+", " ");
                String splitter[] = trimmed.split(" ");
                Customer customer = new Customer(Integer.parseInt(splitter[1]), Double.parseDouble(splitter[2]), customerNumber);
                customers.add(customer);
                events.add(new ArrivalEvent(customer, Double.parseDouble(splitter[0])));
                // makes an arrivalEvent for the customer
                customerNumber++;
            }
            fileIn.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        PriorityQueue<Lane> lanes = makeLanes(0,1); 
        
       for (int i =0; i<events.size(); i++) {
           Collections.sort(events);
            if (events.get(i) instanceof ArrivalEvent) {
                double time = 0;
                time = events.get(i).getTime() + (events.get(i).getCustomer().getNumberOfItems() * events.get(i).getCustomer().getTimePerItem());
                events.get(i).getCustomer().setFinishShoppingTime(time);
                // stores the time that the customer finished shopping
                FinishShoppingEvent finishShoppingEvent = new FinishShoppingEvent(events.get(i).getCustomer(), time);
                // makes a FinishShopping event for the customer
                events.add(finishShoppingEvent);
            }

            if (events.get(i) instanceof FinishShoppingEvent) {

                PriorityQueue<Lane> expressLane = new PriorityQueue<>();
                if (events.get(i).getCustomer().getNumberOfItems() > 12) {
                    while (lanes.peek() instanceof ExpressLane) {
                         expressLane.add(lanes.poll());
                     } // if the customer cannot use an expresslane then the expresslanes are polled into a different pq until a regularlane is found
                     Lane lane = lanes.peek();
                     events.get(i).setLaneNumber(lanes.peek().getLaneNumber());
                     events.get(i).setLaneSize(lane.size());
                     // sets the size according to how many customers are currently in the lane
                     lanes.peek().add(events.get(i));
                     // adds the event into the lane
                     events.add(lanes.poll().getEvent());
                     // polls the lane to add the new event to the event pq
                     lanes.add(lane);
                   
//                     if (!lanes.peek().isEmpty()) {
//                         lanes.peek().remove(0);
//                     }
                
                 }
                 lanes.addAll(expressLane);
                 // adds the express lanes back in
                 if (events.get(i).getCustomer().getNumberOfItems() <= 12) {
                     Lane lane = lanes.peek();
                     events.get(i).setLaneNumber(lanes.peek().getLaneNumber());
                     events.get(i).setLaneSize(lane.size());
                     System.out.println(lane.size());
                     // sets the size according to how many customers are currently in the lane
                     lanes.peek().add(events.get(i));
                     // adds the event into the lane
                     events.add(lanes.poll().getEvent());
                     // polls the lane to add the new event to the event pq
                     lanes.add(lane);
                
//                     if (!lanes.peek().isEmpty()) {
//                         lanes.peek().remove(0);
//                     }
                 }

            }
            Collections.sort(events);
            System.out.println(events.get(i));
            if (!lanes.peek().isEmpty()) {
                    lanes.peek().remove(0);
                }
            if (events.get(i) instanceof FinishCheckoutEvent) {
                if (!lanes.peek().isEmpty()) {
                    lanes.peek().remove(0);
                }

                events.get(i).setLaneSize(lanes.peek().size());
                // sets the size according to the new lane size after the customer in front has finished checking out
            }

        }
         
    }
}
