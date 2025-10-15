package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categorie {
    
    String id;
    String nom;

    public Categorie (String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public static ArrayList <Categorie> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Categorie> cataegs = new ArrayList <> ();
        String req = "SELECT * FROM categorie";
        try {
            pstmt = conn.prepareStatement(req);
            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_categorie");
                String nom = rs.getString("nom");
                cataegs.add(new Categorie(id, nom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return cataegs;
    }

    public int insert_setID (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String req = "INSERT INTO categorie (nom) VALUES (?)";
        try {
            pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, nom);

            int nbr = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                // Retourner l'ID généré
                this.setId(rs.getString(1));
            } 
            return nbr;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }

    public String getId() {return id; }
    public String getNom() {return nom; }

    public void setId(String id) {this.id = id; }

}
