CREATE TABLE talent(
                       id_talent SERIAL,
                       nom VARCHAR(50)  NOT NULL,
                       prenom VARCHAR(50)  NOT NULL,
                       mail VARCHAR(50)  NOT NULL,
                       password VARCHAR(255) ,
                       is_admin BOOLEAN NOT NULL,
                       PRIMARY KEY(id_talent)
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

CREATE TABLE niveau_diplome(
                               id_niveau_diplome SERIAL,
                               niveau_diplome INTEGER NOT NULL,
                               nom_niveau_diplome VARCHAR(50)  NOT NULL,
                               PRIMARY KEY(id_niveau_diplome),
                               UNIQUE(niveau_diplome)
);

CREATE TABLE langue(
                       id_langue SERIAL,
                       langue VARCHAR(50) ,
                       PRIMARY KEY(id_langue)
);

CREATE TABLE niveau_langue(
                              id_niveau_langue SERIAL,
                              nom_niveau_langue VARCHAR(50) ,
                              niveau_langue INTEGER,
                              PRIMARY KEY(id_niveau_langue),
                              UNIQUE(niveau_langue)
);

CREATE TABLE chat(
                     id_chat SERIAL,
                     mot_cle VARCHAR(50)  NOT NULL,
                     reponse TEXT NOT NULL,
                     PRIMARY KEY(id_chat),
                     UNIQUE(mot_cle)
);

CREATE TABLE domaine(
                        id_domaine SERIAL,
                        domaine VARCHAR(50)  NOT NULL,
                        PRIMARY KEY(id_domaine),
                        UNIQUE(domaine)
);

CREATE TABLE type_conge(
                           id_type_conge SERIAL,
                           nom_type_conge VARCHAR(50)  NOT NULL,
                           PRIMARY KEY(id_type_conge)
);

CREATE TABLE type_justificatif(
                                  id_type_justificatif SERIAL,
                                  nom_type_justificatif VARCHAR(50)  NOT NULL,
                                  PRIMARY KEY(id_type_justificatif)
);

CREATE TABLE evenement_interne(
                                  id_evenement_interne SERIAL,
                                  nom_evenement_libre VARCHAR(50)  NOT NULL,
                                  date_evenement_interne DATE NOT NULL,
                                  lieu_evenement_interne VARCHAR(50)  NOT NULL,
                                  description TEXT,
                                  img_evenement_interne TEXT,
                                  PRIMARY KEY(id_evenement_interne)
);

CREATE TABLE type_competence(
                                id_type_competence SERIAL,
                                nom_type_competence VARCHAR(50)  NOT NULL,
                                PRIMARY KEY(id_type_competence)
);

CREATE TABLE type_poste(
                           id_type_poste SERIAL,
                           nom_type_poste VARCHAR(50)  NOT NULL,
                           rang_type_poste INTEGER NOT NULL,
                           PRIMARY KEY(id_type_poste)
);

CREATE TABLE competence(
                           id_competence SERIAL,
                           competence VARCHAR(50)  NOT NULL,
                           id_type_competence INTEGER NOT NULL,
                           PRIMARY KEY(id_competence),
                           UNIQUE(competence),
                           FOREIGN KEY(id_type_competence) REFERENCES type_competence(id_type_competence)
);

CREATE TABLE poste(
                      id_poste SERIAL,
                      nom_poste VARCHAR(50)  NOT NULL,
                      description_poste VARCHAR(255)  NOT NULL,
                      id_type_poste INTEGER NOT NULL,
                      PRIMARY KEY(id_poste),
                      FOREIGN KEY(id_type_poste) REFERENCES type_poste(id_type_poste)
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

CREATE TABLE absence(
                        id_absence SERIAL,
                        motif VARCHAR(50) ,
                        date_absence DATE NOT NULL,
                        id_contrat INTEGER NOT NULL,
                        PRIMARY KEY(id_absence),
                        FOREIGN KEY(id_contrat) REFERENCES contrat(id_contrat)
);

CREATE TABLE diplome(
                        id_diplome SERIAL,
                        nom_diplome VARCHAR(50) ,
                        id_niveau_diplome INTEGER NOT NULL,
                        PRIMARY KEY(id_diplome),
                        FOREIGN KEY(id_niveau_diplome) REFERENCES niveau_diplome(id_niveau_diplome)
);

CREATE TABLE question(
                         id_question SERIAL,
                         question TEXT NOT NULL,
                         id_domaine INTEGER NOT NULL,
                         PRIMARY KEY(id_question),
                         UNIQUE(question),
                         FOREIGN KEY(id_domaine) REFERENCES domaine(id_domaine)
);

CREATE TABLE reponse(
                        id_reponse SERIAL,
                        reponse TEXT NOT NULL,
                        note NUMERIC(15,2)   NOT NULL,
                        id_question INTEGER NOT NULL,
                        PRIMARY KEY(id_reponse),
                        FOREIGN KEY(id_question) REFERENCES question(id_question)
);

CREATE TABLE resultat_technique(
                                   id_resultat_technique SERIAL,
                                   note NUMERIC(15,2)  ,
                                   date_resultat TIMESTAMP,
                                   id_domaine INTEGER NOT NULL,
                                   id_talent INTEGER NOT NULL,
                                   PRIMARY KEY(id_resultat_technique),
                                   FOREIGN KEY(id_domaine) REFERENCES domaine(id_domaine),
                                   FOREIGN KEY(id_talent) REFERENCES talent(id_talent)
);

CREATE TABLE justificatif(
                             id_justificatif SERIAL,
                             date_justificatif DATE NOT NULL,
                             image_justificatif TEXT NOT NULL,
                             id_type_justificatif INTEGER NOT NULL,
                             PRIMARY KEY(id_justificatif),
                             FOREIGN KEY(id_type_justificatif) REFERENCES type_justificatif(id_type_justificatif)
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

CREATE TABLE conge(
                      id_conge SERIAL,
                      nb_jour INTEGER NOT NULL,
                      motif VARCHAR(50) ,
                      date_debut DATE NOT NULL,
                      date_validation DATE,
                      motif_refus TEXT,
                      date_refus DATE,
                      id_justificatif INTEGER,
                      id_type_conge INTEGER NOT NULL,
                      id_contrat INTEGER NOT NULL,
                      PRIMARY KEY(id_conge),
                      FOREIGN KEY(id_justificatif) REFERENCES justificatif(id_justificatif),
                      FOREIGN KEY(id_type_conge) REFERENCES type_conge(id_type_conge),
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

CREATE TABLE talent_diplome(
                               id_talent INTEGER,
                               id_diplome INTEGER,
                               PRIMARY KEY(id_talent, id_diplome),
                               FOREIGN KEY(id_talent) REFERENCES talent(id_talent),
                               FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);

CREATE TABLE talent_langue(
                              id_talent INTEGER,
                              id_langue INTEGER,
                              id_niveau_langue INTEGER,
                              PRIMARY KEY(id_talent, id_langue, id_niveau_langue),
                              FOREIGN KEY(id_talent) REFERENCES talent(id_talent),
                              FOREIGN KEY(id_langue) REFERENCES langue(id_langue),
                              FOREIGN KEY(id_niveau_langue) REFERENCES niveau_langue(id_niveau_langue)
);

CREATE TABLE diplome_poste(
                              id_poste INTEGER,
                              id_diplome INTEGER,
                              PRIMARY KEY(id_poste, id_diplome),
                              FOREIGN KEY(id_poste) REFERENCES poste(id_poste),
                              FOREIGN KEY(id_diplome) REFERENCES diplome(id_diplome)
);

CREATE TABLE langue_poste(
                             id_poste INTEGER,
                             id_langue INTEGER,
                             id_niveau_langue INTEGER,
                             PRIMARY KEY(id_poste, id_langue, id_niveau_langue),
                             FOREIGN KEY(id_poste) REFERENCES poste(id_poste),
                             FOREIGN KEY(id_langue) REFERENCES langue(id_langue),
                             FOREIGN KEY(id_niveau_langue) REFERENCES niveau_langue(id_niveau_langue)
);

CREATE TABLE compatibilite(
                              id_talent INTEGER,
                              id_poste INTEGER,
                              pourcentage NUMERIC(15,2)  ,
                              PRIMARY KEY(id_talent, id_poste),
                              FOREIGN KEY(id_talent) REFERENCES talent(id_talent),
                              FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE competence_poste(
                                 id_competence INTEGER,
                                 id_poste INTEGER,
                                 point_requis INTEGER NOT NULL,
                                 PRIMARY KEY(id_competence, id_poste),
                                 FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
                                 FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE candidat_historique
(
    id_candidat_histo SERIAL PRIMARY KEY,
    id_talent         INTEGER,
    action            VARCHAR(255),
    description       VARCHAR(255),
    date_action       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_talent FOREIGN KEY (id_talent) REFERENCES Talent (id_talent) ON DELETE CASCADE
);
