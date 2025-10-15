package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Unite {
    
    String id;
    String nom, ref;

    public Unite (String id, String nom, String ref) throws Exception {
        this.id = id;
        this.nom = nom;
        setRef(ref);
    }

    public static ArrayList <Unite> getAll (Connection conn) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Unite> unites = new ArrayList <> ();
        String req = "SELECT * FROM unite";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_unite");
                String nom = rs.getString("nom");
                String ref = rs.getString("reference");
                unites.add(new Unite(id, nom, ref));
            }
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unites;
    }


    
    public int insert_setID (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String req = "INSERT INTO unite (id_unite, nom, reference) VALUES (?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, nom);
            pstmt.setString(2, nom);
            pstmt.setString(3, ref);

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


    public String getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getRef() {
        return ref;
    }


    public void setId(String id) { this.id = id; }
    public void setRef(String ref) throws Exception  {
        
        if (ref != null && ref.length() > 10) {
            throw new Exception ("LE REFERENCE NE DOIS PAS ETRE TROP LONG 10 CARACTERES MAX");
        }
        this.ref = ref;
    }
}
