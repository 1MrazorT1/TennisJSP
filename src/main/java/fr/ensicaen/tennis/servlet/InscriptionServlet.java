package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.bean.Tournoi;
import fr.ensicaen.tennis.exception.AlreadyEnrolledException;
import fr.ensicaen.tennis.persistence.InscriptionDAO;
import fr.ensicaen.tennis.persistence.TournoiDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/inscrireTournoi")
public class InscriptionServlet extends HttpServlet {
    private final InscriptionDAO inscriptionDAO = new InscriptionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object idObj = (session != null) ? session.getAttribute("adherentId") : null;

        if (idObj == null) {
            req.setAttribute("error", "Vous devez être connecté pour vous inscrire à un tournoi.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        String tournoiParam = req.getParameter("tournoi");
        if (tournoiParam == null || tournoiParam.isBlank()) {
            req.setAttribute("error", "Aucun tournoi sélectionné.");
            req.getRequestDispatcher("InscriptionDejaFaite.jsp").forward(req, resp);
            return;
        }

        long idAdherent = Long.parseLong(idObj.toString());
        long idTournoi = Long.parseLong(tournoiParam);

        try {
            inscriptionDAO.inscrireAdherent(idTournoi, idAdherent);

            // Charger les tournois inscrits et les envoyer à la JSP
            TournoiDAO tournoiDAO = new TournoiDAO();
            Adherent adherent = new Adherent();
            adherent.setId(idAdherent); // Important pour l'identification dans la requête
            List<Tournoi> tournoisInscrits = tournoiDAO.getTournoisInscrits(adherent);
            req.setAttribute("tournois", tournoisInscrits);

            req.setAttribute("message", "Inscription réussie !");
            req.getRequestDispatcher("/WEB-INF/pages/mesTournois.jsp").forward(req, resp);
        } catch (AlreadyEnrolledException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("InscriptionDejaFaite.jsp").forward(req, resp);
        }

    }
}
