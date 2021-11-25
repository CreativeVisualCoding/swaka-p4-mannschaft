package org.acme.team.control;

import org.acme.team.entity.Team;
import org.acme.team.entity.Teamkatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnzeigeAusKatalog {
    @Inject
    Teamkatalog teamRepository;

    public Team[] getAll() {
        return teamRepository.getAll();
    }

    public Team getOne(int id) {
        return teamRepository.getOne(id);
    }
}
