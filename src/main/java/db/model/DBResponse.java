package db.model;

public class DBResponse<Param> {
    private DBResponseCode code;
    private Param body;

    public DBResponse() {}

    public DBResponse(DBResponseCode code, Param body) {
        this.code = code;
        this.body = body;
    }

    public int code() {
        return this.code.value();
    }

    public void setCode(DBResponseCode code) {
        this.code = code;
    }

    public Param body() {
        return this.body;
    }

    public void setBody(Param body) {
        this.body = body;
    }

    public boolean isSuccessful() {
        return code == DBResponseCode.OK || code == DBResponseCode.CREATED || code == DBResponseCode.ACCEPTED;
    }

    public DBResponseCode getCode() {
        return code;
    }

    public Param getBody() {
        return body;
    }
}
