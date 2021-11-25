package org.acme.team.entity;

import org.acme.person.entity.Person;

import java.util.Map;

public interface Teamkatalog {

    public Team[] getAll();

    public Team getOne(int id);

    public Team create(Team team);

    public Team update(int id, Team team);

    public Team delete(int id);

    Person deleteOnePlayer(int teamId, int playerId);
}
