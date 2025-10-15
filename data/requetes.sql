

-- ============================= TESTE REQUETES ======================================

select
    id_vendeur,
    nom,
    prix_ttl,
    commission
from commission
where daty >= '2024-02-01' and daty <= '2024-09-01' and id_vendeur = 1;

-- min max 

select
    id_vendeur,
    nom,
    sum (prix_ttl) prix_ttl,
    sum (commission) commission
from commission
group by id_vendeur;


SELECT 
    g.genre AS genre,
    SUM(c.commission) AS total_commission,
    COUNT(c.id_vente) AS nbr_ventes,
    SUM(c.prix_ttl) AS prix_ttl
FROM 
    commission c
LEFT JOIN vendeur v ON c.id_vendeur = v.id_vendeur
LEFT JOIN genre g ON v.id_genre = g.id_genre
WHERE c.daty BETWEEN '2024-05-01' AND '2024-12-31'
GROUP BY g.genre;



SELECT 
    g.genre AS genre,
    SUM(c.commission) AS total_commission,
    COUNT(c.id_vente) AS nbr_ventes,
    SUM(c.prix_ttl) AS prix_ttl
FROM 
    commission c
LEFT JOIN vendeur v ON c.id_vendeur = v.id_vendeur
LEFT JOIN genre g ON v.id_genre = g.id_genre
GROUP BY g.genre;


SELECT
    pv.id_prixvente AS id,
    pv.id_produit AS id_produit,
    pv.prix AS prix,
    pv.daty AS daty,
    pv.motif AS motif,
    p.nom nom
FROM prix_vente pv 
LEFT JOIN produit p ON pv.id_produit = p.id_produit
WHERE pv.id_produit = 1;


INSERT INTO prix_vente (id_produit, daty, motif, prix) VALUES
(8, '2024-02-18', 'PROMOTION', 50),
(8, '2024-10-05', 'AUG', 150);



SELECT
    p.id_produit id_produit,
    p.nom nom,
    p.id_categorie id_categorie,
    p.id_unite id_unite,
    pv.prix prix
FROM produit p 
LEFT JOIN (
    -- prix de vente actuel
    SELECT id_produit, MAX(daty) daty, prix
    FROM prix_vente
    GROUP BY id_produit, prix
) pv ON p.id_produit = pv.id_produit;


SELECT DISTINCT ON (p.id_produit) 
    p.id_produit id_produit,
    p.nom nom,
    p.id_categorie id_categorie,
    p.id_unite id_unite,
    pv.prix prix
FROM produit p
LEFT JOIN prix_vente pv 
    ON p.id_produit = pv.id_produit
    AND pv.daty <= '2025-11-20'  -- On récupère uniquement les prix avant ou à cette date
ORDER BY p.id_produit, pv.daty DESC;


-- RECUPERER DETAIL PRODUIT (params daty)
SELECT DISTINCT ON (p.id_produit)
    p.id_produit AS id_produit,
    p.nom AS nom_produit,
    ct.id_categorie AS id_categorie,
    u.id_unite AS id_unite,
    pv.prix AS prix,
    rp.reste AS reste
FROM produit p
LEFT JOIN prix_vente pv ON p.id_produit = pv.id_produit AND pv.daty <= CURRENT_DATE
LEFT JOIN reste_produit rp on p.id_produit = rp.id_produit
LEFT JOIN categorie ct on p.id_categorie = ct.id_categorie
LEFT JOIN unite u on p.id_unite = u.id_unite
ORDER BY p.id_produit, pv.daty DESC;


SELECT DISTINCT ON (p.id_produit)
     p.id_produit AS id_produit,
     p.nom AS nom_produit,
     ct.id_categorie AS id_categorie,
     u.id_unite AS id_unite,
     pv.prix AS prix,
     rp.reste AS reste
 FROM produit p
 LEFT JOIN prix_vente pv ON p.id_produit = pv.id_produit AND pv.daty <= CURRENT_DATE
 LEFT JOIN reste_produit rp on p.id_produit = rp.id_produit
 LEFT JOIN categorie ct on p.id_categorie = ct.id_categorie
 LEFT JOIN unite u on p.id_unite = u.id_unite
 ORDER BY p.id_produit, pv.daty DESC