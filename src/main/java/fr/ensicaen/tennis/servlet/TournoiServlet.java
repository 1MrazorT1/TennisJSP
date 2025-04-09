package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.bean.Tournoi;
import fr.ensicaen.tennis.persistence.TournoiDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/mesTournois")
public class TournoiServlet extends HttpServlet {
    private final TournoiDAO tournoiDAO = new TournoiDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Adherent adherent = (Adherent) req.getSession().getAttribute("adherent");

        if (adherent == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Tournoi> tournois = tournoiDAO.getTournoisInscrits(adherent);
        req.setAttribute("tournois", tournois);

        req.getRequestDispatcher("/WEB-INF/pages/mesTournois.jsp").forward(req, resp);
    }
}
