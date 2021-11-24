package org.acme.rest.util.entity;
public abstract class BaseEntity {
    int id;
    Response [] data;
    Response [] included;
    BaseLink [] links;
}
