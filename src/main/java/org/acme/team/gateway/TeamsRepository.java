package org.acme.team.gateway;

import org.acme.team.entity.Team;
import org.acme.team.entity.TeamAttribute;
import org.acme.team.entity.Teamkatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class TeamsRepository implements Teamkatalog {
    int nextId = 1;
    Map<Integer, Team> teams = new HashMap<Integer, Team>();

    public TeamsRepository() {
        Team team = new Team();
        Team team2 = new Team();
        TeamAttribute attr = new TeamAttribute();
        attr.setName("Name");
        attr.setCategory("Category");
        TeamAttribute[] attributes = {attr};
        team.setAttributes(attributes);
        team2.setAttributes(attributes);
        this.create(team);
        this.create(team2);
    }


    @Override
    public Team[] getAll() {
        return this.teams.values().toArray(new Team[0]);
    }

    @Override
    public Team getOne(int id) {
        return teams.get(id);
    }

    @Override
    public Team create(Team team) {
        team.setId(nextId++);
        teams.put(team.getId(), team);
        return getOne(team.getId());
    }

    @Override
    public Team update(int id, Team team) {
        if (getOne(id) == null) return null;
        team.setId(id);
        teams.put(id, team);
        return getOne(id);
    }

    @Override
    public Team delete(int id) {
        return teams.remove(id);
    }
}
