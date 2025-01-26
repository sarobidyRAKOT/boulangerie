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

import mg.ITU.boulangerie.beans.Formule_detaile;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/detail_formule")
public class Detail_formule extends HttpServlet  {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String id_formule = (String) request.getParameter("id_formule");
        HttpSession session = request.getSession();

        try {
            Util_BD udb = Util_BD.get_Instance();
            Connection conn = udb.connect();
            
            ArrayList <Formule_detaile> formule_detailes = Formule_detaile.getAll_byId_formule(conn, Integer.parseInt(id_formule));
            
            request.setAttribute("id_produit", session.getAttribute("production.id_produit"));
            request.setAttribute("formule_detailes", formule_detailes);
            
            conn.close();

            request.setAttribute("id_formule", id_formule);
            request.setAttribute("but", "pages/production/detail_formule.jsp");
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
