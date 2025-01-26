package mg.ITU.boulangerie.servlets.pages.listes;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.annotations.Recherche;
import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.beans.Produit_mois;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeProduit")
public class Produits extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] m = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin","Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        HashMap <String, String> mois = new HashMap<>();
        int index = 1;
        for (String string : m) {
            mois.put(""+index, string);
            ++ index;
        }
        LocalDate date = LocalDate.now();
        String month = request.getParameter("month");
        if (month == null || month.equals("")) {
            month = ""+date.getMonthValue();
        }
        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();

            ArrayList <Produit> produits = Produit_mois.getAll_byMois(conn, month);
            
            
            request.setAttribute("produits", produits);
            request.setAttribute("mois", mois);
            request.setAttribute("but", "pages/listes/liste-produit.jsp");
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
