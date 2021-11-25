package org.acme.person.control;

import org.acme.person.entity.Person;
import org.acme.person.entity.PersonKatalog;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AktualisierePersonInKatalog {

    @Inject
    PersonKatalog personRepository;

    public Person update(int id, Person item) {
        return personRepository.update(id, item);
    }
}
