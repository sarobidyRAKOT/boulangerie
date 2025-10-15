package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Erreur;
import mg.ITU.boulangerie.beans.Prix_vente;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/insertion_PrixVente")
public class PrixVente extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_produit = request.getParameter("id_produit");
        String daty = (String) request.getParameter("daty");
        String prix = request.getParameter("prix");
        String motif = request.getParameter("motif");

        Connection conn = null;
        Prix_vente prix_vente;

        
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);
            prix_vente = new Prix_vente(id_produit, motif, daty, prix);

            prix_vente.insert(conn);

            conn.commit();
            conn.close();
            response.sendRedirect("PrixVente");

        } catch (ClassNotFoundException | SQLException e) {
            try { conn.rollback(); System.out.println("ROLL BACK");
            } catch (SQLException e1) { e1.printStackTrace();
            }
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        } catch (Erreur | IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("pages/errors/error-500.jsp").forward(request, response);
        }
        


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
