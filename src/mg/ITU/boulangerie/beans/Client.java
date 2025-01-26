package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Client {
    
    Integer id;
    String nom, tel;

    public Client (Integer id, String nom, String tel) {
        setId(id);
        setNom(nom);
        setTel(tel);
    }
    
    public static ArrayList <Client> getAll (Connection conn) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Client> clients = new ArrayList <> ();
        String req = "SELECT * FROM client";
        try {
            pstmt = conn.prepareStatement(req);
            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_client");
                String nom = rs.getString("nom");
                String tel = rs.getString("tel");

                clients.add(new Client(id, nom, tel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return clients;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getTel() {
        return tel;
    }

}
