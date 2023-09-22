package common;

import java.util.ArrayList;
import model.Order;

public class AlgorithmOrder extends ArrayList<Order>{

    public AlgorithmOrder() {
    }
    
    public void create(Order order) {
       this.add(order);
    }
    
    public void display() {
        for (Order order : this) {
            System.out.println(order);
        }
    }
}