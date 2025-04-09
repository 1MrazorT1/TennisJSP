package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("tennis");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        EntityManager em = emf.createEntityManager();
        TypedQuery<Adherent> query = em.createQuery("SELECT a FROM Adherent a WHERE a.email = :email", Adherent.class);
        query.setParameter("email", email);

        try {
            Adherent adherent = query.getSingleResult();
            req.getSession().setAttribute("adherentId", adherent.getId());
            if (adherent.getPasswordHash().equals(password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("adherent", adherent);
                session.setAttribute("adherentId", adherent.getId());
                resp.sendRedirect("dashboard.jsp");
            } else {
                resp.sendRedirect("login.jsp?error=1");
            }
        } catch (NoResultException e) {
            resp.sendRedirect("login.jsp?error=1");
        } finally {
            em.close();
        }
    }
}
