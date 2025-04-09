package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Tournoi;
import fr.ensicaen.tennis.persistence.TournoiDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/listeTournois")
public class ListeTournoisServlet extends HttpServlet {
    private final TournoiDAO tournoiDAO = new TournoiDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Tournoi> tousLesTournois = tournoiDAO.getAllTournois();
        req.setAttribute("tournois", tousLesTournois);

        req.getRequestDispatcher("/WEB-INF/pages/listeTournois.jsp").forward(req, resp);
    }
}
