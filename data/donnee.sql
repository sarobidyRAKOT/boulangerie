INSERT INTO categorie (nom) VALUES 
('Pains'),
('Viennoiseries'),
('Patisseries'),
('Produits sales'),
('Biscuits et produits annexes'),
('Specialites de fêtes');

INSERT INTO unite (nom, reference) VALUES 
('Piece', 'PCE'),
('Kilogramme', 'KG'),
('Lot', 'LOT');


INSERT INTO genre (genre) VALUES 
('Homme'),
('Femme');

INSERT INTO produit (nom, prix, id_categorie, id_unite) VALUES 
('Pain tradition', 1.20 * 4500, 1, 1),
('Pain complet', 2.50 * 4500, 1, 1),
('Pain aux graines', 3.00 * 4500, 1, 1),
('Pain de mie artisanal', 4.00 * 4500, 1, 1),
('Croissant au beurre', 1.10 * 4500, 2, 1),
('Pain au chocolat', 1.20 * 4500, 2, 1),
('Brioche nature', 3.00 * 4500, 2, 1);
-- ('Eclair au chocolat', 3.50 * 4500, 3, 1),
-- ('Paris-Brest', 4.00 * 4500, 3, 1),
-- ('Tartelette aux fruits', 4.50 * 4500, 3, 1),
-- ('Millefeuille', 4.00 * 4500, 3, 1),
-- ('Quiche lorraine', 4.50 * 4500, 4, 1),
-- ('Croque-monsieur', 4.00 * 4500, 4, 1),
-- ('Pain pour burgers artisanaux', 3.50 * 4500, 4, 1),
-- ('Feuillete au jambon', 2.80 * 4500, 4, 1),
-- ('Financiers', 3.50 * 4500, 5, 1),
-- ('Confitures maison', 5.00 * 4500, 5, 1),
-- ('Mini-focaccia', 1.80 * 4500, 5, 1),
-- ('Galette des rois', 12.00 * 4500, 6, 1),
-- ('Buche patissiere', 25.00 * 4500, 6, 1),
-- ('Chocolats maison', 15.00 * 4500, 6, 1),
-- ('Pain d epices', 6.50 * 4500, 6, 1);


INSERT INTO vendeur (nom, id_genre) VALUES 
('Jean Dupont', 1),
('Marie Durant', 2),
('Paul Morel', 1),
('Sophie Martin', 2),
('Luc Bernard', 1);

INSERT INTO client (nom, tel, id_genre) VALUES 
('Alice Lefèvre', '0612345678', 2),
('Marc Petit', '0678901234', 1),
('Chloé Renault', '0654321098', 2),
('Julien Lambert', '0623456789', 1),
('Emma Girard', '0698765432', 2);

INSERT INTO vente (prix_ttl, daty, id_vendeur, id_client) VALUES 
(500.00, '2024-01-15', 1, 1),
(1200.50, '2024-02-10', 2, 2),
(800.00, '2024-03-05', 3, 3),
(450.25, '2024-04-18', 4, 4),
(999.99, '2024-05-25', 5, 5),
(1500.00, '2024-06-12', 1, 2),
(720.00, '2024-07-04', 2, 3),
(300.75, '2024-08-19', 3, 4),
(1250.00, '2024-09-10', 4, 5),
(2000.00, '2024-10-21', 5, 1),
(540.00, '2024-11-14', 1, 3),
(880.00, '2024-12-01', 2, 4),
(600.50, '2024-12-15', 3, 5),
(1750.00, '2024-12-20', 4, 1),
(900.00, '2024-01-30', 5, 2),
(1100.00, '2024-02-25', 1, 4),
(300.00, '2024-03-15', 2, 5),
(125.50, '2024-04-10', 3, 1),
(200.00, '2024-05-28', 4, 2),
(1800.00, '2024-06-30', 5, 3);


