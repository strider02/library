package transfers;

import java.io.Serializable;

public class Client implements Serializable {
    private int operation;
    private Object parameter;

    public Client() {
    }

    public Client(int operation, Object parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
