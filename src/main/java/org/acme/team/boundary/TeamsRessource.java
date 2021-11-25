package org.acme.team.boundary;

import org.acme.person.entity.Person;
import org.acme.rest.util.entity.Error;
import org.acme.rest.util.entity.Relationship;
import org.acme.rest.util.entity.RestResponse;
import org.acme.team.control.TeamAdministrator;
import org.acme.team.entity.Team;
import org.acme.utils.BaseRessource;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamsRessource implements BaseRessource<Team> {

    @Inject
    TeamAdministrator teamVw;

    @Context
    UriInfo uriInfo;

    @GET
    // Link: http://localhost:8080/teams
    public Response get() {
        ArrayList<Team> dtos = new ArrayList<>(Arrays.asList(this.teamVw.getAll()));

        List<Link> links = new ArrayList<>();
        if (dtos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Keine Teams vorhanden.").build();
        }
        dtos.forEach(team -> {
            //Erstellung der jeweiligen paths der einzelnen Teams
            URI uri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path(String.valueOf(team.id)).build();
            Link link = Link.fromUri(uri).rel("teams").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
            links.add(link);
        });
        return Response.ok(dtos).links(links.toArray(new Link[links.size()])).build();
    }

    @GET
    @Path("/{id}")
    // Link: http://localhost:8080/teams/1?include=manager,players
    public Response getOne(@PathParam("id") int id, @QueryParam("include") String include) {
        Team team = null;
        List<Link> links = new ArrayList<>();
        if (include != null) {
            if (include.contains("manager") && !include.contains("players")) {
                team = this.teamVw.getOne(id);
                links.addAll(this.getBothLinks("manager"));
            } else if (include.contains("players") && !include.contains("manager")) {
                team = this.teamVw.getOne(id);
                links.addAll(this.getBothLinks("players"));
            } else { //include alles
                team = this.teamVw.getOne(id);
                links.addAll(this.getBothLinks("manager"));
                links.addAll(this.getBothLinks("players"));
            }
        } else {
            team = this.teamVw.getOne(id);
        }
        if (team != null) {
            return Response.ok(team).links(links.toArray(new Link[links.size()])).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + id + " existiert nicht.").build();
    }

    @POST
    public Response post(Team team) {
        Team newTeam = this.teamVw.create(team);
        if (newTeam != null) {
            URI selfUri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path(String.valueOf(newTeam.id)).build();
            Link selfLink = Link.fromUri(selfUri).rel("teams").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
            return Response.status(Response.Status.CREATED).entity(newTeam).links(selfLink).build();
        }
        return Response.status(Response.Status.CONFLICT).entity("Team existiert bereits.").build();
    }

    @PUT
    public Response put(Team team) {
        Team newTeam = this.teamVw.update(team.id, team);
        if (newTeam != null) {
            URI selfUri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path(String.valueOf(newTeam.id)).build();
            Link selfLink = Link.fromUri(selfUri).rel("teams").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
            return Response.ok(newTeam).links(selfLink).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team existiert nicht.").build();
    }

    @DELETE
    @Path("/{id}")
    // Link: http://localhost:8080/teams/0
    public Response delete(@PathParam("id") int id) {
        if (this.teamVw.delete(id)) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + id + " existiert nicht.").build();
    }

    @GET
    @Path("/{id}/relationships/players")
    // Link: http://localhost:8080/teams/0/relationships/players
    public Response getPlayersRelation(@PathParam("id") int teamId) {
        ArrayList<Relationship> rel = new ArrayList<Relationship>();
        for (Person player:this.teamVw.getOne(teamId).getSpieler()) {
            rel.add(player.rel);
        }

        if (rel != null) {
            List<Link> links = this.getRelationshipLinks();
            return Response.ok(rel).links(links.toArray(new Link[links.size()])).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + teamId + " existiert nicht.").build();
    }

    @GET
    @Path("/{id}/players")
    // Link: http://localhost:8080/teams/0/players
    public Response getPlayers(@PathParam("id") int teamId) {
        ArrayList<Person> persons =new ArrayList<Person>(Arrays.asList(this.teamVw.getOne(teamId).getSpieler()));
        if (persons.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + teamId + " existiert nicht.").build();
        }
        URI uri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).build();
        Link link = Link.fromUri(uri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
        return Response.ok(persons).links(link).build();
    }

    @GET
    @Path("/{id}/relationships/manager")
    // Link: http://localhost:8080/teams/0/relationships/manager
    public Response getManagerRelation(@PathParam("id") int teamId) {
        Person person = this.teamVw.getOne(teamId).getManager();
        if (person != null) {
            List<Link> links = this.getRelationshipLinks();
            return Response.ok(person).links(links.toArray(new Link[links.size()])).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + teamId + " existiert nicht.").build();
    }

    @GET
    @Path("/{id}/manager")
    // Link: http://localhost:8080/teams/0/manager
    public Response getManager(@PathParam("id") int teamId) {
        Person dto = this.teamVw.getOne(teamId).getManager();
        if (dto != null) {
            URI uri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).build();
            Link link = Link.fromUri(uri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
            return Response.ok(dto).links(link).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Team mit Id " + teamId + " existiert nicht.").build();
    }

    @POST
    @Path("/{id}/players")
    // WICHTIG: -> alle PUT und POST nur über die Tests testbar
    public Response addPlayer(@PathParam("id") int teamId, Person player) {
        Person newPlayer = this.teamVw.getOne(teamId).setOnePlayer(player);
        if (newPlayer != null) {
            URI uri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).build();
            Link link = Link.fromUri(uri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
            return Response.status(Response.Status.CREATED).entity(newPlayer).links(link).build();
        }
        return Response.status(Response.Status.CONFLICT).entity("Spieler existiert bereits im Team mit Id " + teamId + ".").build();
    }

    @DELETE
    @Path("/{teamId}/players/{playerId}")
    // Link: http://localhost:8080/teams/0/players/1
    // wird aber auch ueber Test Klasse getestet
    public Response deletePlayer(@PathParam("teamId") int teamId, @PathParam("playerId") int playerId) {
        if (this.teamVw.deletePlayer(teamId, playerId) != null) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Player mit Id " + playerId + " existiert nicht im Team mit Id " + teamId + ".").build();
    }

    private List<Link> getBothLinks(String path){
        return Arrays.asList(this.getSelfLink(path), this.getRelatedLink(path));
    }

    private Link getSelfLink(String path){
        URI selfUri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path(path).build();
        return Link.fromUri(selfUri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();

    }

    private Link getRelatedLink(String path){
        URI relatedUri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).path("relationships").path(path).build();
        return Link.fromUri(relatedUri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
    }

    private List<Link> getRelationshipLinks(){
        URI selfUri = UriBuilder.fromUri(this.uriInfo.getAbsolutePath()).build();
        Link selfLink = Link.fromUri(selfUri).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
        UriBuilder relatedUri = UriBuilder.fromUri(this.uriInfo.getBaseUri());
        this.uriInfo.getPathSegments().forEach(path -> {
            if(!path.getPath().equals("relationships")){
                relatedUri.path(path.getPath());
            }
        });
        Link relatedLink = Link.fromUri(relatedUri.build()).rel("persons").type(MediaType.APPLICATION_JSON).param("method", "GET").build();
        return Arrays.asList(selfLink, relatedLink);
    }

}