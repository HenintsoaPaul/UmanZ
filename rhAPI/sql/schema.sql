CREATE TABLE competence(
                           id_competence SERIAL,
                           competence VARCHAR(50)  NOT NULL,
                           PRIMARY KEY(id_competence),
                           UNIQUE(competence)
);

CREATE TABLE talent(
                       id_talent SERIAL,
                       nom VARCHAR(50)  NOT NULL,
                       prenom VARCHAR(50)  NOT NULL,
                       mail VARCHAR(50)  NOT NULL,
                       password VARCHAR(255) ,
                       is_admin BOOLEAN NOT NULL,
                       PRIMARY KEY(id_talent)
);

CREATE TABLE poste(
                      id_poste SERIAL,
                      nom_poste VARCHAR(50)  NOT NULL,
                      description_poste VARCHAR(255)  NOT NULL,
                      PRIMARY KEY(id_poste)
);

CREATE TABLE etat_entretien(
                               id_etat_entretien SERIAL,
                               etat_entretien VARCHAR(100)  NOT NULL,
                               niveau SMALLINT,
                               PRIMARY KEY(id_etat_entretien),
                               UNIQUE(niveau)
);

CREATE TABLE type_contrat(
                             id_type_contrat SERIAL,
                             type_contrat VARCHAR(50) ,
                             PRIMARY KEY(id_type_contrat)
);

