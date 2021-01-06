package db.callbacks;

import db.enums.DBResponse;

import java.util.Map;

public interface DBCallback {
    void onSuccess(Map<DBResponse, Object> res);
    void onFailure(Map<DBResponse, Object> res);
}
