package it.unibo.openapi.model;

public class ProductDetail {

    public String productId;
    public String productName;
    public String description;
    public Float price;


    public ProductDetail() {
    }

    public ProductDetail(String id, String productName, String description, Float price) {
        this.productId = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }


}
