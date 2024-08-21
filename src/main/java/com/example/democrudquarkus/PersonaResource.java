package com.example.democrudquarkus;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.*;
import model.Persona;
import service.api.PersonaServiceApi;

@ApplicationScoped
@Path("/persona/api")
public class PersonaResource {

    @Inject
    PersonaServiceApi personaServiceApi;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("id") Integer id) {
        if (id == null) {
            return Response.ok(personaServiceApi.getAll()).status(Status.OK).build();
        }

        return Response.ok(personaServiceApi.get(id)).status(Status.OK).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Persona p) {
        p = personaServiceApi.save(p);
        return Response.ok(p).status(Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Persona p) {
        p = personaServiceApi.update(p);
        return Response.ok(p).status(Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        personaServiceApi.delete(id);
        return Response.ok().status(Status.NO_CONTENT).build();
    }
}
