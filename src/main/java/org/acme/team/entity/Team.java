package org.acme.team.entity;

import org.acme.rest.util.entity.BaseEntity;

public class Team extends BaseEntity {

    TeamAttribute[] attributes;

    public Team() {
        super();
    }

    public TeamAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(TeamAttribute[] attributes) {
        this.attributes = attributes;
    }

}
