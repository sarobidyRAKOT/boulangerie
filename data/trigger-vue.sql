
CREATE OR REPLACE FUNCTION prix_vente ()
RETURNS TRIGGER AS $$ 
BEGIN 
    IF (TG_OP = 'INSERT') THEN
        INSERT INTO prix_vente (prix, daty, id_produit)
        VALUES (NEW.prix, CURRENT_DATE, NEW.id_produit);
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO prix_vente (prix, daty, id_produit)
        VALUES (OLD.prix, CURRENT_DATE, OLD.id_produit);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_prix_vente
AFTER INSERT OR UPDATE ON produit
FOR EACH ROW
EXECUTE FUNCTION prix_vente();





-- ============================================ VUE =========================================

-- VUE RESTE PRODUIT ...
CREATE OR REPLACE VIEW reste_produit AS
SELECT 
    SS.id_produit, 
    SE.qtt_entre,
    SS.qtt_sorti,
    (SE.qtt_entre - SS.qtt_sorti) reste
FROM (
    SELECT id_produit, SUM(quantite) qtt_entre
    FROM stock_produit sp
    WHERE id_mvtstock = 1
    GROUP BY id_produit, id_mvtstock
) AS SE
LEFT JOIN (
    SELECT id_produit, SUM(quantite) qtt_sorti
    FROM stock_produit sp
    WHERE id_mvtstock = 2
    GROUP BY id_produit, id_mvtstock
) AS SS 
ON SE.id_produit = SS.id_produit;


-- VUE POUR CALCULER LES COMMISSION DES VENDEURS
CREATE OR REPLACE VIEW commission AS
SELECT
    v.id_vente id_vente,
    vend.id_vendeur id_vendeur,
    vend.nom nom,
    v.prix_ttl prix_ttl, 
    v.daty daty,
    ROUND((v.prix_ttl * v.commission) / 100, 2) commission
FROM vente v 
LEFT JOIN vendeur vend ON v.id_vendeur = vend.id_vendeur;

