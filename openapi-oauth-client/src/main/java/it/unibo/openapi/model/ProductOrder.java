package it.unibo.openapi.model;

public class ProductOrder {

    public String productId;
    public int quantity;


    public ProductOrder() {
    }

    public ProductOrder(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
