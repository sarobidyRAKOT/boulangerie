package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Prix_vente {
    
    Integer id_prxivente, id_produit;
    Date daty;
    String produit, motif;
    Double prix;

    public Prix_vente (String id_produit, String motif, String daty, String prix) throws Erreur {
        this.setId_produit(id_produit);
        this.setDaty(daty);
        this.setPrix(prix);
        this.setMotif(motif);
    }

    private Prix_vente (int id, int id_produit, String nom, Date daty, String motif, double prix) {
        this.setId_prxivente(id);
        this.setId_produit(id_produit);
        this.setProduit(nom);
        this.setDaty(daty);
        this.setMotif(motif);
        this.setPrix(prix);
    }

    public int insert (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO prix_vente (id_produit, daty, motif, prix) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req);

            pstmt.setInt(1, this.getId_produit());
            pstmt.setDate(2, this.getDaty());
            pstmt.setString(3, this.getMotif());
            pstmt.setDouble(4, this.getPrix());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }

    public static int insert_currentDaty (Connection conn, int id_produit, String motif, double prix) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO prix_vente (id_produit, motif, prix, daty) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req);

            pstmt.setInt(1, id_produit);
            pstmt.setString(2, motif);
            pstmt.setDouble(3, prix);
            pstmt.setDate(4, Date.valueOf(LocalDate.now()));

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }

    public static ArrayList <Prix_vente> getHistorique_byID (Connection conn, String where) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Prix_vente> prix_ventes = new ArrayList <> ();
        String req = "SELECT\r\n" +
        "    pv.id_prixvente AS id,\r\n" +
        "    pv.id_produit AS id_produit,\r\n" +
        "    pv.prix AS prix,\r\n" +
        "    pv.daty AS daty,\r\n" +
        "    pv.motif AS motif,\r\n" +
        "    p.nom nom\r\n" +
        "FROM prix_vente pv \r\n" +
        "LEFT JOIN produit p ON pv.id_produit = p.id_produit\r\n";

        if (where != null) {
            req += where;
        }
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                int id_produit = rs.getInt("id_produit");
                String motif = rs.getString("motif");
                Date daty = rs.getDate ("daty");
                String nom = rs.getString ("nom");
                double prix = rs.getDouble ("prix");

                prix_ventes.add(new Prix_vente(id, id_produit, nom, daty, motif, prix));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return prix_ventes;
    }
    



    
    public Date getDaty() { return daty; }
    public Integer getId_produit() { return id_produit; }
    public String getNom_produit() { return produit; }
    public Double getPrix() { return prix; }
    public String getMotif() { return motif; }
    public Integer getId_prxivente() { return id_prxivente; }
    public void setPrix(String prix) throws Erreur { 
        if (prix == null) throw new Erreur("prix NE DOIS PAS ETRE NULL");
        if (prix.isEmpty()) throw new Erreur("prix NE DOIS PAS ETRE VIDE");
        
        try {
            this.prix = Double.parseDouble(prix);
        } catch (NumberFormatException e) {
            throw new Erreur(e.getMessage());
        }
    }
    public void setDaty(String daty) throws Erreur { 

        if (daty == null) throw new Erreur("DATY NE DOIS PAS ETRE NULL");
        if (daty.isEmpty()) throw new Erreur("DATY NE DOIS PAS ETRE VIDE");
        
        try {
            this.daty = Date.valueOf(daty);
        } catch (IllegalArgumentException e) {
            throw new Erreur(e.getMessage());
        }
    }

    public void setId_produit(String id_produit) throws Erreur { 
        if (id_produit == null) throw new Erreur("ID_PRODUIT NE DOIS PAS ETRE NULL");
        if (id_produit.isEmpty()) throw new Erreur("ID_PRODUIT NE DOIS PAS ETRE VIDE");
        
        try {
            this.id_produit = Integer.parseInt(id_produit);
        } catch (NumberFormatException e) {
            throw new Erreur(e.getMessage());
        }
    }
    
    public void setDaty(Date daty) {  this.daty = daty; }
    public void setId_produit(Integer id_produit) { this.id_produit = id_produit; }
    public void setProduit(String nom_produit) { this.produit = nom_produit; }
    public void setPrix(Double prix) { this.prix = prix; }
    public void setId_prxivente(Integer id_prxivente) { this.id_prxivente = id_prxivente; }
    public void setMotif(String motif) { this.motif = motif; }
}
