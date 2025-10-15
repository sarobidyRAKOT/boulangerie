package mg.ITU.boulangerie.servlets.pages.insertion.basiques;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/produitMois")
public class Produit_mois extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();            
            conn = udb.connect();
            String[] m = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin","Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
            HashMap <String, String> mois = new HashMap<>();
            int index = 1;
            for (String string : m) {
                mois.put(""+index, string);
                ++ index;
            }
            
            ArrayList <mg.ITU.boulangerie.beans.Produit> produits = Produit.getAll(conn, null);
            
            request.setAttribute("mois", mois);
            request.setAttribute("produits", produits);
            conn.close();
            request.setAttribute("contenu", "basiques/produitMois.jsp");
                        
            request.setAttribute("but", "pages/insertion/basiques.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
            e.printStackTrace();
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
