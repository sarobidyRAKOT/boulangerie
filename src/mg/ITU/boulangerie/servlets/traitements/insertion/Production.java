package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet (urlPatterns = "/insertion_production")
public class Production extends HttpServlet  {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();


        Integer n_ingredient = Integer.parseInt(request.getParameter("n_ingredient"));
        Integer quantite_produite = Integer.parseInt(request.getParameter("quantite_produite"));
        Date daty = Date.valueOf(request.getParameter("daty"));
        String id_produit = (String) session.getAttribute("production.id_produit");
        String id_formule = (String) session.getAttribute("production.id_formule");

        /**
         * TRAITEMENT INSERTION PRODUCTION
         * 
         * Sorti stock ingredient
         * calucl prix de production
         * redirection vers une page pouyr afficher les details de la production
         */

         
        

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
