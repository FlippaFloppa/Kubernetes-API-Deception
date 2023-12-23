package it.unibo.openapi.endpoints;

import io.quarkus.security.Authenticated;
import it.unibo.openapi.model.ProductDetail;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.NoCache;

import java.util.LinkedHashMap;
import java.util.Set;

import static java.util.Collections.newSetFromMap;
import static java.util.Collections.synchronizedMap;

@Path("/api/v2/products")
@ApplicationScoped
@NoCache
public class ProductCreation {

    private final Set<ProductDetail> productDetails = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public ProductCreation(){

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Set<ProductDetail> getProducts(){
        return productDetails;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"dev"})
    public Set<ProductDetail> addProduct(ProductDetail productDetail){
        this.productDetails.add(productDetail);
        return productDetails;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addAll")
    @RolesAllowed({"dev"})
    public Set<ProductDetail> addProducts(Set<ProductDetail> products){
        this.productDetails.addAll(products);
        return productDetails;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Set<ProductDetail> removeProduct(String productId){

        productDetails.removeIf(productDetail -> productDetail.productId.equals(productId));
        return productDetails;
    }
}
