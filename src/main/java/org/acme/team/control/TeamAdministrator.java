package org.acme.team.control;


import org.acme.team.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TeamAdministrator {

    @Inject
    AnzeigeAusKatalog anzeigeAusKatalog;
    @Inject
    ErgänzeInKatalog ergänzeInKatalog;
    @Inject
    AktualisiereInKatalog aktualisiereInKatalog;
    @Inject
    LöscheInKatalog löscheInKatalog;


    public Team[] getAll() {
        return this.anzeigeAusKatalog.getAll();
    }

    public Team getOne(int id) {
        return this.anzeigeAusKatalog.getOne(id);
    }

    public Team update(int id, Team item) {
        return this.aktualisiereInKatalog.update(id,item);
    }

    public Team create(Team item) {
        return this.ergänzeInKatalog.create(item);
    }

    public Team delete(int id) {
        return  this.löscheInKatalog.delete(id);
    }
}
