package estm.dsic.jee.rest.controllers;

import java.util.List;

import estm.dsic.jee.rest.buisness.services.ContactServices;
import estm.dsic.jee.rest.models.Contact;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contacts")
public class ContactController {
    
    @Inject 
    ContactServices contactServices;
    
    @Path("/addContact")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addContact(Contact contact) {
        Contact newContact = contactServices.addContact(contact);
        return Response.ok(newContact).build();
    }

    @Path("/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getUserContacts(@PathParam("userId") int userId) {
        List<Contact> userContacts = contactServices.all(userId);
        return userContacts;
    }

    @Path("/deleteContact")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteContact(Contact contact) {
        contactServices.delete(contact);
        return Response.ok().build();
    }
    
    @Path("/updateContact")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateContact(Contact contact) {
        Contact updatedContact = contactServices.updateContact(contact);
        return Response.ok(updatedContact).build();
    }

    @Path("/findContact")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findContact(Contact contact) {
        Contact foundContact = contactServices.find(contact);
        return Response.ok(foundContact).build();
    }
}
