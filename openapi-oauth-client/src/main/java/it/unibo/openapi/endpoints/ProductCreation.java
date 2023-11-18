package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.newSetFromMap;
import static java.util.Collections.synchronizedMap;

@Path("/api/vs/products")
@ApplicationScoped
public class ProductCreation {

    private final Set<Product> products = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public ProductCreation(){
        products.add(new Product("21322", "coca", "mi serve", 23.5F));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Product> getProducts(){
        return products;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Product> addProduct(Product product){
        return products;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Product> removeProduct(String productId){

        products.removeIf(product -> product.productId.equals(productId));

        return products;
    }
}
