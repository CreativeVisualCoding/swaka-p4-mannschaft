package org.acme.person.control;

import org.acme.person.entity.Person;
import org.acme.person.entity.PersonKatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AnzeigePersonAusKatalog {

    @Inject
    PersonKatalog personKatalog;

    public Person[] getAll() {
        return personKatalog.getAll();
    }

    public Person getOne(int id) {
        return personKatalog.getOne(id);
    }
}