CREATE TABLE annonce(
                        id_annonce SERIAL,
                        date_annonce DATE NOT NULL,
                        entreprise VARCHAR(50) ,
                        date_expiration DATE,
                        date_rupture DATE,
                        id_poste INTEGER NOT NULL,
                        PRIMARY KEY(id_annonce),
                        FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE formation(
                          id_formation SERIAL,
                          nom_formation VARCHAR(50)  NOT NULL,
                          date_debut DATE NOT NULL,
                          date_fin DATE NOT NULL,
                          PRIMARY KEY(id_formation)
);

CREATE TABLE type_rupture(
                             id_type_rupture SERIAL,
                             nom_type_rupture VARCHAR(50)  NOT NULL,
                             PRIMARY KEY(id_type_rupture),
                             UNIQUE(nom_type_rupture)
);

CREATE TABLE entretien(
                          id_entretien SERIAL,
                          date_creation DATE NOT NULL,
                          date_validation DATE,
                          note INTEGER,
                          motif VARCHAR(255) ,
                          id_enfant INTEGER,
                          id_annonce INTEGER NOT NULL,
                          id_etat_entretien INTEGER NOT NULL,
                          id_talent INTEGER NOT NULL,
                          PRIMARY KEY(id_entretien),
                          FOREIGN KEY(id_enfant) REFERENCES entretien(id_entretien),
                          FOREIGN KEY(id_annonce) REFERENCES annonce(id_annonce),
                          FOREIGN KEY(id_etat_entretien) REFERENCES etat_entretien(id_etat_entretien),
                          FOREIGN KEY(id_talent) REFERENCES talent(id_talent)
);

CREATE TABLE contrat(
                        id_contrat SERIAL,
                        contrat VARCHAR(100)  NOT NULL,
                        date_debut DATE NOT NULL,
                        date_fin DATE,
                        salaire_horaire NUMERIC(15,2)   NOT NULL,
                        nb_jour_semaine INTEGER NOT NULL,
                        nb_jour_conge_an INTEGER NOT NULL,
                        nb_heure_jour NUMERIC(15,2)   NOT NULL,
                        id_poste INTEGER NOT NULL,
                        id_talent INTEGER NOT NULL,
                        id_type_contrat INTEGER NOT NULL,
                        PRIMARY KEY(id_contrat),
                        FOREIGN KEY(id_poste) REFERENCES poste(id_poste),
                        FOREIGN KEY(id_talent) REFERENCES talent(id_talent),
                        FOREIGN KEY(id_type_contrat) REFERENCES type_contrat(id_type_contrat)
);

CREATE TABLE rupture(
                        id_rupture SERIAL,
                        date_rupture DATE NOT NULL,
                        motif VARCHAR(50) ,
                        id_type_rupture INTEGER NOT NULL,
                        id_contrat INTEGER NOT NULL,
                        PRIMARY KEY(id_rupture),
                        UNIQUE(id_contrat),
                        FOREIGN KEY(id_type_rupture) REFERENCES type_rupture(id_type_rupture),
                        FOREIGN KEY(id_contrat) REFERENCES contrat(id_contrat)
);

CREATE TABLE conge(
                      id_conge SERIAL,
                      date_debut DATE NOT NULL,
                      nb_jour INTEGER NOT NULL,
                      motif VARCHAR(50) ,
                      date_validation DATE,
                      id_contrat INTEGER NOT NULL,
                      PRIMARY KEY(id_conge),
                      FOREIGN KEY(id_contrat) REFERENCES contrat(id_contrat)
);

CREATE TABLE absence(
                        id_absence SERIAL,
                        motif VARCHAR(50) ,
                        date_absence DATE NOT NULL,
                        id_contrat INTEGER NOT NULL,
                        PRIMARY KEY(id_absence),
                        FOREIGN KEY(id_contrat) REFERENCES contrat(id_contrat)
);

CREATE TABLE talent_competence(
                                  id_competence INTEGER,
                                  id_talent INTEGER,
                                  point INTEGER,
                                  PRIMARY KEY(id_competence, id_talent),
                                  FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
                                  FOREIGN KEY(id_talent) REFERENCES talent(id_talent)
);

CREATE TABLE competence_annonce(
                                   id_competence INTEGER,
                                   id_annonce INTEGER,
                                   point INTEGER NOT NULL,
                                   PRIMARY KEY(id_competence, id_annonce),
                                   FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
                                   FOREIGN KEY(id_annonce) REFERENCES annonce(id_annonce)
);

CREATE TABLE experience_talent(
                                  id_talent INTEGER,
                                  id_poste INTEGER,
                                  ans NUMERIC(11,2)   NOT NULL,
                                  PRIMARY KEY(id_talent, id_poste),
                                  FOREIGN KEY(id_talent) REFERENCES talent(id_talent),
                                  FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE experience_poste(
                                 id_poste INTEGER,
                                 id_annonce INTEGER,
                                 ans NUMERIC(10,2)  ,
                                 PRIMARY KEY(id_poste, id_annonce),
                                 FOREIGN KEY(id_poste) REFERENCES poste(id_poste),
                                 FOREIGN KEY(id_annonce) REFERENCES annonce(id_annonce)
);

CREATE TABLE competence_cible(
                                 id_competence INTEGER,
                                 id_formation INTEGER,
                                 point_gagne INTEGER NOT NULL,
                                 PRIMARY KEY(id_competence, id_formation),
                                 FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
                                 FOREIGN KEY(id_formation) REFERENCES formation(id_formation)
);

CREATE TABLE participation(
                              id_contrat INTEGER,
                              id_formation INTEGER,
                              date_inscription DATE NOT NULL,
                              PRIMARY KEY(id_contrat, id_formation),
                              FOREIGN KEY(id_contrat) REFERENCES contrat(id_contrat),
                              FOREIGN KEY(id_formation) REFERENCES formation(id_formation)
);

CREATE TABLE candidat_historique (
    id_candidat_histo SERIAL PRIMARY KEY,
    id_talent INTEGER,
    action VARCHAR(255),
    description VARCHAR(255),
    date_action TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_talent FOREIGN KEY (id_talent) REFERENCES Talent(id_talent) ON DELETE CASCADE
);

CREATE TABLE categories (
    id_categories SERIAL PRIMARY KEY ,
    nom VARCHAR(50),
    description TEXT
);

CREATE TABLE categories_position (
    id_poste INTEGER,      
    id_categories INTEGER,
    point_categorie INTEGER,
    PRIMARY KEY (id_poste, id_categories), -- Clé primaire composite
    FOREIGN KEY (id_poste) REFERENCES poste(id_poste),
    FOREIGN KEY (id_categories) REFERENCES categories(id_categories)
);

CREATE TABLE employe ( 
    id_employee SERIAL PRIMARY KEY ,
    nom VARCHAR(50),
    email VARCHAR(100),
    id_poste INTEGER,
    id_categories INTEGER,
    date_of_hire DATE,
    FOREIGN KEY (id_poste, id_categories) REFERENCES categories_position (id_poste, id_categories)
); 