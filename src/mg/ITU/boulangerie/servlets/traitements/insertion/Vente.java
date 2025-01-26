package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Vente_detail;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/insertion_vente")
public class Vente extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        String[] id_produit = request.getParameterValues("id_produit[]");
        String[] quantite_produit = request.getParameterValues("quantite_produit[]");
        String[] prix_unitaire = request.getParameterValues("prix_unitaire[]");
        String daty = (String) request.getParameter("daty");
        String id_client = request.getParameter("id_client");
        String id_vendeur = request.getParameter("id_vendeur");
        
        double prix_ttl = 0;
        for (int i = 0; i < id_produit.length; ++ i) {
            prix_ttl += Double.parseDouble(prix_unitaire[i]) * Integer.parseInt(quantite_produit[i]);
        }
        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);

            mg.ITU.boulangerie.beans.Vente vente = new mg.ITU.boulangerie.beans.Vente(null, prix_ttl, daty, Integer.parseInt(id_client));    
            vente.setId_vendeur(Integer.parseInt(id_vendeur));
            vente.insert_setID(conn); // insertion

            Vente_detail.insert(conn, quantite_produit, prix_unitaire, id_produit, vente.getId());

            conn.commit();
            conn.close();
            response.sendRedirect("vente_venteDetail");

        } catch (ClassNotFoundException | SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            request.setAttribute("error", e);
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
