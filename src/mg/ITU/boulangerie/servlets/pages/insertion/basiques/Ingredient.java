package mg.ITU.boulangerie.servlets.pages.insertion.basiques;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Unite;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/ingredient")
public class Ingredient extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();            
            conn = udb.connect();

            ArrayList <Unite> unites = Unite.getAll(conn);
            request.setAttribute("unites", unites);

            conn.close();
            request.setAttribute("contenu", "basiques/ingredient.jsp");
                        
            request.setAttribute("but", "pages/insertion/basiques.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
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
