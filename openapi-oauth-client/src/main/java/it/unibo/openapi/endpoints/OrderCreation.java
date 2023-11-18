package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.newSetFromMap;
import static java.util.Collections.synchronizedMap;

@Path("/api/v2/orders")
public class OrderCreation {

    @Inject
    ProductCreation productCreation;

    @Inject
    UserResource userResource;

    private final Set<Order> orders = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public OrderCreation() {
        //TODO load orders from file
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getOrdersInfo(){

        ArrayList<String> res = new ArrayList<>();

        orders.stream().forEach(order -> res.add("ID: " + order.orderId + "  User: "+ order.username
        + "  total price: " + getOrderTotalPrice(order.orderId)));

        return  res;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Set<Order> addOrder(Order order){
        orders.add(order);

        return  orders;
    }

    private Float getOrderTotalPrice(String orderId){

        Float res = 0.0F;
        Set<Set<Float>> tmp = orders.stream().filter(order -> order.orderId.equals(orderId)).map(order -> {
            return productCreation.getProducts().stream().filter(product -> order.products.containsKey(product.productId))
                    .map(product -> product.price * order.products.get(product.productId)).collect(Collectors.toSet());
        }).collect(Collectors.toSet());

        for(Set<Float> entry:tmp){
            for(Float value: entry){
                res *= value;
            }
        }

        return res;
    }
}
