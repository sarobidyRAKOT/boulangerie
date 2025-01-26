package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vendeur {
    
    Integer id, nbr_vente;
    String nom, genre;
    Double prix_venteTTL, commissionTTL;


    public Vendeur (Integer id, String nom, Double prix_venteTTL, Double commissionTTL) {
        this.id = id;
        this.nom = nom;
        this.commissionTTL = commissionTTL;
        this.prix_venteTTL = prix_venteTTL;
    }

    public Vendeur (Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public static ArrayList <Vendeur> getAll_byPeriode (Connection conn, String date_debut, String date_fin) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vendeur> vendeurs = new ArrayList <> ();
        String req = "select\r\n" + //
        "    id_vendeur,\r\n" + //
        "    nom,\r\n" + //
        "    sum (prix_ttl) prix_ttl,\r\n" + //
        "    sum (commission) commission\r\n" + //
        "from commission\r\n" + //
        "where daty >= ? and daty <= ? \r\n" + //
        "group by id_vendeur";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setDate(1, Date.valueOf(date_debut));
            pstmt.setDate(2, Date.valueOf(date_fin));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_vente = rs.getInt("id_vendeur");
                String nom = rs.getString("nom");
                Double prix_ttl = rs.getDouble("prix_ttl");
                Double commission = rs.getDouble("commission");

                vendeurs.add(new Vendeur(id_vente, nom, prix_ttl, commission));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vendeurs;
    }

    public static ArrayList <Vendeur> getEtat_parGenre (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vendeur> vendeurs = new ArrayList <> ();
        String req = "SELECT \r\n" + //
        "    g.genre AS genre,\r\n" + //
        "    SUM(c.commission) AS total_commission,\r\n" + //
        "    COUNT(c.id_vente) AS nbr_ventes,\r\n" + //
        "    SUM(c.prix_ttl) AS prix_ttl\r\n" + //
        "FROM \r\n" + //
        "    commission c\r\n" + //
        "LEFT JOIN vendeur v ON c.id_vendeur = v.id_vendeur\r\n" + //
        "LEFT JOIN genre g ON v.id_genre = g.id_genre\r\n" + //
        "GROUP BY g.genre";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                String genre = rs.getString("genre");
                Double total_commission = rs.getDouble("total_commission");
                Double prix_ttl = rs.getDouble("prix_ttl");
                int nbr_ventes = rs.getInt("nbr_ventes");
                Vendeur vendeur = new Vendeur(null, null);
                vendeur.setGenre(genre);
                vendeur.setCommissionTTL(total_commission);
                vendeur.setPrix_venteTTL(prix_ttl);
                vendeur.setNbr_vente(nbr_ventes);

                vendeurs.add(vendeur);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vendeurs;
    }

    public static ArrayList <Vendeur> getEtat_parGenredans_Periode (Connection conn, String date_debut, String date_fin) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vendeur> vendeurs = new ArrayList <> ();
        String req = "SELECT \r\n" + //
        "    g.genre AS genre,\r\n" + //
        "    SUM(c.commission) AS total_commission,\r\n" + //
        "    COUNT(c.id_vente) AS nbr_ventes,\r\n" + //
        "    SUM(c.prix_ttl) AS prix_ttl\r\n" + //
        "FROM \r\n" + //
        "    commission c\r\n" + //
        "LEFT JOIN vendeur v ON c.id_vendeur = v.id_vendeur\r\n" + //
        "LEFT JOIN genre g ON v.id_genre = g.id_genre\r\n" + //
        "WHERE c.daty BETWEEN ? AND ?\r\n" + //
        "GROUP BY g.genre";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setDate(1, Date.valueOf(date_debut));
            pstmt.setDate(2, Date.valueOf(date_fin));

            rs = pstmt.executeQuery();

            while (rs.next()) {

                String genre = rs.getString("genre");
                Double total_commission = rs.getDouble("total_commission");
                Double prix_ttl = rs.getDouble("prix_ttl");
                int nbr_ventes = rs.getInt("nbr_ventes");
                Vendeur vendeur = new Vendeur(null, null);
                vendeur.setGenre(genre);
                vendeur.setCommissionTTL(total_commission);
                vendeur.setPrix_venteTTL(prix_ttl);
                vendeur.setNbr_vente(nbr_ventes);

                vendeurs.add(vendeur);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vendeurs;
    }
    


    public ArrayList <Vendeur> getAll_byPeriode_vendeur (Connection conn, String date_debut, String date_fin) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vendeur> vendeurs = new ArrayList <> ();
        String req = "select\r\n" + //
        "    id_vendeur,\r\n" + //
        "    nom,\r\n" + //
        "    prix_ttl,\r\n" + //
        "    commission\r\n" + //
        "from commission\r\n" + //
        "where daty >= ? and daty <= ? and id_vendeur = ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setDate(1, Date.valueOf(date_debut));
            pstmt.setDate(2, Date.valueOf(date_fin));
            pstmt.setInt(3, this.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_vente = rs.getInt("id_vendeur");
                String nom = rs.getString("nom");
                Double prix_ttl = rs.getDouble("prix_ttl");
                Double commission = rs.getDouble("commission");

                vendeurs.add(new Vendeur(id_vente, nom, prix_ttl, commission));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vendeurs;
    }




    public static ArrayList <Vendeur> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vendeur> vendeurs = new ArrayList <> ();
        String req = "select * from vendeur";
        try {
            pstmt = conn.prepareStatement(req);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_vente = rs.getInt("id_vendeur");
                String nom = rs.getString("nom");

                vendeurs.add(new Vendeur(id_vente, nom));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vendeurs;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public Double getCommissionTTL() {
        return commissionTTL;
    }
    public String getNom() {
        return nom;
    }
    public Double getPrix_venteTTL() {
        return prix_venteTTL;
    }

    public String getGenre() {
        return genre;
    }
    public Integer getNbr_vente() {
        return nbr_vente;
    }
    public void setCommissionTTL(Double commissionTTL) {
        this.commissionTTL = commissionTTL;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setNbr_vente(Integer nbr_vente) {
        this.nbr_vente = nbr_vente;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrix_venteTTL(Double prix_venteTTL) {
        this.prix_venteTTL = prix_venteTTL;
    }

}
