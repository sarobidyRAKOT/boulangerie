package mg.ITU.boulangerie.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Colonne {
    
    String nom();
    boolean insert () default true;
}
