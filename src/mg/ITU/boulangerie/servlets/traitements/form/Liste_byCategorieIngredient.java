package mg.ITU.boulangerie.servlets.traitements.form;

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

import mg.ITU.boulangerie.beans.Formule;
import mg.ITU.boulangerie.beans.Formule_detaile;
import mg.ITU.boulangerie.beans.Produit;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/liste_byCategorieIngredient")
public class Liste_byCategorieIngredient extends HttpServlet  {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        String id_categorie = request.getParameter("id_categorie");
        String id_ingredient = request.getParameter("id_ingredient");

        System.out.println("where "+id_categorie+" "+id_ingredient);

        Connection conn = null;

        try {
            Util_BD udb = Util_BD.get_Instance();
            conn = udb.connect();

            ArrayList <Produit> produits = Produit.getAll_byCateg_Ingredient(conn, Integer.parseInt(id_categorie), Integer.parseInt(id_ingredient));
            request.setAttribute("produits", produits);

            conn.close();
            request.setAttribute("but", "pages/listes/liste-produit.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
