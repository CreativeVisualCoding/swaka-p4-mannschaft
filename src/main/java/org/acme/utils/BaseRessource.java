package org.acme.utils;

import java.util.Map;

public interface BaseRessource<T> {
    public T [] get();
    public T getOne();
    public T put(T item);
    public T post(T item);
    public T delete();
    public T [] filter(Map<T ,T> items);
    public T [] getPaginated(Map <T,T> items);

}
