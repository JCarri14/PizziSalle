package db.repositories;

import db.DBConnector;
import model.delegation.Delegation;
import model.pizza.Drink;

import java.util.List;

public class DelegationRepository extends BaseRepository<Delegation> {
    private static DelegationRepository instance;

    public static DelegationRepository getInstance() {
        if (instance == null) {
            synchronized (DelegationRepository.class) {
                if (instance == null) {
                    instance = new DelegationRepository();
                }
            }
        }
        return instance;
    }

    public Delegation getDelegationByName(DBConnector dbConnector, String name) {
        return new GetByNameAsync(dbConnector, Delegation.type).execute(name);
    }

    public List<Delegation> getAllDelegations(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Delegation.type).execute();
    }

    public void insertDelegation(DBConnector dbConnector, Delegation delegation) {
        new InsertOneAsync(dbConnector, Delegation.type).execute(delegation);
    }

    public void deleteDelegationById(DBConnector dbConnector, int delegationId) {
        new DeleteByIdAsync(dbConnector, Delegation.type).execute(delegationId);
    }

    public void deleteDelegationByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Delegation.type).execute(name);
    }

}
