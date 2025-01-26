package mg.ITU.boulangerie.servlets.pages.insertion.basiques;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet (urlPatterns = "/categorie")
public class Categorie extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("contenu", "basiques/categorie.jsp");
        request.setAttribute("but", "pages/insertion/basiques.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
        // Util_BD udb;
        // Connection conn = null;
        // try {
        //     udb = Util_BD.get_Instance();
        //     conn = udb.connect();
        //     // ArrayList <Produit> produits = Produit.getAll(conn);

        //     // mg.ITU.boulangerie.beans.Formule formule = (mg.ITU.boulangerie.beans.Formule) session.getAttribute("formule");
            
            
        //     // request.setAttribute("formule", formule); // DONNER POUR LE BTN RETOUR
        //     // request.setAttribute("produits", produits);
            
        //     conn.close();



        // } catch (ClassNotFoundException | SQLException e) {
        //     e.printStackTrace();
        //     request.setAttribute("error", e);
        //     request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
        // }

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
