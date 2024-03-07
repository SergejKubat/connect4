package com.main.connect4server.operations.generic;

import com.main.connect4server.database.DatabaseBroker;
import com.main.connect4server.database.DatabaseRepository;
import com.main.connect4server.repositories.CustomRepository;
import com.main.connect4shared.domain.generic.GenericEntity;

public abstract class AbstractGenericOperation<T extends GenericEntity> {
    protected final CustomRepository repository;

    public AbstractGenericOperation() {
        this.repository = new DatabaseBroker();
    }

    public final void execute(T entity) throws Exception {
        try {
            validate(entity);
            startTransaction();
            executeOperation(entity);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    // Operation-specific method
    protected abstract void validate(T entity) throws Exception;

    private void startTransaction() throws Exception {
        ((DatabaseRepository) repository).connect();
    }

    // Operation-specific method
    protected abstract void executeOperation(T entity) throws Exception;

    private void commitTransaction() throws Exception {
        ((DatabaseRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DatabaseRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DatabaseRepository) repository).disconnect();
    }
}
