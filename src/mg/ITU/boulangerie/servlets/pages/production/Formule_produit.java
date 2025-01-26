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
import javax.servlet.http.HttpSession;

import mg.ITU.boulangerie.beans.Formule;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/formule_produit")
public class Formule_produit extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_produit = (String) request.getParameter("id_produit");

        HttpSession session = request.getSession();
        try {
            Util_BD udb = Util_BD.get_Instance();
            Connection conn = udb.connect();
            ArrayList <Formule> formules = Formule.getAll_byId_produit(conn, Integer.parseInt(id_produit));

            request.setAttribute("formules", formules);
            conn.close();

            session.setAttribute("production.id_produit", id_produit);
            
            // System.out.println("ID_PRODUIT "+session.getAttribute("production.id_produit"));

            request.setAttribute("but", "pages/production/formule_produit.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
