package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vente {
    
    Integer id, id_client, id_vendeur;
    Double prix_total;
    Date daty;
    double comm = 200_000;

    public Vente (Integer id, Double prix_total, String daty, Integer id_client) throws Erreur {
        this.setId(id);
        this.setPrix_total(prix_total);
        setDaty(daty);
        this.setId_client(id_client);
    }

    public Vente (String daty, Integer id_client, Integer id_vendeur) throws Erreur {
        setDaty(daty);
        this.setId_client(id_client);
        this.setId_vendeur(id_vendeur);
    }

            
    public int insert_setID (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String req = "INSERT INTO vente (prix_ttl, daty, commission, id_client, id_vendeur) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req, PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setDouble(1, this.getPrix_total());
            pstmt.setDate(2, this.getDaty());
            if (this.getPrix_total() > this.comm) {
                pstmt.setDouble(3, 5);
            } else {
                pstmt.setDouble(3, 0);
            }
            pstmt.setInt(4, this.getId_client());
            pstmt.setInt(5, this.getId_vendeur());

            int nbr = pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
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



    public String[][] conf_venteDetail (Connection conn, String[] id_produits, String[] qtts) 
        throws SQLException, NullPointerException, NumberFormatException, Erreur {
        
        String[][] data = new String[id_produits.length][3];
        ArrayList <Produit> produits = Produit.getAll(conn, this.getDaty());
        
        double prix_ttl = 0;

        for (int i = 0; i < id_produits.length; i ++) {
            Produit p = Produit.get_ProduitByID(produits, Integer.parseInt(id_produits[i]));
            double qtt = Double.parseDouble(qtts[i]);
            
            if (p != null && qtt > 0 && p.getPrix() > 0) {
                prix_ttl += qtt * p.getPrix(); 
                data[i][0] = ""+p.getId();
                data[i][1] = ""+qtt;
                data[i][2] = ""+p.getPrix();
            } else {
                throw new Erreur("IL Y A UN PROBLEME POUR LA VENTE DU PRODUIT "+i);
            }
        }

        this.setPrix_total(prix_ttl);
        return data;
    } 



    public void setDaty(String daty) throws Erreur {
        if (daty == null) throw new Erreur("DATE NE DOIS PAS ETRE NULL"); 
        if (daty.isEmpty()) throw new Erreur("DATE NE DOIS PAS ETRE VIDE"); 
        try {
            this.daty = Date.valueOf(daty);
        } catch (IllegalArgumentException e) {
            throw new Erreur(e.getMessage());
        }
    }
    public void setDaty(Date daty) {
        this.daty = daty;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setPrix_total(Double prix_total) {
        this.prix_total = prix_total;
    }
    public Date getDaty() { return daty; }
    public Double getPrix_total() { return prix_total; }
    public Integer getId() { return id; }

    public Integer getId_client() {
        return id_client;
    }
    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }
    public Integer getId_vendeur() {
        return id_vendeur;
    }
    public void setId_vendeur(Integer id_vendeur) {
        this.id_vendeur = id_vendeur;
    }
}
