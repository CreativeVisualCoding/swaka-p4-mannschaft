package org.acme.person.control;

import org.acme.person.entity.Person;
import org.acme.person.entity.PersonKatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class Erg√§nzePersonInKatalog {

    @Inject
    PersonKatalog personKatalog;
    public Person create(Person item) {
        return personKatalog.create(item);
    }
}
