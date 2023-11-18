package it.unibo.openapi.endpoints;

import io.quarkus.runtime.util.HashUtil;
import it.unibo.openapi.model.User;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.*;

@Path( "/api/v2/users")
@ApplicationScoped
public class UserResource {

    private final Set<User> users = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public UserResource() {
        //TODO load users from file
        users.add(new User("Michele", "Pioli",
                "michelepalle","michele@phu.com",
                "piedi", LocalDateTime.now()));
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> listUsernames(){
        return users.stream().map(user -> user.username).collect(Collectors.toSet());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> add(User user){
        user.password = Base64.getEncoder().encodeToString(("$6$" + HashUtil.sha512(user.password)).getBytes());
        users.add(user);
        return users;
    }

    @POST
    @Path("addAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> addAll(Set<User> userSet){
        users.addAll(userSet);
        return users;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Set<User> delete(String username){
        users.removeIf( existingUser -> existingUser.username.equals(username));
        return users;
    }

    @GET
    @Path("{username}")
    public Set<User> getUsersByUsername(String username){
        return users.stream().filter(user -> user.username.equals(username)).collect(Collectors.toSet());
    }

}
