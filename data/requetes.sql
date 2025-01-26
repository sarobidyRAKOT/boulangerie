

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
