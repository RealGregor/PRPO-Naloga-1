package jdbc;

import java.sql.Connection;
import java.util.List;

public class UporabnikDaoImpl implements BaseDao{
    private static UporabnikDaoImpl daoInstance;

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public Entiteta vrni(int id) {
        return null;
    }

    @Override
    public void vstavi(Entiteta ent) {

    }

    @Override
    public void odstrani(int id) {

    }

    @Override
    public void posodobi(Entiteta ent) {

    }

    @Override
    public List<Entiteta> vrniVse() {
        return null;
    }
}
