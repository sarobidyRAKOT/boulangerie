package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vente_client {
    
    Integer id_vente, id_client;
    String nom_tel;
    Double prix_ttl;
    Date daty;


    public Vente_client (Integer id_vente, Integer id_client, String nom_tel, Double prix_ttl, Date daty) {
        this.id_vente = id_vente;
        this.id_client = id_client;
        this.nom_tel = nom_tel;
        this.prix_ttl = prix_ttl;
        this.daty = daty;
    }
    
    public static ArrayList <Vente_client> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vente_client> vente_clients = new ArrayList <> ();
        String req = "SELECT \r\n" + //
        "    *\r\n" + //
        "FROM vente v \r\n" + //
        "LEFT JOIN client cl ON v.id_client = cl.id_client";
        try {
            pstmt = conn.prepareStatement(req);
            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_vente = rs.getInt("id_vente");
                Integer id_client = rs.getInt("id_client");
                String nom_tel = rs.getString("nom")+" "+rs.getString("tel");
                Double prix_ttl = rs.getDouble("prix_ttl");
                Date daty = rs.getDate("daty");

                vente_clients.add(new Vente_client(id_vente, id_client, nom_tel, prix_ttl, daty));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vente_clients;
    }

    
    public static ArrayList <Vente_client> getAll_bydate (Connection conn, String date) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Vente_client> vente_clients = new ArrayList <> ();
        String req = "SELECT \r\n" + //
        "    *\r\n" + //
        "FROM vente v \r\n" + //
        "LEFT JOIN client cl ON v.id_client = cl.id_client\r\n" + //
        "WHERE v.daty = ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setDate(1, Date.valueOf(date));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_vente = rs.getInt("id_vente");
                Integer id_client = rs.getInt("id_client");
                String nom_tel = rs.getString("nom")+" "+rs.getString("tel");
                Double prix_ttl = rs.getDouble("prix_ttl");
                Date daty = rs.getDate("daty");

                vente_clients.add(new Vente_client(id_vente, id_client, nom_tel, prix_ttl, daty));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return vente_clients;
    }


    public Date getDaty() {
        return daty;
    }
    public Integer getId_client() {
        return id_client;
    }
    public Integer getId_vente() {
        return id_vente;
    }
    public String getNom_tel() {
        return nom_tel;
    }
    public Double getPrix_ttl() {
        return prix_ttl;
    }
}
