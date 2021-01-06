package db.mappers;

import model.delegation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DelegationMapper {

    public static Delegation MySQLResponseToObject(ResultSet rs) {
        try {
            String name = rs.getString("name");
            switch (name) {
                case "Barcelona":
                    return new Barcelona(
                            Integer.parseInt(rs.getString("id")),
                            name
                            );
                case "Tarragona":
                    return new Tarragona(
                            Integer.parseInt(rs.getString("id")),
                            name
                    );
                case "Lleida":
                    return new Lleida(
                            Integer.parseInt(rs.getString("id")),
                            name
                    );
                case "Girona":
                    return new Girona(
                            Integer.parseInt(rs.getString("id")),
                            name
                    );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static String ObjectToMySQLQuery(Delegation delegation) {
        return "INSERT INTO Delegation (name) VALUES ("
                + '\'' + delegation.getName() + '\''
                + ");";
    }
}
