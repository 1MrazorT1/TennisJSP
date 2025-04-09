package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("tennis");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Adherent adherent = new Adherent();
        adherent.setNom(nom);
        adherent.setEmail(email);
        adherent.setPasswordHash(password);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(adherent);
        em.getTransaction().commit();
        em.close();

        resp.sendRedirect("login.jsp");
    }
}