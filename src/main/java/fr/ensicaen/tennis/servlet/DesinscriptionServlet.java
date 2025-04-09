package fr.ensicaen.tennis.servlet;

import java.io.IOException;

import fr.ensicaen.tennis.persistence.InscriptionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/desinscrireTournoi")
public class DesinscriptionServlet extends HttpServlet {
    private final InscriptionDAO inscriptionDAO = new InscriptionDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object idObj = (session != null) ? session.getAttribute("adherentId") : null;

        if (idObj == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        long idAdherent = Long.parseLong(idObj.toString());
        long idTournoi = Long.parseLong(req.getParameter("idTournoi"));

        inscriptionDAO.desinscrireAdherent(idTournoi, idAdherent);
        resp.sendRedirect("mesTournois");
    }
}