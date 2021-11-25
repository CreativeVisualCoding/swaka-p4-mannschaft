package org.acme.utils;

import javax.ws.rs.core.Response;
import java.util.Map;

public interface BaseRessource<T> {
    public Response get();

    public Response getOne(int id, String include);

    public Response put(T item);

    public Response post(T item);

    public Response delete(int id);
}
