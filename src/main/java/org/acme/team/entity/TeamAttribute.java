package org.acme.team.entity;

import org.acme.utils.BaseAttribute;

public class TeamAttribute extends BaseAttribute {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
