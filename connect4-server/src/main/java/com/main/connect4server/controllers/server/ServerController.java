package com.main.connect4server.controllers.server;

import com.main.connect4server.database.DatabaseBroker;
import com.main.connect4server.operations.GetAllPlayersOperation;
import com.main.connect4server.operations.SignInOperation;
import com.main.connect4server.operations.SignUpOperation;
import com.main.connect4server.operations.UpdatePlayerOperation;
import com.main.connect4server.operations.generic.AbstractGenericOperation;
import com.main.connect4server.repositories.CustomRepository;
import com.main.connect4shared.domain.GenericEntity;

import java.util.List;

public class ServerController {
    private static ServerController instance;

    private CustomRepository repository;

    private ServerController() {
        repository = new DatabaseBroker();
    }

    public synchronized static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public GenericEntity signUp(GenericEntity entity) throws Exception {
        AbstractGenericOperation operation = new SignUpOperation();

        operation.execute(entity);

        return ((SignUpOperation) operation).getObject();
    }

    public GenericEntity signIn(GenericEntity entity) throws Exception {
        AbstractGenericOperation operation = new SignInOperation();

        operation.execute(entity);

        return ((SignInOperation) operation).getObject();
    }

    public List<GenericEntity> getAllPlayers(GenericEntity entity) throws Exception {
        AbstractGenericOperation operation = new GetAllPlayersOperation();

        operation.execute(entity);

        return ((GetAllPlayersOperation) operation).getObject();
    }

    public GenericEntity updatePlayerWins(GenericEntity entity) throws Exception {
        AbstractGenericOperation operation = new UpdatePlayerOperation();

        operation.execute(entity);

        return ((UpdatePlayerOperation) operation).getObject();
    }
}
