package mg.ITU.boulangerie.utils;

import java.io.IOException;
import java.util.Properties;

public class LoaderProperties {
    
    public static Properties load_properties (String file_name) {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(file_name));
            
        } catch (IOException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }
        return properties;
    }

}
