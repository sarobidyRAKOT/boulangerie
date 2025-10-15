CREATE TABLE mvt_stock(
   id_mvtstock SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_mvtstock)
);

CREATE TABLE unite(
   id_unite VARCHAR(50) ,
   nom VARCHAR(50)  NOT NULL,
   reference VARCHAR(10)  NOT NULL,
   PRIMARY KEY(id_unite)
);

CREATE TABLE achat(
   id_achat SERIAL,
   daty DATE NOT NULL,
   prix_ttl NUMERIC(15,2)   NOT NULL,
   PRIMARY KEY(id_achat)
);

CREATE TABLE categorie(
   id_categorie VARCHAR(50) ,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_categorie)
);

CREATE TABLE genre(
   id_genre VARCHAR(50) ,
   genre VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_genre),
   UNIQUE(genre)
);

CREATE TABLE ingredient(
   id_ingredient SERIAL,
   nom VARCHAR(50)  NOT NULL,
   id_unite VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_ingredient),
   FOREIGN KEY(id_unite) REFERENCES unite(id_unite)
);

CREATE TABLE produit(
   id_produit SERIAL,
   nom VARCHAR(50)  NOT NULL,
   id_categorie VARCHAR(50)  NOT NULL,
   id_unite VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_produit),
   FOREIGN KEY(id_categorie) REFERENCES categorie(id_categorie),
   FOREIGN KEY(id_unite) REFERENCES unite(id_unite)
);

CREATE TABLE prix_vente(
   id_prixvente SERIAL,
   prix NUMERIC(15,2)   NOT NULL,
   motif TEXT,
   daty DATE NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_prixvente),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE formule(
   id_formule SERIAL,
   daty DATE NOT NULL,
   description VARCHAR(50)  NOT NULL,
   quantite_produite INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_formule),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE stock_produit(
   id_stockproduit SERIAL,
   daty DATE NOT NULL,
   quantite NUMERIC(15,2)   NOT NULL,
   id_mvtstock INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_stockproduit),
   FOREIGN KEY(id_mvtstock) REFERENCES mvt_stock(id_mvtstock),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE stock_ingredient(
   Id_stockingredient SERIAL,
   daty DATE NOT NULL,
   quantite NUMERIC(15,2)   NOT NULL,
   id_mvtstock INTEGER NOT NULL,
   id_ingredient INTEGER NOT NULL,
   PRIMARY KEY(Id_stockingredient),
   FOREIGN KEY(id_mvtstock) REFERENCES mvt_stock(id_mvtstock),
   FOREIGN KEY(id_ingredient) REFERENCES ingredient(id_ingredient)
);

CREATE TABLE achart_ingredient(
   Id_achartingredient SERIAL,
   quantite NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(15,2)   NOT NULL,
   id_achat INTEGER NOT NULL,
   id_ingredient INTEGER NOT NULL,
   PRIMARY KEY(Id_achartingredient),
   FOREIGN KEY(id_achat) REFERENCES achat(id_achat),
   FOREIGN KEY(id_ingredient) REFERENCES ingredient(id_ingredient)
);

CREATE TABLE produit_mois(
   id_produitmois SERIAL,
   mois VARCHAR(2)  NOT NULL,
   daty DATE NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_produitmois),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE client(
   id_client SERIAL,
   nom VARCHAR(50)  NOT NULL,
   tel VARCHAR(50) ,
   id_genre VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_client),
   FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE vendeur(
   id_vendeur SERIAL,
   nom VARCHAR(50)  NOT NULL,
   id_genre VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_vendeur),
   FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE vente(
   id_vente SERIAL,
   prix_ttl NUMERIC(15,2)   NOT NULL,
   daty DATE NOT NULL,
   commission NUMERIC(15,2)   NOT NULL,
   id_vendeur INTEGER NOT NULL,
   id_client INTEGER NOT NULL,
   PRIMARY KEY(id_vente),
   FOREIGN KEY(id_vendeur) REFERENCES vendeur(id_vendeur),
   FOREIGN KEY(id_client) REFERENCES client(id_client)
);

CREATE TABLE production(
   id_production SERIAL,
   quantite INTEGER NOT NULL,
   daty DATE NOT NULL,
   prix_production NUMERIC(15,2)   NOT NULL,
   id_formule INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id_production),
   FOREIGN KEY(id_formule) REFERENCES formule(id_formule),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE vente_detail(
   Id_ventedetail SERIAL,
   quantite_produit NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(15,2)   NOT NULL,
   id_vente INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(Id_ventedetail),
   FOREIGN KEY(id_vente) REFERENCES vente(id_vente),
   FOREIGN KEY(id_produit) REFERENCES produit(id_produit)
);

CREATE TABLE formule_detail(
   id_ingredient INTEGER,
   id_formule INTEGER,
   quantite_ingredient NUMERIC(15,2)   NOT NULL,
   PRIMARY KEY(id_ingredient, id_formule),
   FOREIGN KEY(id_ingredient) REFERENCES ingredient(id_ingredient),
   FOREIGN KEY(id_formule) REFERENCES formule(id_formule)
);

CREATE TABLE production_detail(
   id_ingredient INTEGER,
   id_production INTEGER,
   quantite_ingredient NUMERIC(15,2)   NOT NULL,
   prixtotal_ingredient NUMERIC(15,2)   NOT NULL,
   PRIMARY KEY(id_ingredient, id_production),
   FOREIGN KEY(id_ingredient) REFERENCES ingredient(id_ingredient),
   FOREIGN KEY(id_production) REFERENCES production(id_production)
);
