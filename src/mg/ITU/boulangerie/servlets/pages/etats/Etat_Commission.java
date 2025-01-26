package mg.ITU.boulangerie.servlets.pages.etats;

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

import mg.ITU.boulangerie.beans.Categorie;
import mg.ITU.boulangerie.beans.Vendeur;
import mg.ITU.boulangerie.beans.Vente_detail;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/etatCommission")
public class Etat_Commission extends HttpServlet {

    private int size = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // int page = 1;
        // if (request.getParameter("page") != null) {
        //     page = Integer.parseInt(request.getParameter("page"));
        // }
        // if (page <= 0) {page = 1;}

        // String id_categorie = request.getParameter("id_categorie");
        // if (id_categorie != null) { session.setAttribute("id_categorie", id_categorie);}
        String date_debut = request.getParameter("date_debut");
        String date_fin = request.getParameter("date_fin");

        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Vendeur> vendeurs = new ArrayList<>();

            if (date_debut != null && !date_debut.equals("") &&
            date_fin != null && !date_fin.equals("")) {
                vendeurs = Vendeur.getEtat_parGenredans_Periode(conn, date_debut, date_fin);
            } else {                
                vendeurs = Vendeur.getEtat_parGenre(conn);
            }


            conn.close();



            // request.setAttribute("vente_details", vente_details);
            // request.setAttribute("page", page);
            request.setAttribute("vendeurs", vendeurs);

            request.setAttribute("but", "pages/etats/etat-commission.jsp");
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
