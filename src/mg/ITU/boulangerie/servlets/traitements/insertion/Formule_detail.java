package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mg.ITU.boulangerie.beans.Formule;
import mg.ITU.boulangerie.beans.Formule_detaile;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/insertion_formule_detail")
public class Formule_detail extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        String[] ingredients = request.getParameterValues("ingredients[]");
        String[] quantites = request.getParameterValues("quantites[]");
        Connection conn = null;
        HttpSession session = request.getSession();

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            conn.setAutoCommit(false);
            Formule formule = (Formule) session.getAttribute("formule");

            formule.insert_setID(conn);
            // System.out.println(formule.getId());
            Formule_detaile fd = new Formule_detaile(formule.getId());
            fd.insert(conn, ingredients, quantites);
            
            conn.commit();

            session.removeAttribute("formule");        
            response.sendRedirect("formule");
        //     response.sendRedirect("ingredient_produit");
        } catch (ClassNotFoundException | SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher("pages/errors/error-500.jsp").forward(request, response);
        }
        finally {
            if (conn != null) {
                try { conn.close();
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
