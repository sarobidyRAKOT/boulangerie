package mg.ITU.boulangerie.servlets.pages.production;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.display.Produit_detail;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/choisirProduit")
public class Choisir_produit extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Produit_detail> produits = Produit_detail.getAll (conn, null);

            conn.close();
            request.setAttribute("produits", produits);
            request.setAttribute("but", "pages/production/choisir_produit.jsp");
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
