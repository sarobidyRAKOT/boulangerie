package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produit_mois {
    
    Integer id, id_produit;
    String nom_produit, mois;
    Date daty;

    public Produit_mois (String mois, Integer id_produit) {
        this.id_produit = id_produit;
        this.mois = mois;
    }

    
    
    public Produit_mois() {
        //TODO Auto-generated constructor stub
    }

    public int insert (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO produit_mois (mois, id_produit) VALUES (?, ?)";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setString(1, this.getMois());
            pstmt.setInt(2, this.getId_produit());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }

    public static ArrayList <Produit> getHistorique_2024 (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "SELECT \r\n"+
        "    p.id_produit AS id_produit,\r\n"+
        "    p.nom AS nom,\r\n"+
        "    p.id_categorie AS id_categorie,\r\n"+
        "    p.id_unite AS id_unite\r\n"+
        "FROM produit_mois pm\r\n"+
        "LEFT JOIN produit p ON pm.id_produit = p.id_produit\r\n"+
        "WHERE EXTRACT(YEAR FROM pm.daty) = 2024";

        try {
            pstmt = conn.prepareStatement(req);
            // pstmt.setString(1, index_mois);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                Integer id_categorie = rs.getInt ("id_categorie");
                Integer id_unite = rs.getInt ("id_unite");


                Produit produit= new Produit(id, nom, null, id_categorie, id_unite);

                
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return produits;
    }


    public static ArrayList <Produit> getAll_byMois (Connection conn, String index_mois) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "select \r\n" + //
        "    p.id_produit id_produit,\r\n" + //
        "    p.nom nom,\r\n" + //
        "    p.id_categorie id_categorie,\r\n" + //
        "    p.id_unite id_unite\r\n" + //
        "from produit_mois pm\r\n" + //
        "left join produit p on pm.id_produit = p.id_produit\r\n" + //
        "where pm.mois like ?";

        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setString(1, index_mois);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                Integer id_categorie = rs.getInt ("id_categorie");
                Integer id_unite = rs.getInt ("id_unite");


                Produit produit= new Produit(id, nom, null, id_categorie, id_unite);

                
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return produits;
    }



    // public static ArrayList <Produit_mois> getAll (Connection conn) throws SQLException {

    //     PreparedStatement pstmt = null;
    //     ResultSet rs = null;
    //     ArrayList <Produit_mois> produits = new ArrayList <> ();
    //     String req = "SELECT * FROM produit";
    //     try {
    //         pstmt = conn.prepareStatement(req);

    //         rs = pstmt.executeQuery();

    //         while (rs.next()) {
    //             Integer id = rs.getInt("id_produit");
    //             String nom = rs.getString("nom");
    //             Double prix = rs.getDouble ("prix");
    //             Integer id_categorie = rs.getInt ("id_categorie");
    //             Integer id_unite = rs.getInt ("id_unite");

    //             produits.add(new Produit_mois(id, nom, prix, id_categorie, id_unite));
    //             // unites.add(new Produit(id, nom, ref));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     finally {
    //         if (pstmt != null) pstmt.close();
    //         if (rs != null) rs.close();
    //     }
    //     return produits;
    // }

    public Integer getId() {
        return id;
    }
    public Integer getId_produit() {
        return id_produit;
    }
    public String getMois() {
        return mois;
    }
    public String getNom_produit() {
        return nom_produit;
    }

}
