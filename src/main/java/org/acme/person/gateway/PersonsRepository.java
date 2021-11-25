package org.acme.person.gateway;

import org.acme.person.entity.Person;
import org.acme.person.entity.PersonAttribute;
import org.acme.person.entity.PersonKatalog;
import org.acme.person.entity.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class PersonsRepository implements PersonKatalog {
    int nextId = 1;
    Map<Integer, Person> persons = new HashMap<Integer, Person>();

    public PersonsRepository() {
        Person person = new Person();
        Person person2 = new Person();
        PersonAttribute attr = new PersonAttribute();
        attr.setName("Person - Name");
        attr.setCondition("Person - Condition");
        PersonAttribute[] attributes = {attr};
        person.setAttributes(attributes);
        person2.setAttributes(attributes);
        create(person);
        create(person2);
    }

    @Override
    public Person[] getAll() {
        return this.persons.values().toArray(new Person[0]);
    }

    @Override
    public Person getOne(int id) {
        return persons.get(id);
    }

    @Override
    public Person create(Person person) {
        person.setId(nextId++);
        persons.put(person.getId(), person);
        return getOne(person.getId());
    }

    @Override
    public Person update(int id, Person person) {
        if (getOne(id) == null) return null;
        person.setId(id);
        persons.put(id, person);
        return getOne(id);
    }

    @Override
    public Person delete(int id) {
        return persons.remove(id);
    }
}
