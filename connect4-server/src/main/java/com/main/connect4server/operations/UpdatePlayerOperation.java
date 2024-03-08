package com.main.connect4server.operations;

import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.domain.generic.GenericEntity;

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
