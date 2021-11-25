package org.acme.team.boundary;

import org.acme.rest.util.entity.Error;
import org.acme.rest.util.entity.RestResponse;
import org.acme.team.control.TeamAdministrator;
import org.acme.team.entity.Team;
import org.acme.utils.BaseRessource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Path("teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamsRessource implements BaseRessource<Team> {

    @Inject
    TeamAdministrator teamAdmin;

    @Context
    UriInfo uriInfo;

    @Override
    @GET
    public Response get() {
        Team[] all = this.teamAdmin.getAll();
        List<Link> links = new ArrayList<>();
        for (Team team:all) {
            URI uri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path(String.valueOf(team.id)).build();
         //   Link link = Link.fromUri(uri).rel("teams").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
        //   links.add(link);
           team.links.self = uri.toString();
        }
       return Response.ok(all).links(links.toArray(new Link[links.size()])).build();
      //  return Response.ok(RestResponse.getDataResponseFromArray(all)).links().build();
    }

    @Override
    @GET
    @Path("{id}")
    public Response getOne(@PathParam("id") int id) {
        Team team = this.teamAdmin.getOne(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(RestResponse.getDataResponse(team)).build();
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
        return Response.ok(RestResponse.getDataResponse(team)).build();
    }

    @Override
    @POST
    @Transactional
    public Response post(Team item) {
        Team team = this.teamAdmin.create(item);
        if (team == null) {
            return Response.ok(RestResponse.getErrorResponse(Error.getNotCreated())).build();
        }
        return Response.ok(RestResponse.getDataResponse(team)).build();
    }

    @Override
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        Team team = this.teamAdmin.delete(id);
        if (team == null) {
            return Response.ok(RestResponse.getErrorResponse(Error.getNotFoundError())).build();
        }
        return Response.ok(RestResponse.getDataResponse(team)).build();
    }
}