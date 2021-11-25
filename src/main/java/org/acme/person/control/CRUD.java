package org.acme.person.control;

import org.acme.person.entity.Person;

public interface CRUD {
    Person[] getAll();

    Person getOne(int id);

    Person update(int id, Person person);

    Person create(Person person);

    Person delete(int id);
}
