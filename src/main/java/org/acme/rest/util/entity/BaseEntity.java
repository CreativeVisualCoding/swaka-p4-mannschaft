package org.acme.rest.util.entity;

public  class BaseEntity {
    public int id;
    public BaseLink links = new BaseLink();
    public Relationship rel = new Relationship();
}
