package org.acme.utils;

import javax.ws.rs.core.Response;
import java.util.Map;

public interface BaseRessource<T> {
    public Response get();

    public Response getOne(int id);

    public Response put(int id, T item);

    public Response post(T item);

    public Response delete(int id);
}
