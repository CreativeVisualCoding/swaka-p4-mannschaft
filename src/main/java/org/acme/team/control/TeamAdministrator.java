package org.acme.team.control;


import org.acme.person.entity.Person;
import org.acme.team.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TeamAdministrator implements CRUD {

    @Inject
    AnzeigeAusKatalog anzeigeAusKatalog;
    @Inject
    ErgänzeInKatalog ergänzeInKatalog;
    @Inject
    AktualisiereInKatalog aktualisiereInKatalog;
    @Inject
    LöscheInKatalog löscheInKatalog;

    @Override
    public Team[] getAll() {
        return this.anzeigeAusKatalog.getAll();
    }

    @Override
    public Team getOne(int id) {
        return this.anzeigeAusKatalog.getOne(id);
    }

    @Override
    public Team update(int id, Team item) {
        return this.aktualisiereInKatalog.update(id, item);
    }

    @Override
    public Team create(Team item) {
        return this.ergänzeInKatalog.create(item);
    }

    @Override
    public boolean delete(int id) {
        return this.löscheInKatalog.delete(id);
    }

    public Person deletePlayer(int teamId, int playerId) {
        return this.löscheInKatalog.deleteOnePlayer(teamId,playerId);
    }
}
