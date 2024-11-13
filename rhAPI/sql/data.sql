-- Insérer des données dans la table etat_entretien (corrigé)
INSERT INTO etat_entretien (etat_entretien, niveau)
VALUES ('Refuse', 1),
       ('Candidat', 2),
       ('1e Entretien', 3),
       ('1e Entretien valide', 4),
       ('2e Entretien', 5),
       ('En attente de contrat', 6),
       ('Contrat valide', 7);

-- Insérer des données dans la table type_contrat (corrigé)
INSERT INTO type_contrat (type_contrat)
VALUES ('Contrat d''essaie'),
       ('Contrat duree determine (CDD)'),
       ('Contrat duree indetermine (CDI)');
INSERT INTO competence (competence)
VALUES ('Cloud Computing'),
       ('Machine Learning'),
       ('Big Data Analysis'),
       ('Gestion de projet agile'),
       ('UX/UI Design'),
       ('Développement mobile'),
       ('Cybersecurity'),
       ('Base de données SQL'),
       ('JavaScript'),
       ('Python'),
       ('Développement backend'),
       ('Docker'),
       ('Kubernetes');


INSERT INTO competence (competence)
VALUES ('Communication'),
       ('Leadership'),
       ('Programmation'),
       ('Gestion de projet'),
       ('Design');

INSERT INTO talent (nom, prenom, mail, password, is_admin)
VALUES ('Dupont', 'Jean', 'jean.dupont@example.com', 'password1', true),
       ('Martin', 'Alice', 'alice.martin@example.com', 'password2', false),
       ('Petit', 'Louis', 'louis.petit@example.com', 'password3', false),
       ('Durand', 'Emma', 'emma.durand@example.com', 'password4', false),
       ('Leroy', 'Paul', 'paul.leroy@example.com', 'password5', false);

INSERT INTO poste (nom_poste, description_poste)
VALUES ('Développeur', 'Responsable du développement des applications'),
       ('Chef de projet', 'Gestion et coordination des projets'),
       ('Designer', 'Création des interfaces graphiques et visuels'),
       ('Analyste', 'Analyse des besoins fonctionnels et techniques'),
       ('Support technique', 'Assistance et résolution des problèmes techniques');

INSERT INTO annonce (date_annonce, entreprise, date_expiration, id_poste)
VALUES ('2024-01-01', 'Tech Corp', '2024-03-01', 1),
       ('2024-02-15', 'Design Studio', '2024-04-15', 3),
       ('2024-03-10', 'Data Solutions', '2024-05-10', 4),
       ('2024-04-05', 'IT Services', '2024-06-05', 2),
       ('2024-05-01', 'Global Consulting', '2024-07-01', 5);

INSERT INTO formation (nom_formation, date_debut, date_fin)
VALUES ('Formation Java', '2023-01-10', '2023-03-10'),
       ('Gestion de projet', '2023-02-01', '2023-04-01'),
       ('Design UX/UI', '2023-05-15', '2023-07-15'),
       ('Sécurité informatique', '2023-06-20', '2023-08-20'),
       ('Big Data', '2023-09-10', '2023-11-10');

INSERT INTO type_rupture (nom_type_rupture)
VALUES ('Fin de contrat'),
       ('Démission'),
       ('Licenciement'),
       ('Rupture amiable'),
       ('Retraite');

INSERT INTO contrat (contrat, date_debut, date_fin, salaire_horaire, nb_jour_semaine, nb_jour_conge_an, nb_heure_jour,
                     id_poste, id_talent, id_type_contrat)
VALUES ('Contrat CDI Développeur', '2023-01-01', '2024-01-01', 20.00, 5, 25, 8.0, 1, 1, 3),
       ('Contrat CDD Designer', '2023-02-01', '2023-08-01', 18.00, 5, 20, 7.5, 3, 2, 2),
       ('Contrat CDD Analyste', '2023-05-15', NULL, 22.00, 5, 30, 8.0, 4, 3, 2),
       ('Contrat d''essai Chef de Projet', '2023-06-01', NULL, 25.00, 4, 15, 7.0, 2, 4, 1),
       ('Contrat CDI Support technique', '2024-01-01', NULL, 19.50, 5, 25, 7.5, 5, 5, 3);

