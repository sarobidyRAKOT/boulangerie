package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Produit {
    
    Integer id, id_categorie, id_unite;
    String nom, nom_categorie, nom_unite;
    Double prix, quantite;

    public Produit (Integer id, String nom, Double prix, Integer id_categorie, Integer id_unite) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.prix = prix;
        this.id_unite = id_unite;
        
    }
    public Produit (String nom, Double prix, Integer id_categorie, Integer id_unite) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.prix = prix;
        this.id_unite = id_unite;
    }

    
    
    public Produit() {
        //TODO Auto-generated constructor stub
    }
    public int insert (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO produit (nom, prix, id_categorie, id_unite) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setString(1, this.getNom());
            pstmt.setDouble(2, this.getPrix());
            pstmt.setInt(3, this.getId_categorie());
            pstmt.setInt(4, this.getId_unite());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }


    public static ArrayList <Produit> getAll_byMois (Connection conn, Integer id_categ, Integer id_ingredient) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "select \r\n" + //
        "    categ.id_produit,\r\n" + //
        "    categ.nom nom_produit,\r\n" + //
        "    categ.prix,\r\n" + //
        "    categ.id_categorie, \r\n" + //
        "    ct.nom nom_categorie,\r\n" + //
        "    categ.id_unite\r\n" + //
        "from (\r\n" + //
        "    select \r\n" + //
        "        p.id_produit,\r\n" + //
        "        p.nom,\r\n" + //
        "        p.prix,\r\n" + //
        "        p.id_unite,\r\n" + //
        "        p.id_categorie,\r\n" + //
        "        f.id_formule\r\n" + //
        "    from produit p \r\n" + //
        "    join formule f on f.id_produit = p.id_produit\r\n" + //
        "    where p.id_categorie = "+id_categ+"\r\n" + //
        ") AS categ\r\n" + //
        "JOIN (\r\n" + //
        "    select * from formule_detail fd \r\n" + //
        "    left join ingredient i on fd.id_ingredient = i.id_ingredient\r\n" + //
        "    where i.id_ingredient = "+id_ingredient+"\r\n" + //
        ") ing\r\n" + //
        "on categ.id_formule = ing.id_formule\r\n" + //
        "left join categorie ct on categ.id_categorie = ct.id_categorie";

        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom_produit");
                Integer id_categorie = rs.getInt ("id_categorie");
                String nom_categorie = rs.getString ("nom_categorie");
                Integer id_unite = rs.getInt ("id_unite");

                Double prix = rs.getDouble ("prix");

                Produit produit= new Produit(id, nom, prix, id_categorie, id_unite);
                produit.nom_categorie = nom_categorie;

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


    public static ArrayList <Produit> getAll_byCateg_Ingredient (Connection conn, Integer id_categ, Integer id_ingredient) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "select \r\n" + //
        "    categ.id_produit,\r\n" + //
        "    categ.nom nom_produit,\r\n" + //
        "    categ.prix,\r\n" + //
        "    categ.id_categorie, \r\n" + //
        "    ct.nom nom_categorie,\r\n" + //
        "    categ.id_unite\r\n" + //
        "from (\r\n" + //
        "    select \r\n" + //
        "        p.id_produit,\r\n" + //
        "        p.nom,\r\n" + //
        "        p.prix,\r\n" + //
        "        p.id_unite,\r\n" + //
        "        p.id_categorie,\r\n" + //
        "        f.id_formule\r\n" + //
        "    from produit p \r\n" + //
        "    join formule f on f.id_produit = p.id_produit\r\n" + //
        "    where p.id_categorie = "+id_categ+"\r\n" + //
        ") AS categ\r\n" + //
        "JOIN (\r\n" + //
        "    select * from formule_detail fd \r\n" + //
        "    left join ingredient i on fd.id_ingredient = i.id_ingredient\r\n" + //
        "    where i.id_ingredient = "+id_ingredient+"\r\n" + //
        ") ing\r\n" + //
        "on categ.id_formule = ing.id_formule\r\n" + //
        "left join categorie ct on categ.id_categorie = ct.id_categorie";

        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom_produit");
                Integer id_categorie = rs.getInt ("id_categorie");
                String nom_categorie = rs.getString ("nom_categorie");
                Integer id_unite = rs.getInt ("id_unite");

                Double prix = rs.getDouble ("prix");

                Produit produit= new Produit(id, nom, prix, id_categorie, id_unite);
                produit.nom_categorie = nom_categorie;

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

    public static ArrayList <Produit> getAll_withDetail (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "\r\n" +
        "select\r\n" +
        "    p.id_produit,\r\n" +
        "    p.nom nom_produit,\r\n" +
        "    ct.id_categorie,\r\n" +
        "    ct.nom nom_categorie,\r\n" +
        "    u.id_unite,\r\n" +
        "    u.nom nom_unite,\r\n" +
        "    p.prix,\r\n" +
        "    rp.reste\r\n" +
        "from produit p \r\n" +
        "left join reste_produit rp on p.id_produit = rp.id_produit\r\n" +
        "left join categorie ct on p.id_categorie = ct.id_categorie\r\n" +
        "left join unite u on p.id_unite = u.id_unite";

        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom_produit");
                Integer id_categorie = rs.getInt ("id_categorie");
                String nom_categorie = rs.getString ("nom_categorie");
                Integer id_unite = rs.getInt ("id_unite");
                String nom_unite = rs.getString ("nom_unite");

                Double prix = rs.getDouble ("prix");
                Double reste = rs.getDouble ("reste");

                Produit produit= new Produit(id, nom, prix, id_categorie, id_unite);
                produit.nom_categorie = nom_categorie;
                produit.nom_unite = nom_unite;
                produit.quantite = reste;

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


    public static ArrayList <Produit> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "SELECT * FROM produit";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                Double prix = rs.getDouble ("prix");
                Integer id_categorie = rs.getInt ("id_categorie");
                Integer id_unite = rs.getInt ("id_unite");

                produits.add(new Produit(id, nom, prix, id_categorie, id_unite));
                // unites.add(new Produit(id, nom, ref));
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

    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public Integer getId_categorie() { return id_categorie; }
    public Integer getId_unite() { return id_unite; }
    public Double getPrix() { return prix; }
    public String getNom_categorie() { return nom_categorie; }
    public String getNom_unite() { return nom_unite; }
    public Double getQuantite() { return quantite; }

}
