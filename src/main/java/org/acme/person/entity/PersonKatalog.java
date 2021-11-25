package org.acme.person.entity;



public interface PersonKatalog {
    public Person[] getAll();
    public Person getOne(int id);
    public Person create(Person person);
    public Person update(int id,Person person);
    public Person delete(int id);
}
