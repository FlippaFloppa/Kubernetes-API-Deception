package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.ProductDetail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.LinkedHashMap;
import java.util.Set;

import static java.util.Collections.newSetFromMap;
import static java.util.Collections.synchronizedMap;

@Path("/api/vs/products")
@ApplicationScoped
public class ProductCreation {

    private final Set<ProductDetail> productDetails = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public ProductCreation(){
        productDetails.add(new ProductDetail("21322", "coca", "mi serve", 23.5F));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ProductDetail> getProducts(){
        return productDetails;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ProductDetail> addProduct(ProductDetail productDetail){
        return productDetails;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ProductDetail> removeProduct(String productId){

        productDetails.removeIf(productDetail -> productDetail.productId.equals(productId));

        return productDetails;
    }
}
