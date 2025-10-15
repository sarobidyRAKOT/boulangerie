package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Produit {
    
    Integer id, id_historique;
    String nom, nom_categorie, nom_unite, id_unite, id_categorie;
    Double prix, quantite;
    Date date;

    public Produit (Integer id, String nom, Double prix, String id_categorie, String id_unite) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.prix = prix;
        this.id_unite = id_unite;
        
    }
    public Produit (String nom, String prix, String id_categorie, String id_unite) throws Erreur {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.setPrix(prix);
        this.id_unite = id_unite;
    }

    public Integer getId_historique() { return id_historique; }
    public void setId_historique(Integer id_historique) { this.id_historique = id_historique; }
    
    
    public Produit() {}

    
    public int insert_setID (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String req = "INSERT INTO produit (nom, id_categorie, id_unite) VALUES (?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getId_categorie());
            pstmt.setString(3, this.getId_unite());


            
            int nbr = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) this.setId(rs.getInt(1));

            return nbr;


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
                String id_categorie = rs.getString ("id_categorie");
                String nom_categorie = rs.getString ("nom_categorie");
                String id_unite = rs.getString ("id_unite");

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
        String req = "select \r\n"+
        "    categ.id_produit,\r\n"+
        "    categ.nom nom_produit,\r\n"+
        "    categ.prix,\r\n"+
        "    categ.id_categorie, \r\n"+
        "    ct.nom nom_categorie,\r\n"+
        "    categ.id_unite\r\n"+
        "from (\r\n"+
        "    select \r\n"+
        "        p.id_produit,\r\n"+
        "        p.nom,\r\n"+
        "        p.prix,\r\n"+
        "        p.id_unite,\r\n"+
        "        p.id_categorie,\r\n"+
        "        f.id_formule\r\n"+
        "    from produit p \r\n"+
        "    join formule f on f.id_produit = p.id_produit\r\n"+
        "    where p.id_categorie = "+id_categ+"\r\n"+
        ") AS categ\r\n"+
        "JOIN (\r\n"+
        "    select * from formule_detail fd \r\n"+
        "    left join ingredient i on fd.id_ingredient = i.id_ingredient\r\n"+
        "    where i.id_ingredient = "+id_ingredient+"\r\n"+
        ") ing\r\n"+
        "on categ.id_formule = ing.id_formule\r\n"+
        "left join categorie ct on categ.id_categorie = ct.id_categorie";

        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom_produit");
                String id_categorie = rs.getString ("id_categorie");
                String nom_categorie = rs.getString ("nom_categorie");
                String id_unite = rs.getString ("id_unite");

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


    static Produit get_ProduitByID (ArrayList <Produit>produits, int id_produit) {
        for (Produit produit : produits) {
            if (produit.getId().equals(id_produit)) {
                return produit;
            }
        }
        return null;
    }


    public static ArrayList <Produit> getAll (Connection conn, Date daty) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Produit> produits = new ArrayList <> ();
        String req = "SELECT DISTINCT ON (p.id_produit) \r\n" + //
        "    p.id_produit id_produit,\r\n" + //
        "    p.nom nom,\r\n" + //
        "    p.id_categorie id_categorie,\r\n" + //
        "    p.id_unite id_unite,\r\n" + //
        "    pv.prix prix\r\n" + //
        "FROM produit p\r\n" + //
        "LEFT JOIN prix_vente pv \r\n" + //
        "    ON p.id_produit = pv.id_produit\r\n" + //
        "    AND pv.daty <= ?  -- On récupère uniquement les prix avant ou à cette date\r\n" + //
        "ORDER BY p.id_produit, pv.daty DESC";
        try {
            pstmt = conn.prepareStatement(req);

            if (daty != null) pstmt.setDate(1, daty);
            else  pstmt.setDate(1, Date.valueOf(LocalDate.now()));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_produit");
                String nom = rs.getString("nom");
                Double prix = rs.getDouble ("prix");
                String id_categorie = rs.getString ("id_categorie");
                String id_unite = rs.getString ("id_unite");

                produits.add(new Produit(id, nom, prix, id_categorie, id_unite));
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
    public String getId_categorie() { return id_categorie; }
    public String getId_unite() { return id_unite; }
    public Double getPrix() { return prix; }
    public String getNom_categorie() { return nom_categorie; }
    public String getNom_unite() { return nom_unite; }
    public Double getQuantite() { return quantite; }
    public Date getDate() { return date; }
    public void setDate(Date date) {this.date = date;}
    public void setId(Integer id) { this.id = id;}


    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix(String prix) throws Erreur {
        if (prix == null) throw new Erreur("prix NE DOIS PAS ETRE NULL");
        if (prix.isEmpty()) throw new Erreur("prix NE DOIS PAS ETRE VIDE");
        
        try {
            this.prix = Double.parseDouble(prix);
        } catch (NumberFormatException e) {
            throw new Erreur(e.getMessage());
        }
    }

}
