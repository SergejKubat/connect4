package com.main.connect4server.operations;

import com.main.connect4server.models.GenericEntity;
import com.main.connect4server.models.Player;
import com.main.connect4server.operations.generic.AbstractGenericOperation;

public class UpdatePlayerOperation extends AbstractGenericOperation<Player> {
    GenericEntity object;

    @Override
    protected void validate(Player entity) throws Exception {
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        super.repository.update(entity);
    }

    public GenericEntity getObject() {
        return object;
    }
}
