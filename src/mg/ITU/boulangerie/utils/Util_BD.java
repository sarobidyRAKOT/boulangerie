package mg.ITU.boulangerie.utils;

import java.sql.*;
import java.util.Properties;

public class Util_BD {
    
    private String user;
    private String password;
    private String url;


    Connection conn;
    
    
    public Util_BD () {}

    private Util_BD (String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }



    public static Util_BD get_Instance () throws ClassNotFoundException {

        Properties properties = LoaderProperties.load_properties("config/boulangerie.properties");

        String url = properties.getProperty("boulangerie.conn.url.database");
        String user = properties.getProperty("boulangerie.conn.nom.user");
        String mdp = properties.getProperty("boulangerie.conn.mot.de.passe");

        
        
        try {
            Class.forName("org.postgresql.Driver");
            Util_BD util_BD = new Util_BD(url, user, mdp);
            return util_BD;
        } catch (ClassNotFoundException e) {
            throw e;
        }        
        
    }
    
    
    public void close_conn () {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Connection connect () throws SQLException {
                                
        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        return this.conn;
    }


    public void commit_ON () {
        try {
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    public void commit_OFF () {
        try {
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void valider () {
        try {
            this.conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void annuler () {
        try {
            this.conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

}
