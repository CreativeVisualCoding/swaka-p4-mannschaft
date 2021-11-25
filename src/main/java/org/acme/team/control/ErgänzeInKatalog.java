package org.acme.team.control;

import org.acme.team.entity.Team;
import org.acme.team.entity.Teamkatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ErgänzeInKatalog {
    @Inject
    Teamkatalog teamRepository;

    public Team create(Team item) {
        return teamRepository.create(item);
    }
}
