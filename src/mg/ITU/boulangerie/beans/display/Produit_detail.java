package mg.ITU.boulangerie.beans.display;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Produit_detail {

    int id_produit;
    String nom, id_categorie, id_unite;
    double prix, reste;

    public String getId_categorie() { return id_categorie; }
    public int getId_produit() { return id_produit; }
    public String getId_unite() { return id_unite; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public double getReste() { return reste; }

    private Produit_detail (int id_produit, String nom, String id_categorie,
        String id_unite, double prix, double reste) {
   
        this.id_produit = id_produit;
        this.nom = nom;
        this.id_categorie = id_categorie;
        this.id_unite = id_unite;
        this.prix = prix;
        this.reste = reste;
    }

    public static ArrayList <Produit_detail> getAll (Connection conn, Date daty) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit_detail> produits = new ArrayList <> ();
        String req = "SELECT DISTINCT ON (p.id_produit)\r\n" + //
        "    p.id_produit AS id_produit,\r\n" + //
        "    p.nom AS nom_produit,\r\n" + //
        "    ct.id_categorie AS id_categorie,\r\n" + //
        "    u.id_unite AS id_unite,\r\n" + //
        "    pv.prix AS prix,\r\n" + //
        "    rp.reste AS reste\r\n" + //
        "FROM produit p\r\n" + //
        "LEFT JOIN prix_vente pv ON p.id_produit = pv.id_produit AND pv.daty <= ?\r\n" + //
        "LEFT JOIN reste_produit rp on p.id_produit = rp.id_produit\r\n" + //
        "LEFT JOIN categorie ct on p.id_categorie = ct.id_categorie\r\n" + //
        "LEFT JOIN unite u on p.id_unite = u.id_unite\r\n" + //
        "ORDER BY p.id_produit, pv.daty DESC";

        try {

            
            pstmt = conn.prepareStatement(req);
            if (daty != null) pstmt.setDate(1, daty);
            else pstmt.setDate(1, Date.valueOf(LocalDate.now()));
                
            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                int id_produit = rs.getInt("id_produit");
                String nom = rs.getString("nom_produit");
                String id_categorie = rs.getString ("id_categorie");
                String id_unite = rs.getString ("id_unite");

                double prix = rs.getDouble ("prix");
                double reste = rs.getDouble ("reste");

                produits.add(new Produit_detail(id_produit, nom, id_categorie, id_unite, prix, reste));
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
}
