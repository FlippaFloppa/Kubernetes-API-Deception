package it.unibo.openapi.endpoints;

import it.unibo.openapi.model.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.*;

@Path("/users")
public class UserResource {

    private final Set<User> users = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public UserResource() {
        //TODO load users from file
        users.add(new User("Michele", "Pioli",
                "michelepalle","michele@phu.com",
                "piedi", new Date()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> listUsers(){
        return users;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> add(User user){
        users.add(user);
        return users;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> delete(User user){
        users.removeIf( existingUser -> existingUser.username.equals(user.username));
        return users;
    }

    @GET
    @Path("/{username}")
    public Set<User> getUsersByUsername(String username){
        return users.stream().filter(user -> user.username.equals(username)).collect(Collectors.toSet());
    }
}