INSERT INTO entretien (date_creation, date_validation, note, motif, id_enfant, id_annonce, id_etat_entretien, id_talent)
VALUES ('2023-02-15', '2023-02-20', 85, 'Entretien positif', NULL, 1, 4, 1),
       ('2023-03-10', '2023-03-15', 90, 'Candidat qualifié', NULL, 2, 5, 2),
       ('2023-04-05', NULL, NULL, 'En attente de validation', 1, 3, 1, 3),
       ('2023-05-01', '2023-05-05', 70, 'Compétences insuffisantes', NULL, 4, 2, 4),
       ('2023-06-01', NULL, NULL, 'Annulation', 2, 5, 6, 5);

INSERT INTO rupture (date_rupture, motif, id_type_rupture, id_contrat)
VALUES ('2024-01-01', 'Fin de contrat', 1, 1),
       ('2023-08-01', 'Fin de CDD', 1, 2),
       ('2023-10-01', 'Licenciement', 3, 3);

INSERT INTO poste (nom_poste, description_poste)
VALUES ('Ingénieur DevOps', 'Assurer le déploiement et l''intégration continue des applications'),
       ('Data Scientist', 'Analyser et interpréter des données complexes pour guider les décisions stratégiques'),
       ('Responsable RH', 'Gestion des ressources humaines et développement du personnel'),
       ('Consultant', 'Conseiller les entreprises dans leur stratégie de croissance'),
       ('Développeur Frontend', 'Développement de l''interface utilisateur des applications web'),
       ('Architecte Logiciel', 'Conception et gestion de l''architecture des systèmes informatiques');

INSERT INTO annonce (date_annonce, entreprise, date_expiration, id_poste)
VALUES ('2024-10-15', 'Innovative Solutions', '2025-01-15', 6),
       ('2024-11-01', 'DataXplore', '2025-02-01', 7),
       ('2024-11-05', 'GreenTech', '2025-03-01', 8),
       ('2024-11-07', 'Consultancy Hub', '2025-04-10', 9),
       ('2024-11-09', 'WebGenius', '2025-05-15', 10),
       ('2024-11-10', 'CodeCrafters', '2025-06-20', 11);

-- Ingénieur DevOps (poste 6)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (14, 1, 5), -- Docker
       (15, 1, 5), -- Kubernetes
       (12, 1, 4);
-- Leadership

-- Data Scientist (poste 7)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (2, 2, 5),  -- Machine Learning
       (3, 2, 5),  -- Big Data Analysis
       (10, 2, 4), -- Python
       (1, 2, 4);
-- Cloud Computing

-- Responsable RH (poste 8)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (12, 3, 5), -- Leadership
       (10, 3, 4), -- Communication
       (1, 3, 3);
-- Cloud Computing

-- Consultant (poste 9)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (4, 4, 5),  -- Gestion de projet agile
       (12, 4, 4), -- Leadership
       (10, 4, 3);
-- Communication

-- Développeur Frontend (poste 10)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (9, 5, 5), -- JavaScript
       (5, 5, 4), -- UX/UI Design
       (13, 5, 4);
-- Développement frontend

-- Architecte Logiciel (poste 11)
INSERT INTO competence_annonce (id_competence, id_annonce, point)
VALUES (13, 6, 5), -- Développement backend
       (14, 6, 4), -- Docker
       (1, 6, 4);
-- Cloud Computing

-- Ingénieur DevOps (poste 6)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (6, 1, 3);
-- 3 ans d'expérience en Ingénierie DevOps

-- Data Scientist (poste 7)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (7, 2, 2);
-- 2 ans d'expérience en Data Science

-- Responsable RH (poste 8)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (8, 3, 5);
-- 5 ans d'expérience en gestion des ressources humaines

-- Consultant (poste 9)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (9, 4, 4);
-- 4 ans d'expérience en consultation

-- Développeur Frontend (poste 10)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (10, 5, 3);
-- 3 ans d'expérience en développement frontend

-- Architecte Logiciel (poste 11)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (11, 6, 6); -- 6 ans d'expérience en architecture logicielle

INSERT INTO conge (date_debut, date_validation, nb_jour, motif, id_contrat)
VALUES ('2024-12-01', '2024-12-01', 5, 'Congé annuel', 1),      -- Congé annuel pour le contrat 1
       ('2024-12-15', '2024-12-15', 3, 'Congé maladie', 2),     -- Congé maladie pour le contrat 2
       ('2024-11-20', 7, 'Congé parental', 3),    -- Congé parental pour le contrat 3
       ('2024-11-05', 10, 'Congé sabbatique', 4), -- Congé sabbatique pour le contrat 4
       ('2024-12-10', '2024-12-10', 4, 'Congé urgent', 5),
       ('2024-08-10', '2024-08-10', 5, 'Congé annuel', 1);
