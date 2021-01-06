package db.repositories;

import db.DBConnector;
import model.pizza.Drink;
import model.pizza.Mass;

import java.util.List;

public class MassRepository extends BaseRepository<Mass> {
    private static MassRepository instance;

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
        return new GetByNameAsync(dbConnector, Mass.type).execute(name);
    }

    public List<Mass> getAllMasses(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, Mass.type).execute();
    }

    public void insertMass(DBConnector dbConnector, Mass drink) {
        new InsertOneAsync(dbConnector, Mass.type).execute(drink);
    }

    public void deleteMassById(DBConnector dbConnector, int massId) {
        new DeleteByIdAsync(dbConnector, Mass.type).execute(massId);
    }

    public void deleteMassByName(DBConnector dbConnector, String name) {
        new DeleteByNameAsync(dbConnector, Mass.type).execute(name);
    }

}
