package com.main.connect4server.operations;

import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4server.utils.HashUtil;
import com.main.connect4shared.domain.generic.GenericEntity;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.utils.Validator;

public class SignInOperation extends AbstractGenericOperation<Player> {
    GenericEntity object;

    @Override
    protected void validate(Player entity) throws Exception {
        String username = entity.getUsername();
        String password = entity.getPassword();

        if (username == null || username.isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (!Validator.validateUsername(username)) {
            throw new Exception("Username is not in valid format.");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Password is required.");
        }

        if (!Validator.validatePassword(password)) {
            throw new Exception("Password is not in valid format.");
        }
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        Player player = (Player) super.repository.find(new Player(entity.getUsername()));

        if (player == null) {
            throw new RuntimeException("Invalid username and/or password.");
        }

        String providedPassword = HashUtil.hash(entity.getPassword());
        String storedPassword = player.getPassword();

        if (!providedPassword.equals(storedPassword)) {
            throw new RuntimeException("Invalid username and/or password.");
        }

        object = player;
    }

    public GenericEntity getObject() {
        return object;
    }
}
