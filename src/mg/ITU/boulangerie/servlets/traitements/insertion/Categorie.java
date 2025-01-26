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


@WebServlet (urlPatterns = "/insertion_categorie")
public class Categorie extends HttpServlet  {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String nom = request.getParameter("nom");

        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);
            
            mg.ITU.boulangerie.beans.Categorie categorie = new mg.ITU.boulangerie.beans.Categorie(null, nom);
            categorie.insert_setID(conn);
            conn.commit();
            conn.close();
            response.sendRedirect("categorie");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        }
        

        /**
         * TRAITEMENT INSERTION PRODUCTION
         * 
         * Sorti stock ingredient
         * calucl prix de production
         * redirection vers une page pouyr afficher les details de la production
         */

         
        

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
