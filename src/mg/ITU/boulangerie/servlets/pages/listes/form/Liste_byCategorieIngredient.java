package mg.ITU.boulangerie.servlets.pages.listes.form;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mg.ITU.boulangerie.beans.Categorie;
import mg.ITU.boulangerie.beans.Ingredient;
import mg.ITU.boulangerie.utils.Util_BD;

@WebServlet (urlPatterns = "/liste-by-categorie_ingredient")
public class Liste_byCategorieIngredient extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        

        Util_BD udb;
        Connection conn = null;
        try {
            udb = Util_BD.get_Instance();
            conn = udb.connect();
            ArrayList <Ingredient> ingredients = Ingredient.getAll(conn);
            ArrayList <Categorie> categories = Categorie.getAll(conn);

            request.setAttribute("ingredients", ingredients);
            request.setAttribute("categories", categories);
    
            request.setAttribute("but", "pages/listes/form/liste-by-categorie_ingredient.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            request.getRequestDispatcher("pages/errors/error-404.jsp").forward(request, response);
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
