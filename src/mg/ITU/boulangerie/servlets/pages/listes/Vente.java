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
import javax.servlet.http.HttpSession;

import mg.ITU.boulangerie.beans.Categorie;
import mg.ITU.boulangerie.beans.Vente_detail;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeVente")
public class Vente extends HttpServlet {

    private int size = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (page <= 0) {page = 1;}

        String id_categorie = request.getParameter("id_categorie");
        if (id_categorie != null) { session.setAttribute("id_categorie", id_categorie);}
        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Categorie> categories = Categorie.getAll (conn);
            ArrayList <Vente_detail> vente_details = new ArrayList<>();
            int offset = (page - 1) * size;

            id_categorie = (String) session.getAttribute("id_categorie");

            if (id_categorie != null && !id_categorie.equals("")) {
                vente_details = Vente_detail.getbyIDCategorie_detailListe(conn, offset, size, Integer.parseInt(id_categorie));
            } else {                
                vente_details = Vente_detail.getAll_detailListe(conn, offset, size);
            }


            conn.close();

            request.setAttribute("vente_details", vente_details);
            request.setAttribute("page", page);
            request.setAttribute("categories", categories);
            request.setAttribute("but", "pages/listes/liste-vente.jsp");
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
