package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.model.DBResponse;
import model.delegation.Delegation;
import model.pizza.Drink;
import model.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class DelegationRepository extends BaseRepository<Delegation> implements DBCallback {
    private static DelegationRepository instance;

    private List<Delegation> delegations;

    private DelegationRepository() {
        this.delegations = new ArrayList<>();
    }

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
        return new GetByNameAsync(dbConnector, Delegation.TYPE, this).execute(name);
    }

    public List<Delegation> getAllDelegations(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Delegation.TYPE, this).execute();
    }

    public void insertDelegation(DBConnector dbConnector, Delegation delegation) {
        new InsertOneAsync(dbConnector, Delegation.TYPE, this).execute(delegation);
    }

    public void deleteDelegationById(DBConnector dbConnector, int delegationId) {
        new DeleteByIdAsync(dbConnector, Delegation.TYPE, this).execute(delegationId);
    }

    public void deleteDelegationByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Delegation.TYPE, this).execute(name);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            support.firePropertyChange("delegations", this.delegations, DBResponse.body());
            this.delegations = (List<Delegation>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
