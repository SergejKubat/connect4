package com.main.connect4server.operations;

import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4server.utils.HashUtil;
import com.main.connect4shared.domain.generic.GenericEntity;
import com.main.connect4shared.domain.Player;
import com.main.connect4shared.utils.Validator;

public class SignUpOperation extends AbstractGenericOperation<Player> {
    GenericEntity object;

    @Override
    protected void validate(Player entity) throws Exception {
        String username = entity.getUsername();
        String email = entity.getEmail();
        String password = entity.getPassword();

        if (username == null || username.isEmpty()) {
            throw new Exception("Username is required.");
        }

        if (!Validator.validateUsername(username)) {
            throw new Exception("Username is not in valid format.");
        }

        if (email == null || email.isEmpty()) {
            throw new Exception("Email is required.");
        }

        if (!Validator.validateEmail(email)) {
            throw new Exception("Email is not in valid format.");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Password is required");
        }

        if (!Validator.validatePassword(password)) {
            throw new Exception("Password is not in valid format.");
        }
    }

    @Override
    protected void executeOperation(Player entity) throws Exception {
        // check if username exists
        Player player = (Player) super.repository.find(new Player(entity.getUsername()));

        if (player != null) {
            throw new RuntimeException("Username already exists.");
        }

        // hash password
        entity.setPassword(HashUtil.hash(entity.getPassword()));

        repository.insert(entity);

        object = super.repository.find(new Player(entity.getUsername()));
    }

    public GenericEntity getObject() {
        return this.object;
    }
}
