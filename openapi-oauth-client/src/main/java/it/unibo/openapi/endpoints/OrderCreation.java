package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.Order;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import static java.util.Collections.newSetFromMap;
import static java.util.Collections.synchronizedMap;

@Path("/api/v2/orders")
public class OrderCreation {

    private final Set<Order> orders = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public OrderCreation() {
        //TODO load orders from file
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getOrdersInfo(){

        ArrayList<String> res = new ArrayList<>();

        return  res;
    }

    private Float getOrderTotalPrice(String orderId){

        return null;
    }
}
