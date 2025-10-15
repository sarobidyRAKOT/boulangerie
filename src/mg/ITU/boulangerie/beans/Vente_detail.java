package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vente_detail {
    
    Integer id, quantite_produit, id_vente, id_produit;
    Double prix_unitaire;
    String vente, nom_produit, unite, categorie;


    public Vente_detail () {}

    public Vente_detail (Integer id, Integer vente, String nom_produit, Integer quantite_produit,
    String unite, Double prix_unitaire, String categorie) {
        this.setId(id);
        this.vente = "VENTE "+vente;
        this.nom_produit = nom_produit;
        this.setQuantite_produit(quantite_produit);
        this.unite = unite;
        this.setPrix_unitaire(prix_unitaire);
        this.categorie = categorie;
    }
    

    public static ArrayList <Vente_detail> getAll_detailListe (Connection conn, int offset, int nbr) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vente_detail> vente_details = new ArrayList <> ();
        String req = "SELECT\r\n"+
        "    vd.id_ventedetail id_ventedetail,\r\n"+
        "    v.id_vente id_vente,\r\n"+
        "    p.nom nom_produit,\r\n"+
        "    vd.quantite_produit quantite_produit,\r\n"+
        "    u.nom unite,\r\n"+
        "    vd.prix_unitaire prix_unitaire,\r\n"+
        "    categ.nom categorie\r\n"+
        "FROM vente_detail vd \r\n"+
        "LEFT JOIN vente v on vd.id_vente = v.id_vente \r\n"+
        "LEFT JOIN produit p on vd.id_produit = p.id_produit \r\n"+
        "LEFT JOIN categorie categ on p.id_categorie = categ.id_categorie \r\n"+
        "LEFT JOIN unite  u on p.id_unite = u.id_unite \r\n"+
        "ORDER BY vd.id_ventedetail\r\n"+
        "LIMIT ? offset ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, nbr);
            pstmt.setInt(2, offset);

            // rs = pstmt.executeQuery(req);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt("id_ventedetail");
                int vente = rs.getInt("id_vente");
                String nom_produit = rs.getString("nom_produit");
                int quantite_produit = rs.getInt("quantite_produit");
                String unite = rs.getString("unite");
                double prix_unitaire = rs.getDouble("prix_unitaire");
                String categorie = rs.getString("categorie");

                vente_details.add(new Vente_detail(id, vente, nom_produit, quantite_produit, unite, prix_unitaire, categorie));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vente_details;
    }

    public static ArrayList <Vente_detail> getbyIDCategorie_detailListe (Connection conn, int offset, int nbr, int id_categorie) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vente_detail> vente_details = new ArrayList <> ();
        String req = "SELECT\r\n"+
        "    vd.id_ventedetail id_ventedetail,\r\n"+
        "    v.id_vente id_vente,\r\n"+
        "    p.nom nom_produit,\r\n"+
        "    vd.quantite_produit quantite_produit,\r\n"+
        "    u.nom unite,\r\n"+
        "    vd.prix_unitaire prix_unitaire,\r\n"+
        "    categ.nom categorie\r\n"+
        "FROM vente_detail vd \r\n"+
        "LEFT JOIN vente v on vd.id_vente = v.id_vente \r\n"+
        "LEFT JOIN produit p on vd.id_produit = p.id_produit \r\n"+
        "LEFT JOIN categorie categ on p.id_categorie = categ.id_categorie \r\n"+
        "LEFT JOIN unite  u on p.id_unite = u.id_unite \r\n"+
        "WHERE p.id_categorie = ? \r\n"+
        "ORDER BY vd.id_ventedetail\r\n"+
        "LIMIT ? offset ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, id_categorie);
            pstmt.setInt(2, nbr);
            pstmt.setInt(3, offset);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_ventedetail");
                int vente = rs.getInt("id_vente");
                String nom_produit = rs.getString("nom_produit");
                int quantite_produit = rs.getInt("quantite_produit");
                String unite = rs.getString("unite");
                double prix_unitaire = rs.getDouble("prix_unitaire");
                String categorie = rs.getString("categorie");

                vente_details.add(new Vente_detail(id, vente, nom_produit, quantite_produit, unite, prix_unitaire, categorie));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vente_details;
    }


    public int insert (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO vente_detail (quantite_produit, prix_unitaire, id_vente, id_produit) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, this.getQuantite_produit());
            pstmt.setDouble(2, this.getPrix_unitaire());
            pstmt.setInt(3, this.getId_vente());
            pstmt.setInt(4, this.getId_produit());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }


    public static int insert (Connection conn, String[][] data, Integer id_vente) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO vente_detail (quantite_produit, prix_unitaire, id_vente, id_produit) VALUES";


        for (int i = 0; i < data.length; i++) {
            int id = Integer.parseInt(data[i][0]);
            double qtt = Double.parseDouble (data[i][1]);
            Double PU = Double.parseDouble(data[i][2]);

            req += "("+qtt+","+PU+","+id_vente+","+id+"),";
        }
        req+= ";"; 
        req = req.replace("),;", ");");

        try {
            pstmt = conn.prepareStatement(req);
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
        }
        return 0;
    }

    public Integer getId() { return id; }
    public Integer getId_produit() { return id_produit; }
    public Integer getId_vente() { return id_vente; }
    public Double getPrix_unitaire() { return prix_unitaire; }
    public Integer getQuantite_produit() { return quantite_produit; }
    public String getCategorie() { return categorie; }
    public String getNom_produit() { return nom_produit; }
    public String getUnite() { return unite; }
    public String getVente() { return vente; }

    public void setId(Integer id) { this.id = id; }
    public void setId_produit(Integer id_produit) { this.id_produit = id_produit; }
    public void setId_vente(Integer id_vente) { this.id_vente = id_vente; }
    public void setPrix_unitaire(Double prix_unitaire) { this.prix_unitaire = prix_unitaire; }
    public void setQuantite_produit(Integer quantite_produit) { this.quantite_produit = quantite_produit; }
    
}
