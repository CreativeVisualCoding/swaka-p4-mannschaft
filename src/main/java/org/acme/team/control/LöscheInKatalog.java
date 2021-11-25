package org.acme.team.control;

import org.acme.person.entity.Person;
import org.acme.team.entity.Team;
import org.acme.team.entity.Teamkatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LöscheInKatalog {

    @Inject
    Teamkatalog teamRepository;

    public Team delete(int id) {
        return teamRepository.delete(id);
    }

    public Person deleteOnePlayer(int teamId, int playerId){
        return teamRepository.deleteOnePlayer(teamId, playerId);
    }
}
