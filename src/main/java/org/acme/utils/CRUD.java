package org.acme.utils;

import org.acme.team.entity.Team;

public interface CRUD<T> {

    public T [] get();
    public T getOne();
    public T delete();
    public T post(T item);
    public T put(T item);
}
