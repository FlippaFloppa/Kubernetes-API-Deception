package it.unibo.openapi.model;

import io.quarkus.runtime.util.HashUtil;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

public class Product {

    public String productId;
    public String productName;
    public String description;
    public Float price;


    public Product() {
    }

    public Product(String id, String productName, String description, Float price) {
        this.productId = id;
        this.productName = productName;
        this.description = description;
        this.price = price;
    }


}
