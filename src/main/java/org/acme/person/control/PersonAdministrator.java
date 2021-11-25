package org.acme.person.control;

import org.acme.person.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PersonAdministrator {
    @Inject
    AnzeigePersonAusKatalog anzeigePersonAusKatalog;
    @Inject
    ErgänzePersonInKatalog ergänzePersonInKatalog;
    @Inject
    AktualisierePersonInKatalog aktualisierePersonInKatalog;
    @Inject
    LöschePersonInKatalog löschePersonInKatalog;


    public Person[] getAll() {
        return this.anzeigePersonAusKatalog.getAll();
    }

    public Person getOne(int id) {
        return this.anzeigePersonAusKatalog.getOne(id);
    }

    public Person update(int id, Person person) {
        return this.aktualisierePersonInKatalog.update(id, person);
    }

    public Person create(Person person) {
        return this.ergänzePersonInKatalog.create(person);
    }

    public Person delete(int id) {
        return this.löschePersonInKatalog.delete(id);
    }

}
