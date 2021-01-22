package db.mappers;

import db.model.DBType;
import model.delegation.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DelegationMapper extends EntityMapper<Delegation>{
    public DelegationMapper(DBType dbType) {
        super(dbType);
    }

    @Override
    protected Delegation mySQLResponseToEntity(ResultSet rs) {
        try {
            String name = rs.getString("name");
            switch (name) {
                case "Barcelona":
                    return new Barcelona(
                            Integer.parseInt(rs.getString("id_delegation")),
                            name
                            );
                case "Tarragona":
                    return new Tarragona(
                            Integer.parseInt(rs.getString("id_delegation")),
                            name
                    );
                case "Lleida":
                    return new Lleida(
                            Integer.parseInt(rs.getString("id_delegation")),
                            name
                    );
                case "Girona":
                    return new Girona(
                            Integer.parseInt(rs.getString("id_delegation")),
                            name
                    );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    protected Delegation mongoResponseToEntity() {
        // to be implemented
        return null;
    }

    @Override
    public String mySQLInsert(Delegation delegation) {
        return "INSERT INTO Delegation (name) VALUES ("
                + '\'' + delegation.getName() + '\''
                + ");";
    }

    @Override
    public String mongoInsert(Delegation delegation) {
        return null;
    }

    @Override
    public String mySQLDelete(Delegation delegation) {
        return null;
    }

    @Override
    public String mongoDelete(Delegation delegation) {
        return null;
    }

    @Override
    public String mySQLGet(Delegation delegation) {
        return null;
    }

    @Override
    public String mongoGet(Delegation delegation) {
        return null;
    }

    @Override
    public String mySQLUpdate(Delegation delegation) {
        return null;
    }

    @Override
    public String mongoUpdate(Delegation delegation) {
        return null;
    }
}
