package estm.dsic.jee.rest.controllers;

import java.util.List;

import estm.dsic.jee.rest.buisness.services.UserServices;
import estm.dsic.jee.rest.models.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {
    @Inject 
    UserServices userServices;
 
    @Path("/auth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User auth(User user) {
       return userServices.authentification(user);
    }

    @Path("/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        userServices.insert(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/validate")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateUser(User user) {
        boolean success = userServices.validate(user);
        if (success) {
            return Response.ok().build(); 
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        
        }
    }

    @Path("/delete")
    @DELETE
    public Response deleteUser(User user) {
        userServices.delete(user);
        return Response.ok().build(); 
    }

     
    @Path("/find")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser(User user) {
        return userServices.find(user);
    }

     @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userServices.all();
    }
    
@Path("/update")
@PUT
@Consumes(MediaType.APPLICATION_JSON)
public Response updateUser(User user) {
    User updatedUser = userServices.update(user);
    if (updatedUser != null) {
        return Response.ok().build(); 
    } else {
        return Response.status(Response.Status.NOT_FOUND).build(); 
    }
}

// @Path("/logout")
// @GET
// public Response logout() {
//     userServices.logout();
//     return Response.ok().build();
// }

@Path("/nbrContact")
@POST
@Consumes(MediaType.APPLICATION_JSON)
public int nbrContact(User user) {
    return userServices.nbrContact(user);
}


}







