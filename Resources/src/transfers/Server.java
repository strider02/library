package transfers;

import java.io.Serializable;

public class Server implements Serializable {
    private int status;
    private Object result;
    private String error;

    public Server() {
    }

    public Server(int status, Object result, String error) {
        this.status = status;
        this.result = result;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
