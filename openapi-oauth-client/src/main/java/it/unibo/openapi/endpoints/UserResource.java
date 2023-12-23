package it.unibo.openapi.endpoints;

import io.quarkus.runtime.util.HashUtil;
import io.quarkus.security.Authenticated;
import it.unibo.openapi.model.User;
import it.unibo.openapi.model.UserInfo;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.jboss.resteasy.reactive.NoCache;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.*;

@Path( "/api/v2/users")
@ApplicationScoped
@NoCache
@Authenticated
public class UserResource {

    private final Set<User> users = newSetFromMap(synchronizedMap(new LinkedHashMap<>()));

    public UserResource() {
        //TODO load users from file
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    @Path("full")
    public Set<User> getUsers(){
        return users;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","dev"})
    @Path("info")
    public Set<UserInfo> getUsersInfo(){
        return users.stream().map(user -> new UserInfo(user)).collect(Collectors.toSet());
    }

    @GET
    @RolesAllowed({"admin","dev","user"})
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> getUsernames(){
        return users.stream().map(user -> user.username).collect(Collectors.toSet());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"dev","user"})
    public Set<User> add(User user){
        user.password = Base64.getEncoder().encodeToString(("$6$" + HashUtil.sha512(user.password)).getBytes());
        users.add(user);
        return users;
    }

    @POST
    @Path("addAll")
    @RolesAllowed({"dev","user"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> addAll(Set<User> userSet){
        userSet.forEach(
                user -> user.password = Base64.getEncoder().encodeToString(("$6$" + HashUtil.sha512(user.password)).getBytes())
        );
        users.addAll(userSet);
        return users;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @RolesAllowed({"admin"})
    public Set<User> delete(String username){
        users.removeIf( existingUser -> existingUser.username.equals(username));
        return users;
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Set<User> getUsersByUsername(String username){
        return users.stream().filter(user -> user.username.equals(username)).collect(Collectors.toSet());
    }

    @GET
    @Path("{username}/info")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin","dev","user"})
    public Set<UserInfo> getUserInfoByUsername(String username){
        return users.stream().filter(user -> user.username.equals(username))
                .map(user -> new UserInfo(user))
                .collect(Collectors.toSet());
    }

}
