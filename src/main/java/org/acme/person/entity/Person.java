package org.acme.person.entity;

import org.acme.rest.util.entity.BaseEntity;

public class Person extends BaseEntity {

    private PersonAttribute[] attributes;

    public Person() {
    }

    public PersonAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(PersonAttribute[] attributes) {
        this.attributes = attributes;
    }
}
