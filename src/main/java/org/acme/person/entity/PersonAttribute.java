package org.acme.person.entity;

import org.acme.utils.BaseAttribute;

public class PersonAttribute extends BaseAttribute {

    private String condition;

    public PersonAttribute() {
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
