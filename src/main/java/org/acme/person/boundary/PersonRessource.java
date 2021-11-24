package org.acme.person.boundary;

import org.acme.utils.BaseRessource;
import java.util.Map;

public class PersonRessource implements BaseRessource {

    @Override
    public Object[] get() {
        return new Object[0];
    }

    @Override
    public Object getOne() {
        return null;
    }

    @Override
    public Object put(Object item) {
        return null;
    }

    @Override
    public Object post(Object item) {
        return null;
    }

    @Override
    public Object delete() {
        return null;
    }

    @Override
    public Object[] filter(Map items) {
        return new Object[0];
    }

    @Override
    public Object[] getPaginated(Map items) {
        return new Object[0];
    }
}
