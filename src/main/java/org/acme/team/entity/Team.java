package org.acme.team.entity;

import org.acme.person.entity.Person;
import org.acme.rest.util.entity.BaseEntity;

public class Team extends BaseEntity {

    private TeamAttribute[] attributes;
    private Person manager;
    private Person[] spieler;

    public Team() {
        super();
    }

    public TeamAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(TeamAttribute[] attributes) {
        this.attributes = attributes;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }

    public Person[] getSpieler() {
        return spieler;
    }

    public void setSpieler(Person[] spieler) {
        this.spieler = spieler;
    }
}
