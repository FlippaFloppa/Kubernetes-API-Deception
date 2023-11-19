package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.Order;
import it.unibo.openapi.model.ProductDetail;
import it.unibo.openapi.model.ProductOrder;
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

        Order order = getOrderById(orderId);

        for(ProductOrder p: order.products){
            res += getProductDetailById(p.productId).price * p.quantity;
        }

        return res;
    }

    private ProductDetail getProductDetailById(String id){
        return productCreation.getProducts().stream().filter(productDetail -> productDetail.productId.equals(id)).limit(1).collect(Collectors.toList()).get(0);
    }

    private Order getOrderById(String id){
        return orders.stream().filter(order -> order.orderId.equals(id)).limit(1).collect(Collectors.toList()).get(0);
    }
}
