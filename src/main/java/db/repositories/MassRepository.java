package db.repositories;

import db.DBConnector;
import db.callbacks.DBCallback;
import db.model.DBResponse;
import model.pizza.Drink;
import model.pizza.Mass;
import model.pizza.Pizza;

import java.util.ArrayList;
import java.util.List;

public class MassRepository extends BaseRepository<Mass> implements DBCallback {
    private static MassRepository instance;

    private List<Mass> masses;

    private MassRepository() {
        masses = new ArrayList<>();
    }

    public static MassRepository getInstance() {
        if (instance == null) {
            synchronized (MassRepository.class) {
                if (instance == null) {
                    instance = new MassRepository();
                }
            }
        }
        return instance;
    }

    public Mass getMassByName(DBConnector dbConnector, String name) {
        return new GetByNameAsync(dbConnector, Mass.TYPE, this).execute(name);
    }

    public List<Mass> getAllMasses(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Mass.TYPE, this).execute();
    }

    public void insertMass(DBConnector dbConnector, Mass drink) {
        new InsertOneAsync(dbConnector, Mass.TYPE, this).execute(drink);
    }

    public void deleteMassById(DBConnector dbConnector, int massId) {
        new DeleteByIdAsync(dbConnector, Mass.TYPE, this).execute(massId);
    }

    public void deleteMassByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Mass.TYPE, this).execute(name);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            support.firePropertyChange("masses", this.masses, DBResponse.body());
            this.masses = (List<Mass>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
