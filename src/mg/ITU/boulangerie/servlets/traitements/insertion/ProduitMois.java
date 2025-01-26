package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/insertion_produitMois")
public class ProduitMois extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String mois = request.getParameter("mois");
        int id_produit = Integer.parseInt(request.getParameter("id_produit"));

        // System.out.println("IO "+nom+" "+prix+" "+id_categorie+" "+id_unite);

        mg.ITU.boulangerie.beans.Produit_mois produit_mois = new mg.ITU.boulangerie.beans.Produit_mois(mois, id_produit);
        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);
            produit_mois.insert(conn);
            conn.commit();
            response.sendRedirect("produitMois");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("pages/errors/error-4004.jsp").forward(request, response);
        }
        finally {
            if (conn != null) {
                try { conn.close();
                } catch (SQLException e) {
                    e.printStackTrace(); 
                }
            }
        }
        
        // request.getRequestDispatcher("pages/p.jsp").forward(request, response);
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
