package org.acme.team.boundary;

import org.acme.person.entity.Person;
import org.acme.team.control.TeamAdministrator;
import org.acme.team.entity.Team;
import org.acme.utils.BaseRessource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path("teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamsRessource implements BaseRessource<Team> {

    @Inject
    TeamAdministrator teamAdmin;

    @Override
    @GET
    public Response get() {
        Team[] all = this.teamAdmin.getAll();
        return Response.ok(all).build();
    }

    @Override
    @GET
    @Path("{id}")
    public Response getOne(@PathParam("id") int id) {
        Team team = this.teamAdmin.getOne(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(team).build();
    }

    @Override
    @PUT
    @Path("{id}")
    @Transactional
    public Response put(@PathParam("id") int id, Team item) {
        Team team = this.teamAdmin.update(id, item);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(team).build();
    }

    @Override
    @POST
    @Transactional
    public Response post(Team item) {
        Team team = this.teamAdmin.create(item);
        if (team == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.ok(team).build();
    }

    @Override
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        Team team = this.teamAdmin.delete(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(team).build();
    }
}