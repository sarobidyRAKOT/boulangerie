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

import mg.ITU.boulangerie.beans.Vente_client;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/listeVenteClient")
public class VenteClient extends HttpServlet {

    private int size = 10;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int page = 1;
        
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (page <= 0) {page = 1;}

        String daty = request.getParameter("daty");

        Connection conn = null;
        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Vente_client> vente_clients = new ArrayList<>();
            // int offset = (page - 1) * size;

            if (daty == null || daty.equals("")) {
                vente_clients = Vente_client.getAll(conn);
            } else {
                vente_clients = Vente_client.getAll_bydate(conn, daty);
            }
            
            // System.out.println("DATY "+daty);

            conn.close();

            request.setAttribute("vente_clients", vente_clients);
            request.setAttribute("page", page);

            request.setAttribute("but", "pages/listes/liste-venteClient.jsp");
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
