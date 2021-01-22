package db.mysql.managers;

import db.managers.MySQLEntityManager;
import db.model.DBObject;
import model.user.User;

import java.sql.Connection;

public class UserManager extends MySQLEntityManager<User> {
    private static UserManager instance;

    protected UserManager(Connection conn) {
        super(conn, DBObject.USER);
    }

    public static UserManager getInstance(Connection conn) {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager(conn);
                }
            }
        }
        return instance;
    }


    @Override
    public String getTable() {
        return "User";
    }


}
