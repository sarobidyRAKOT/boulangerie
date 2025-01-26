package mg.ITU.boulangerie.servlets.traitements.insertion;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet (urlPatterns = "/insertion_formule")
public class Formule extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        Date daty = Date.valueOf((String) request.getParameter("daty"));
        String description = (String) request.getParameter("description");
        Integer quantite_produite = Integer.parseInt(request.getParameter("quantite_produite"));
        Integer id_produit = Integer.parseInt(request.getParameter("id_produit"));


        mg.ITU.boulangerie.beans.Formule formule = new mg.ITU.boulangerie.beans.Formule();
        formule.setDaty(daty);
        formule.setDescription(description);
        formule.setQuantite_produite(quantite_produite);
        formule.setId_produit(id_produit);

        HttpSession session = request.getSession();
        session.setAttribute("formule", formule);
        response.sendRedirect("formule_detail");

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
