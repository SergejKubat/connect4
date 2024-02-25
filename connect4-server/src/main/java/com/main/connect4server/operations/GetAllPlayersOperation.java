package com.main.connect4server.operations;

import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4shared.domain.GenericEntity;
import com.main.connect4shared.domain.Player;

import java.util.List;

public class GetAllPlayersOperation extends AbstractGenericOperation<Player> {
    List<GenericEntity> objects;

    @Override
    protected void validate(Player entity) throws Exception {
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        List<GenericEntity> players = super.repository.findAll(new Player());

        if (players != null) {
            objects = players;
        }
    }

    public List<GenericEntity> getObject() {
        return objects;
    }
}
