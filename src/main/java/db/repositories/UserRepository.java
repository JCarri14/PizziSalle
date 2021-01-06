package db.repositories;

import db.DBConnector;
import db.managers.DBObjectManager;
import model.user.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends BaseRepository<User> {
    private static UserRepository instance;

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

    public User getUserById(DBConnector dbConnector, int userId) {
        return new GetByIdAsync(dbConnector, User.type).execute(userId);
    }

    public List<User> getAllUsers(DBConnector dbConnector) {
        return new GetAllAsync(dbConnector, User.type).execute();
    }

    public void insertUser(DBConnector dbConnector, User user) {
        new InsertOneAsync(dbConnector, User.type).execute(user);
    }

    public void deleteUserById(DBConnector dbConnector, int userId) {
        new DeleteByIdAsync(dbConnector, User.type).execute(userId);
    }

    private static class GetUserByIdAsync extends AsyncTask<Integer,User> {

        public GetUserByIdAsync(DBConnector dbConnector) {
            super(dbConnector);
        }

        @Override
        public User doInBackground(Integer... integers) {
            try {
                DBObjectManager manager = getManager(dbConnector, User.type);
                Map<String, String> filters = new HashMap<>();
                filters.put("id", String.valueOf(integers[0]));
                manager.get(filters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }
}
