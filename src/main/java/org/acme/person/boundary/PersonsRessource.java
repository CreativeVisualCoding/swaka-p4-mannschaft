package org.acme.person.boundary;

import org.acme.person.control.PersonAdministrator;
import org.acme.person.entity.Person;
import org.acme.utils.BaseRessource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonsRessource implements BaseRessource<Person> {

    @Inject
    PersonAdministrator personAdmin;

    @Override
    @GET
    public Response get() {
        Person[] all = this.personAdmin.getAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("{id}")
    public Response getOne(@PathParam("id") int id) {
        Person person = this.personAdmin.getOne(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }


    @PUT
    @Path("{id}")
    @Transactional
    public Response put(@PathParam("id") int id, Person person) {
        Person update = this.personAdmin.update(id, person);
        if (update == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(update).build();
    }


    @POST
    @Transactional
    public Response post(Person person) {
        Person create = this.personAdmin.create(person);
        if (create == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(create).build();
    }


    @Override
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        Person delete = this.personAdmin.delete(id);
        if (delete == null) {
            return Response.status(Response.Status.NOT_FOUND
            ).build();
        }
        return Response.ok(delete).build();
    }
}
