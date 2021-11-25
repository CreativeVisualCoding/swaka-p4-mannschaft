package org.acme.team.control;

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
}
