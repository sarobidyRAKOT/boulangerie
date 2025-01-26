package mg.ITU.boulangerie.servlets.pages.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Client;
import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.beans.Vendeur;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/vente_venteDetail")
public class Vente extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Util_BD udb;
        Connection conn = null;
        try {
            udb = Util_BD.get_Instance();
            conn = udb.connect();

            ArrayList <Produit> produits = Produit.getAll(conn);
            ArrayList <Client> clients = Client.getAll(conn);
            ArrayList <Vendeur> vendeurs = Vendeur.getAll(conn);

            request.setAttribute("produits", produits);
            request.setAttribute("clients", clients);
            request.setAttribute("vendeurs", vendeurs);

            request.setAttribute("but", "pages/insertion/vente.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
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
