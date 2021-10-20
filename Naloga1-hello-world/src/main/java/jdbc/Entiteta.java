package jdbc;

import java.io.Serializable;

public abstract class Entiteta implements Serializable {
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
