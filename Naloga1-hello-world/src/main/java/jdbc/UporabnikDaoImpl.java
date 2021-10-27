package jdbc;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UporabnikDaoImpl implements BaseDao<Uporabnik> {
    private static UporabnikDaoImpl daoInstance;
    Connection con = null;
    private Logger log = Logger.getLogger(UporabnikDaoImpl.class.getName());

    @Override
    public Connection getConnection() {
        try {
            InitialContext initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("jdbc/SimpleJdbcDS");
            return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Uporabnik getUporabnikFromRS(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String ime = rs.getString("ime");
        String priimek = rs.getString("priimek");
        String uporabniskoIme = rs.getString("uporabniskoime");
        var user = new Uporabnik(ime, priimek, uporabniskoIme);
        user.setId(id);
        return user;
    }

    @Override
    public Uporabnik vrni(int id) {
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return getUporabnikFromRS(rs);
            } else {
                log.info("Uporabnik ne obstaja");
            }

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
        return null;
    }

    @Override
    public void vstavi(Uporabnik user) {
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "INSERT INTO uporabnik (id, ime, priimek, uporabniskoime) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setInt(1, user.getId());
            ps.setString(2, user.getIme());
            ps.setString(3, user.getPriimek());
            ps.setString(4, user.getUporabniskoIme());

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void odstrani(int id) {
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "DELETE FROM uporabnik WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public void posodobi(Uporabnik user) {
        PreparedStatement ps = null;

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "UPDATE uporabnik SET ime=?, priimek=?, uporabniskoime=? WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getIme());
            ps.setString(2, user.getPriimek());
            ps.setString(3, user.getUporabniskoIme());
            ps.setInt(4, user.getId());
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            log.severe(e.toString());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }

    @Override
    public List<Uporabnik> vrniVse() {
        PreparedStatement ps = null;

        var result = new ArrayList<Uporabnik>();

        try {
            if (con == null) {
                con = getConnection();
            }

            String sql = "SELECT * FROM uporabnik";
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                result.add(getUporabnikFromRS(rs));
            }

            return result;

        } catch (SQLException e) {
            log.severe(e.toString());
            return result;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.severe(e.toString());
                }
            }
        }
    }
}
