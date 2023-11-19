package it.unibo.openapi.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order {

    public String orderId;
    public List<ProductOrder> products;
    public String username;

    public Order() {
    }

    public Order(String orderId, List<ProductOrder> products, String buyer) {
        this.orderId = orderId;
        this.products = products;
        this.username = buyer;
    }
}
