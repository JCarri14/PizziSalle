package db.interfaces;

import java.util.Map;

public interface DBCallback {
    void onSuccess(Map<String, Object> res);
    void onFailure(Map<String, Object> res);
}
