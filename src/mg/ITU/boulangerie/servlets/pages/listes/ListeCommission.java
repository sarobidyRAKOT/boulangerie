package mg.ITU.boulangerie.servlets.pages.listes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Vendeur;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeCommission")
public class ListeCommission extends HttpServlet {

    // private int size = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int page = 1;
        // if (request.getParameter("page") != null) {
        //     page = Integer.parseInt(request.getParameter("page"));
        // }
        // if (page <= 0) {page = 1;}

        String daty_debut = request.getParameter("daty_debut");
        String daty_fin = request.getParameter("daty_fin");
        String id_vendeur = request.getParameter("id_vendeur");



        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Vendeur> vendeurs = Vendeur.getAll(conn);
            ArrayList <Vendeur> vendeurs_commission = new ArrayList<>();
            // int offset = (page - 1) * size;

            System.out.println(daty_debut+" "+daty_fin+" "+id_vendeur);
            if (daty_debut != null && daty_fin != null && id_vendeur != null &&
                !daty_debut.equals("") && !daty_fin.equals("") && !id_vendeur.equals("")) {
                Vendeur vendeur = new Vendeur(Integer.parseInt(id_vendeur), "");
                    System.out.println("ATU");
                vendeurs_commission = vendeur.getAll_byPeriode_vendeur(conn, daty_debut, daty_fin);
            }

            // if (daty == null || daty.equals("")) {
            //     vente_clients = Vente_client.getAll(conn);
            // } else {
            //     vente_clients = Vente_client.getAll_bydate(conn, daty);
            // }
            
            // System.out.println("DATY "+daty);

            conn.close();

            request.setAttribute("vendeurs", vendeurs);
            request.setAttribute("vendeurs_commission", vendeurs_commission);
            request.setAttribute("page", page);

            request.setAttribute("but", "pages/listes/liste-commission.jsp");
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
