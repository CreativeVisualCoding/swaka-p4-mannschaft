package org.acme.team.control;

import org.acme.team.entity.Team;

public interface CRUD {
    Team[] getAll();

    Team getOne(int id);

    Team update(int id, Team item);

    Team create(Team item);

    Team delete(int id);
}
