package mg.ITU.boulangerie.servlets.pages.listes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.beans.Produit_mois;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeHistoriqueProduitsMois2024")
public class HistoriqueProduitsMois2024 extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();

            ArrayList <Produit> produits = Produit_mois.getHistorique_2024(conn);
            
            
            request.setAttribute("produits", produits);
            request.setAttribute("but", "pages/listes/Liste-historique2024.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
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
