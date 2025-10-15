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

import mg.ITU.boulangerie.beans.Prix_vente;
import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeHistoriquePrixProduit")
public class HistoriquePrixProduit extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // int page = 1;
        // if (request.getParameter("page") != null) {
        //     page = Integer.parseInt(request.getParameter("page"));
        // }
        // if (page <= 0) {page = 1;}

        String id_produit = request.getParameter("id_produit");

        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            
            ArrayList <Produit> produits = Produit.getAll (conn, null);
            ArrayList <Prix_vente> prix_ventes = new ArrayList<>();
            
            
            if (id_produit != null && !id_produit.equals("")) {
                prix_ventes = Prix_vente.getHistorique_byID(conn, "WHERE pv.id_produit = "+Integer.parseInt(id_produit));
            }  else {
                prix_ventes = Prix_vente.getHistorique_byID(conn, null);
            }         
         

            conn.close();


            request.setAttribute("produits", produits);
            request.setAttribute("prix_ventes", prix_ventes);
            request.setAttribute("but", "pages/listes/liste-historiquePrixVente.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            request.setAttribute("but", "pages/errors/error-404.jsp");
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
