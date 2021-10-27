package Servlets;

import com.kumuluz.ee.common.config.EeConfig;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import jdbc.Uporabnik;
import jdbc.UporabnikDaoImpl;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.*;

@WebServlet("/servlet")
public class PrviJdbcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("Ime storitve, verzija in ime izvajalnega okolja:");
        ConfigurationUtil cfg = ConfigurationUtil.getInstance();
        Optional<String> imeStoritve = cfg.get("kumuluzee.name");
        Optional<String> verzijaOkolja = cfg.get("kumuluzee.version");
        Optional<String> imeOkolja = cfg.get("kumuluzee.env.name");
        resp.getWriter().println(imeStoritve.get().split("\\Q[\\E")[0]);
        resp.getWriter().println(verzijaOkolja.get().split("\\Q[\\E")[0]);
        resp.getWriter().println(imeOkolja.get().split("\\Q[\\E")[0]);

        UporabnikDaoImpl dao = new UporabnikDaoImpl();
        Uporabnik uporabnik = dao.vrni(1);
        resp.getWriter().println(uporabnik.getIme()+" "+uporabnik.getPriimek() + " "+ uporabnik.getUporabniskoIme());


        dao.vstavi(new Uporabnik("Jaz", "Ti", "On"));

        Uporabnik uporabnik3 = dao.vrni(3);
        resp.getWriter().println(uporabnik3.getIme()+" "+uporabnik3.getPriimek() + " "+ uporabnik3.getUporabniskoIme());

    }
}