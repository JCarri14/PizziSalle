package db.managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

public abstract class MySQLManager<Param> extends DBObjectManager<Param> {
    protected Connection conn;
    protected String query;
    protected Statement stt;
    protected ResultSet rs;

    public MySQLManager(Connection conn) {
        this.conn = conn;
    }

    protected String getQueryFilters(Map<String, String> filters) {
        StringBuilder builder = new StringBuilder();
        Iterator<Map.Entry<String, String>> itr = filters.entrySet().iterator();
        if (!filters.isEmpty()) {
            builder.append(" WHERE ");
            while(itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                builder.append(entry.getKey())
                        .append("= \"")
                        .append(entry.getValue())
                        .append("\"");
                if (itr.hasNext()) builder.append(" AND ");
            }
        }
        builder.append(";");
        return builder.toString();
    }
}
