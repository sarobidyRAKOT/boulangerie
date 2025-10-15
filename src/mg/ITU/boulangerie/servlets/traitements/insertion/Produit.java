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

@WebServlet (urlPatterns = "/insertion_produit")
public class Produit extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_categorie = (String) request.getParameter("id_categorie");
        String nom = (String) request.getParameter("nom");
        String id_unite = (String) request.getParameter("id_unite");
        String prix = (String) request.getParameter("prix");
        
        
        Connection conn = null;
        
        try {
            mg.ITU.boulangerie.beans.Produit p = new mg.ITU.boulangerie.beans.Produit(nom, prix, id_categorie, id_unite);
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);
            p.insert_setID (conn);

            Prix_vente.insert_currentDaty(conn, p.getId(), "NV PRODUIT", p.getPrix());
            conn.commit();
            conn.close();
            response.sendRedirect("produit");


        } catch (ClassNotFoundException | SQLException e) {
            try { conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        } catch (NullPointerException | NumberFormatException | Erreur e) {
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
