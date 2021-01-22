package db.callbacks;

import db.model.DBResponse;

public interface DBCallback {
    void onResponse(DBResponse DBResponse);
    void onFailure(Throwable t);
}
