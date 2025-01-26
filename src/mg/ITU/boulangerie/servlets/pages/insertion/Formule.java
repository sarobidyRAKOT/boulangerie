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
import javax.servlet.http.HttpSession;

import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/formule")
public class Formule extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        Util_BD udb;
        Connection conn = null;
        try {
            udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Produit> produits = Produit.getAll(conn);

            mg.ITU.boulangerie.beans.Formule formule = (mg.ITU.boulangerie.beans.Formule) session.getAttribute("formule");
            
            
            request.setAttribute("formule", formule); // DONNER POUR LE BTN RETOUR
            request.setAttribute("produits", produits);
    
            request.setAttribute("but", "pages/insertion/formule.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
