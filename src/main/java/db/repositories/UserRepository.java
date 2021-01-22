package db.repositories;

import db.callbacks.DBCallback;
import db.criteria.Criteria;
import db.DBConnector;
import db.interfaces.DBEntityManager;
import db.model.DBFilter;
import db.model.DBResponse;
import db.model.DBType;
import model.pizza.Pizza;
import model.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository extends BaseRepository<User> implements DBCallback {
    private static UserRepository instance;

    private List<User> users;

    private UserRepository() {
        this.users = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public void getUserById(DBConnector dbConnector, int userId, final DBCallback dbCallback) {
        new GetByIdAsync(dbConnector, User.TYPE, dbCallback).execute(userId);
    }

    public void getAllUsers(DBConnector dbConnector, final DBCallback dbCallback) {
        new GetAllAsync(dbConnector, User.TYPE, dbCallback).execute();
    }

    public void insertUser(DBConnector dbConnector, User user, final DBCallback dbCallback) {
        new InsertOneAsync(dbConnector, User.TYPE, dbCallback).execute(user);
    }

    public void deleteUserById(DBConnector dbConnector, int userId, final DBCallback dbCallback) {
        new DeleteByIdAsync(dbConnector, User.TYPE, dbCallback).execute(userId);
    }

    @Override
    public void onResponse(DBResponse DBResponse) {
        if (DBResponse.body() instanceof List) {
            this.users = (List<User>) DBResponse.body();
        }
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
