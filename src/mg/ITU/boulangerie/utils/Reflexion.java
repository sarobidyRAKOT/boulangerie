package mg.ITU.boulangerie.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mg.ITU.boulangerie.annotations.Colonne;
import mg.ITU.boulangerie.annotations.Table;

public class Reflexion {
    

    // public String get_nomClasse (Object object) {
    //     /** maka anaran le objet en parametre... */
    //     return object.getClass().getSimpleName();
    // }
    
    
    // public Class <?>[] get_paramsTYPE (Object[] params) {
    //     /** maka type[] object  */
    //     Class <?>[] type_params = null;
    //     if (params != null && params.length > 0) {
    //         type_params = new Class <?>[params.length];
    //         int i = 0;
    //         for (Object clazz : params) {
    //             type_params[i] = clazz.getClass();
    //             ++ i; //  incrementer i
    //         }
    //     }
        
    //     return type_params;
    // }

    
    // public Field get_fieldClasse_byName (ArrayList <Field> fields, String attr_name) {
        
    //     Field attr = null;
    //     for (Field field : fields) {
    //         if (field.getName().equals(attr_name)) {
    //             attr = field;
    //             break;
    //         }
    //     }
    //     return attr;
    // }


    // public void execute_METHOD (Object object, String methode_name, Object[] params) 
    //     throws Exception {
    //     /** EXECUTE methode SANS RETURN */
    //     Class <?> clazz = object.getClass();
    //     Class <?>[] type_params = this.get_paramsTYPE(params);

    //     try {
    //         Method method = clazz.getDeclaredMethod(methode_name, type_params);
    //         method.invoke(object, params); // appeller la methode
    //     } catch (NoSuchMethodException | SecurityException e) {
    //         /// throws Exception
    //         throw e;
    //     }
    // }


    // public Object execute_METHODE (Object object, String methode_name, Object[] params) throws Exception {
    //     Class <?> clazz = object.getClass();
    //     Class <?>[] type_params = this.get_paramsTYPE(params);
    //     try {
    //         Method method = clazz.getDeclaredMethod(methode_name, type_params);
    //         return method.invoke(object, params); // appeller la methode
    //     } catch (NoSuchMethodException | SecurityException e) {
    //         /// throws Exception
    //         throw e; 
    //     }
    // }

    // public Object execute_METHODE (Object object, String methode_name, Class<?>[] type_params, Object[] params) throws Exception {
    //     Class <?> clazz = object.getClass();
    //     try {
    //         Method method = clazz.getDeclaredMethod(methode_name, type_params);
    //         return method.invoke(object, params); // appeller la methode
    //     } catch (NoSuchMethodException | SecurityException e) {
    //         /// throws Exception
    //         throw e; 
    //     }
    // }




    public void insertion (Connection conn, Object object) throws Error, SQLException, IllegalArgumentException, IllegalAccessException {
        
        String tableName = est_annoterTable(object);
        PreparedStatement stmt = null;
        String req_insertion = "INSERT INTO "+tableName+" (";
        
        List <Field> fields = getFields_annoter(object);
        // format nom colonne 
        
        for (Field field : fields) {
            Colonne colonne = field.getAnnotation(Colonne.class);
            if (colonne != null && colonne.insert()) {
                req_insertion += colonne.nom()+",";
            }
        }
        // values colonne ...
        for (Field field : fields) {
            field.setAccessible(true); // donner permission
            Object objValue = field.get(object);
            
            if (objValue != null) {
                
            }
            req_insertion += field.getAnnotation(Colonne.class).nom()+", ";
            field.setAccessible(false); // retirer
        }
        req_insertion += ") VALUES";
        try {
            stmt = conn.prepareStatement(req_insertion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        finally {
            if (stmt != null) stmt.close();
        }

    }

    private String est_annoterTable (Object object) throws Error {
        
        if (!object.getClass().isAnnotationPresent(Table.class)) {
            throw new Error("CLASSE "+object.getClass().getName()+" N'EST PAS ANNOTER");
        }
        return object.getClass().getAnnotation(Table.class).nom();
    }

    
    private List <Field> getFields_annoter (Object object) {
        // GET FILED ANNOTER COLONNE
        List <Field> fields_annoter = new ArrayList <> ();
        
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Colonne.class)) {
                fields_annoter.add(field);
            }
        }
        
        return fields_annoter;
    }




}
