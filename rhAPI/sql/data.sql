insert into etat_entretien(etat_entretien) values
    ('Non valide'),
    ('Valide'),
    ('2e Entretien'),
    ('En attente de contrat');

insert into type_contrat(type_contrat) values
    ('Contrat d''essaie'),
    ('Contrat duree determine (CDD)'),
    ('Contrat duree indetermine');

-- Insertion de données dans la table `poste`
INSERT INTO poste(description_poste) VALUES
    ('Développeur Backend'),
    ('Développeur Frontend'),
    ('Chef de Projet'),
    ('Data Analyst');

-- Insertion de données dans la table `competence`
INSERT INTO competence(competence) VALUES
    ('Java'),
    ('JavaScript'),
    ('Gestion de projet'),
    ('Analyse de données');

-- Insertion de données dans la table `talents`
INSERT INTO talent(nom, prenom, mail,password) VALUES
    ('Dupont', 'Jean', 'jean.dupont@example.com',''),
    ('Martin', 'Sophie', 'sophie.martin@example.com',''),
    ('Durand', 'Pierre', 'pierre.durand@example.com',''),
    ('Bernard', 'Lucie', 'lucie.bernard@example.com','');

-- Insertion de données dans la table `annonce`
INSERT INTO annonce(date_annonce, id_poste) VALUES
    ('2024-01-15', 1), -- Développeur Backend
    ('2024-02-20', 2), -- Développeur Frontend
    ('2024-03-10', 3), -- Chef de Projet
    ('2024-04-05', 4); -- Data Analyst

-- Insertion de données dans la table `entretien`
INSERT INTO entretien(date_entretien, note, motif, id_annonce, id_etat_entretien, id_talent) VALUES
    ('2024-01-20', 85, 'Bon profil technique', 1, 2, 1), -- Entretien validé
    ('2024-02-25', 70, 'Connaissances insuffisantes', 2, 1, 2), -- Entretien non validé
    ('2024-03-15', 90, 'Très bonne maîtrise du sujet', 3, 3, 3), -- 2e entretien
    ('2024-04-10', NULL, 'En attente d''une réponse', 4, 4, 4); -- En attente de contrat

-- Insertion de données dans la table `contrat`
INSERT INTO contrat(contrat, date_debut, date_fin, id_talent, id_type_contrat) VALUES
    ('Contrat Développeur Backend', '2024-02-01', '2025-02-01', 1, 2), -- CDD pour Jean Dupont
    ('Contrat Développeur Frontend', '2024-03-01', NULL, 3, 3), -- CDI pour Pierre Durand
    ('Contrat Chef de Projet', '2024-04-01', NULL, 4, 3); -- CDI pour Lucie Bernard

-- Insertion de données dans la table `talent_competence`
INSERT INTO talent_competence(id_competence, id_talent) VALUES
    (1, 1), -- Jean Dupont a la compétence Java
    (2, 2), -- Sophie Martin a la compétence JavaScript
    (3, 3), -- Pierre Durand a la compétence Gestion de projet
    (4, 4); -- Lucie Bernard a la compétence Analyse de données

-- Insertion de données dans la table `competence_annonce`
INSERT INTO competence_annonce(id_competence, id_annonce) VALUES
    (1, 1), -- Java pour l'annonce de Développeur Backend
    (2, 2), -- JavaScript pour l'annonce de Développeur Frontend
    (3, 3), -- Gestion de projet pour l'annonce de Chef de Projet
    (4, 4); -- Analyse de données pour l'annonce de Data Analyst

-- Insertion de données dans la table `experience_utilisateur`
INSERT INTO experience_talent(id_talent, id_poste, ans) VALUES
    (1, 1, 2.5), -- Jean Dupont a 2.5 ans d'expérience en Développement Backend
    (2, 2, 1.0), -- Sophie Martin a 1 an d'expérience en Développement Frontend
    (3, 3, 3.0), -- Pierre Durand a 3 ans d'expérience en Gestion de projet
    (4, 4, 1.5); -- Lucie Bernard a 1.5 ans d'expérience en Analyse de données

-- Insertion de données dans la table `experience_poste`
INSERT INTO experience_poste(id_poste, id_annonce, ans) VALUES
    (1, 1, 2.0), -- 2 ans d'expérience requis pour l'annonce de Développeur Backend
    (2, 2, 1.0), -- 1 an d'expérience requis pour l'annonce de Développeur Frontend
    (3, 3, 2.5), -- 2.5 ans d'expérience requis pour l'annonce de Chef de Projet
    (4, 4, 1.0); -- 1 an d'expérience requis pour l'annonce de Data Analyst
