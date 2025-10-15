INSERT INTO categorie (id_categorie, nom) VALUES 
('Pains', 'Pains'),
('Viennoiseries', 'Viennoiseries'),
('Patisseries', 'Patisseries'),
('Produits sales', 'Produits sales'),
('Biscuits et produits annexes', 'Biscuits et produits annexes'),
('Specialites de fêtes', 'Specialites de fêtes');

INSERT INTO unite (id_unite, nom, reference) VALUES 
('Piece', 'Piece', 'PCE'),
('Kilogramme', 'Kilogramme', 'KG'),
('Lot', 'Lot', 'LOT');


INSERT INTO genre (id_genre, genre) VALUES 
('Homme', 'Homme'),
('Femme', 'Femme');

-- INSERT INTO produit (nom, id_categorie, id_unite) VALUES 
-- ('Pain tradition', 1, 1),
-- ('Pain complet', 1, 1),
-- ('Pain aux graines', 1, 1),
-- ('Pain de mie artisanal', 1, 1),
-- ('Croissant au beurre', 2, 1),
-- ('Pain au chocolat', 2, 1),
-- ('Brioche nature', 2, 1);


INSERT INTO vendeur (nom, id_genre) VALUES 
('Jean Dupont', 'Homme'),
('Marie Durant', 'Femme'),
('Paul Morel', 'Homme'),
('Sophie Martin', 'Femme'),
('Luc Bernard', 'Homme');

INSERT INTO client (nom, tel, id_genre) VALUES 
('Alice Lefèvre', '0612345678', 'Femme'),
('Marc Petit', '0678901234', 'Homme'),
('Chloé Renault', '0654321098', 'Femme'),
('Julien Lambert', '0623456789', 'Homme'),
('Emma Girard', '0698765432', 'Femme');

-- INSERT INTO vente (prix_ttl, daty, id_vendeur, id_client) VALUES 
-- (500.00, '2024-01-15', 1, 1),
-- (1200.50, '2024-02-10', 2, 2),
-- (800.00, '2024-03-05', 3, 3),
-- (450.25, '2024-04-18', 4, 4),
-- (999.99, '2024-05-25', 5, 5),
-- (1500.00, '2024-06-12', 1, 2),
-- (720.00, '2024-07-04', 2, 3),
-- (300.75, '2024-08-19', 3, 4),
-- (1250.00, '2024-09-10', 4, 5),
-- (2000.00, '2024-10-21', 5, 1),
-- (540.00, '2024-11-14', 1, 3),
-- (880.00, '2024-12-01', 2, 4),
-- (600.50, '2024-12-15', 3, 5),
-- (1750.00, '2024-12-20', 4, 1),
-- (900.00, '2024-01-30', 5, 2),
-- (1100.00, '2024-02-25', 1, 4),
-- (300.00, '2024-03-15', 2, 5),
-- (125.50, '2024-04-10', 3, 1),
-- (200.00, '2024-05-28', 4, 2),
-- (1800.00, '2024-06-30', 5, 3);


