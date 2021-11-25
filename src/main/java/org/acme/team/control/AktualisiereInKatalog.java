package org.acme.team.control;

import org.acme.person.entity.PersonKatalog;
import org.acme.team.entity.Team;
import org.acme.team.entity.Teamkatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AktualisiereInKatalog {
    @Inject
    Teamkatalog teamRepository;

    public Team update(int id, Team item) {
        return teamRepository.update(id, item);
    }
}
