package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Ingredient {


    Integer id;
    String id_unite;
    String nom;


    public Ingredient (Integer id, String nom, String id_unite) {
        this.setId(id);
        setNom(nom);
        this.setId_unite(id_unite);
    }
    public Ingredient (String nom, String id_unite) {
        setNom(nom);
        this.setId_unite(id_unite);
    }
    public Ingredient () {}



        public static ArrayList <Ingredient> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Ingredient> ingredients = new ArrayList <> ();
        String req = "SELECT * FROM ingredient";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_ingredient");
                String nom = rs.getString("nom");
                String id_unite = rs.getString ("id_unite");

                ingredients.add(new Ingredient(id, nom, id_unite));
                // unites.add(new Ingredient(id, nom, ref));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return ingredients;
    }


    public int insert (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO ingredient (nom, id_unite) VALUES (?, ?)";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getId_unite());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }



    public Integer getId() { return id; }
    public String getId_unite() { return id_unite; }
    public String getNom() { return nom; }

    public void setId(Integer id) { this.id = id; }
    public void setId_unite(String id_unite) { this.id_unite = id_unite; }
    public void setNom(String nom) { this.nom = nom; }
    
}
