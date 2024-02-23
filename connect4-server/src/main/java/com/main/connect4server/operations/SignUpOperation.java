package com.main.connect4server.operations;

import com.main.connect4server.models.GenericEntity;
import com.main.connect4server.models.Player;
import com.main.connect4server.operations.generic.AbstractGenericOperation;

public class SignUpOperation extends AbstractGenericOperation<Player> {
    GenericEntity object;

    // @TODO: validate with regex
    @Override
    protected void validate(Player entity) throws Exception {
        if (entity.getUsername() == null || entity.getUsername().isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
            throw new Exception("Email is required.");
        }

        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            throw new Exception("Password is required");
        }
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        Player player = (Player) super.repository.find(new Player(entity.getUsername()));

        if (player != null) {
            throw new RuntimeException("Username already exists.");
        }

        Player newPlayer = new Player();

        newPlayer.setId(repository.insert(entity));

        object = newPlayer;
    }

    public GenericEntity getObject() {
        return this.object;
    }
}
