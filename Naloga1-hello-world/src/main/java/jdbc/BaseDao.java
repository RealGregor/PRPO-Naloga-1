package jdbc;

import java.sql.Connection;
import java.util.List;

public interface BaseDao <T extends Entiteta>{
    Connection getConnection();

    T vrni(int id);

    void vstavi(T ent);

    void odstrani(int id);

    void posodobi(T ent);

    List<T> vrniVse();
}
