package com.main.connect4server.operations;

import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4shared.domain.GenericEntity;
import com.main.connect4shared.domain.Player;

public class SignInOperation extends AbstractGenericOperation<Player> {
    GenericEntity object;

    // @TODO: validate with regex
    @Override
    protected void validate(Player entity) throws Exception {
        if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new Exception("Password is required.");
        }
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        Player player = (Player) super.repository.find(new Player(entity.getUsername()));

        if (player == null) {
            throw new RuntimeException("Invalid username");
        }

        if (!player.getPassword().equals(entity.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        object = player;
    }

    public GenericEntity getObject() {
        return object;
    }
}
