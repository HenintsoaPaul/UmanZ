CREATE TABLE competence(
   id_competence SERIAL,
   competence VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_competence),
   UNIQUE(competence)
);

CREATE TABLE talents(
   id_candidat SERIAL,
   nom VARCHAR(50)  NOT NULL,
   prenom VARCHAR(50)  NOT NULL,
   mail VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_candidat)
);

CREATE TABLE poste(
   id_poste SERIAL,
   description_poste VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_poste)
);

CREATE TABLE etat_entretien(
   id_etat_entretien SERIAL,
   etat_entretien VARCHAR(100)  NOT NULL,
   PRIMARY KEY(id_etat_entretien)
);

CREATE TABLE type_contrat(
   id_type_contrat SERIAL,
   type_contrat VARCHAR(50) ,
   PRIMARY KEY(id_type_contrat)
);

CREATE TABLE annonce(
   id_annonce SERIAL,
   date_annonce DATE NOT NULL,
   id_poste INTEGER NOT NULL,
   PRIMARY KEY(id_annonce),
   FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE entretien(
   id_entretien SERIAL,
   date_entretien DATE NOT NULL,
   note INTEGER,
   motif VARCHAR(255) ,
   id_etat_entretien INTEGER NOT NULL,
   id_candidat INTEGER NOT NULL,
   id_poste INTEGER NOT NULL,
   PRIMARY KEY(id_entretien),
   FOREIGN KEY(id_etat_entretien) REFERENCES etat_entretien(id_etat_entretien),
   FOREIGN KEY(id_candidat) REFERENCES talents(id_candidat),
   FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE contrat(
   id_contrat SERIAL,
   contrat VARCHAR(100)  NOT NULL,
   date_debut DATE NOT NULL,
   date_fin DATE,
   id_candidat INTEGER NOT NULL,
   id_type_contrat INTEGER NOT NULL,
   PRIMARY KEY(id_contrat),
   FOREIGN KEY(id_candidat) REFERENCES talents(id_candidat),
   FOREIGN KEY(id_type_contrat) REFERENCES type_contrat(id_type_contrat)
);

CREATE TABLE candidat_competence(
   id_competence INTEGER,
   id_candidat INTEGER,
   PRIMARY KEY(id_competence, id_candidat),
   FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
   FOREIGN KEY(id_candidat) REFERENCES talents(id_candidat)
);

CREATE TABLE Asso_12(
   id_competence INTEGER,
   id_annonce INTEGER,
   PRIMARY KEY(id_competence, id_annonce),
   FOREIGN KEY(id_competence) REFERENCES competence(id_competence),
   FOREIGN KEY(id_annonce) REFERENCES annonce(id_annonce)
);

CREATE TABLE experience_utilisateur(
   id_candidat INTEGER,
   id_poste INTEGER,
   ans NUMERIC(11,2)   NOT NULL,
   PRIMARY KEY(id_candidat, id_poste),
   FOREIGN KEY(id_candidat) REFERENCES talents(id_candidat),
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
