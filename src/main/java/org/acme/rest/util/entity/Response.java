package org.acme.rest.util.entity;

import java.util.Map;

public abstract class Response {

    public String jsonapi;
    public Map<String, String> meta;
    Error [] error;
    PaginationLink link;

}
