package mg.ITU.boulangerie.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Formule_detaile {

    Integer id_ingredient, id_formule;
    String nom_ingredient, nom_unite;
    Double quantite_ingredient;



    public Formule_detaile (Integer id, Integer id_ingredient, Double qtt_ingredient) {
        this.setId_formule(id);
        this.setQuantite_ingredient(qtt_ingredient);
        this.setId_ingredient(id_ingredient);
    }
    public Formule_detaile (Integer id_formule) {
        this.setId_formule(id_formule);
    }
    
    public int insert (Connection conn, String[] id_ingredients, String[] quantites) throws SQLException {

        PreparedStatement pstmt = null;
        String req = "INSERT INTO formule_detail (id_ingredient, id_formule, quantite_ingredient) VALUES";

        for (int i = 0; i < id_ingredients.length; i++) {
            int id = Integer.parseInt(id_ingredients[i]);
            Double qtt = Double.parseDouble(quantites[i]);
            req += "("+id+","+this.getId_formule()+","+qtt+"),";
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

    public static ArrayList <Formule_detaile> getAll_byId_formule (Connection conn, int id_formule) throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList <Formule_detaile> formule_detailes = new ArrayList <> ();
        String req = "select\r\n" + //
        "    i.id_ingredient,\r\n" + //
        "    i.nom nom_ingredient,\r\n" + //
        "    fd.quantite_ingredient,\r\n" + //
        "    u.nom nom_unite,\r\n" + //
        "    u.reference\r\n" + //
        "from formule_detail fd \r\n" + //
        "left join ingredient i on fd.id_ingredient = i.id_ingredient \r\n" + //
        "left join unite u on i.id_unite = u.id_unite\r\n" + //
        "where fd.id_formule = ?";
        try {
            pstmt = conn.prepareStatement(req);
            pstmt.setInt(1, id_formule);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Integer id_ingredient = rs.getInt("id_ingredient");
                String nom_ingredient = rs.getString("nom_ingredient");
                Double quantite_ingredient = rs.getDouble ("quantite_ingredient");
                String nom_unite = rs.getString ("nom_unite")+"  "+rs.getString("reference");

                Formule_detaile fd = new Formule_detaile(id_formule, id_ingredient, quantite_ingredient);
                fd.setNom_ingredient(nom_ingredient);
                fd.setNom_unite(nom_unite);
                formule_detailes.add(fd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (pstmt != null) pstmt.close();
            if (rs != null) rs.close();
        }
        return formule_detailes;
    }

    public void setNom_ingredient(String nom_ingredient) {this.nom_ingredient = nom_ingredient; }
    public void setNom_unite(String nom_unite) {this.nom_unite = nom_unite; }
    public void setId_ingredient(Integer id_ingredient) {this.id_ingredient = id_ingredient; }
    public void setQuantite_ingredient(Double quantite_ingredient) {this.quantite_ingredient = quantite_ingredient; }
    public void setId_formule(Integer id_formule) { this.id_formule = id_formule;}

    public Integer getId_formule() { return id_formule;}
    public Integer getId_ingredient() { return id_ingredient; }
    public Double getQuantite_ingredient() { return quantite_ingredient;}
    public String getNom_ingredient() { return nom_ingredient; }
    public String getNom_unite() { return nom_unite; }
}
