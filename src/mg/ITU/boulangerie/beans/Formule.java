package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Formule {
    
    Integer id, quantite_produite, id_produit;
    Date daty;
    String description;


    public Formule () {}
    public Formule (Integer id, Date daty, String desc, Integer qtt_produite, Integer id_produit) {
        this.setId(id);
        this.setDescription(desc);
        this.setDaty(daty);
        this.setQuantite_produite(qtt_produite);
        this.setId_produit(id_produit);
    }



        
    public int insert_setID (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String req = "INSERT INTO formule (daty, description, quantite_produite, id_produit) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, this.getDaty());
            pstmt.setString(2, this.getDescription());
            pstmt.setInt(3, this.getQuantite_produite());
            pstmt.setInt(4, this.getId_produit());

            int nbr = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                // Retourner l'ID généré
                this.setId(rs.getInt(1));
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


    public static ArrayList <Formule> getAll_byId_produit (Connection conn, int id_produit) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Formule> formules = new ArrayList <> ();
        String req = "SELECT * FROM formule WHERE id_produit = ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, id_produit);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_formule");
                Date daty = rs.getDate("daty");
                String description = rs.getString ("description");
                Integer quantite_produite = rs.getInt ("quantite_produite");

                formules.add(new Formule(id, daty, description, quantite_produite, id_produit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return formules;
    }

    public static ArrayList <Formule> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Formule> formules = new ArrayList <> ();
        String req = "SELECT * FROM formule";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_formule");
                Date daty = rs.getDate("daty");
                String description = rs.getString ("description");
                Integer quantite_produite = rs.getInt ("quantite_produite");
                Integer id_produit = rs.getInt ("id_produit");

                formules.add(new Formule(id, daty, description, quantite_produite, id_produit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return formules;
    }


    public void setDaty(Date daty) { this.daty = daty; }
    public void setDescription(String description) { this.description = description; }
    public void setId(Integer id) { this.id = id; }
    public void setId_produit(Integer id_produit) { this.id_produit = id_produit; }
    public void setQuantite_produite(Integer quantite_produite) { this.quantite_produite = quantite_produite; }

    public Date getDaty() { return daty; }
    public String getDescription() { return description; }
    public Integer getId() { return id; }
    public Integer getId_produit() { return id_produit; }
    public Integer getQuantite_produite() { return quantite_produite; }

}
