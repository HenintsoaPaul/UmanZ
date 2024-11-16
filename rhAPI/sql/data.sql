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

INSERT INTO niveau_langue(nom_niveau_langue,niveau_langue)
VALUES ('Debutant',1),
       ('Intermediaire',2),
       ('Avance',3);

INSERT INTO langue(langue)
VALUES ('Malagasy'),
       ('Français'),
       ('Anglais'),
       ('Allemand'),
       ('Mandarin');

INSERT INTO niveau_diplome(nom_niveau_diplome,niveau_diplome)
VALUES ('Baccalaureat',1),
       ('Licence',2),
       ('Master',3),
       ('Doctorat',4);

INSERT INTO diplome(nom_diplome,id_niveau_diplome)
VALUES ('Serie C',1),
       ('Serie D',1),
       ('Serie A',1),
       ('Informatique',2),
       ('Informatique',3),
       ('Informatique',4),
       ('Gestion',2),
       ('Gestion',3),
       ('Gestion',4),
       ('Mathematique',2),
       ('Mathematique',3),
       ('Mathematique',4);

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

insert into talent_langue(id_talent,id_langue,id_niveau_langue)
VALUES (1,1,3),
       (1,2,2),
       (1,3,1),
       (2,1,3),
       (2,2,3),
       (2,4,2),
       (3,1,2),
       (3,3,3),
       (4,1,2),
       (4,2,2),
       (4,3,3),
       (4,4,3),
       (5,1,1);

insert into talent_diplome(id_talent,id_diplome)
VALUES (1,1),
       (1,4),
       (1,5),
       (2,2),
       (2,7),
       (3,1),
       (3,10),
       (4,2),
       (4,4),
       (5,1),
       (5,10),
       (5,11);

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
       ('2023-05-01', NULL, 70, 'Compétences insuffisantes', NULL, 4, 2, 4),
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

insert into annonce_langue (id_annonce,id_langue,id_niveau_langue)
VALUES (1,1,3),
       (1,2,2),
       (1,3,1),
       (2,2,3),
       (2,3,2),
       (2,1,3),
       (3,1,1),
       (3,4,3),
       (3,5,1),
       (4,1,3),
       (5,1,2),
       (5,2,2),
       (6,1,1),
       (6,5,3);

insert into annonce_diplome (id_annonce,id_diplome)
values (1,4),
       (2,5),
       (2,10),
       (3,2),
       (4,7),
       (5,12),
       (6,1),
       (6,2);

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
       ('2024-11-20', null ,7, 'Congé parental', 3),    -- Congé parental pour le contrat 3
       ('2024-11-05', null ,10, 'Congé sabbatique', 4), -- Congé sabbatique pour le contrat 4
       ('2024-12-10', '2024-12-10', 4, 'Congé urgent', 5),
       ('2024-08-10', '2024-08-10', 5, 'Congé annuel', 1);

INSERT INTO chat (mot_cle, reponse) VALUES
                                        ('au revoir', 'Au revoir :)'),
                                        ('bye', 'Au revoir :)'),
                                        ('salut', 'Bonjour, comment puis-je vous aider?'),
                                        ('bonjour', 'Bonjour, comment puis-je vous aider?'),
                                        ('postuler', 'Pour postuler, veuillez vous rendre sur notre page de recrutement et créer un compte.'),
                                        ('processus', 'Le processus de recrutement comprend la soumission de votre CV, un test technique, et un entretien.'),
                                        ('test technique', 'Le test technique se déroule en ligne. Vous recevrez un lien pour y accéder une fois votre candidature validée.'),
                                        ('entretien', 'Après le test technique, nous organiserons un entretien avec notre équipe RH.'),
                                        ('suivi', 'Vous pouvez suivre l’avancement de votre candidature sur votre tableau de bord personnel dans votre compte.'),
                                        ('compétences', 'Nous recherchons des compétences spécifiques selon les postes. Veuillez consulter l’annonce pour plus de détails.'),
                                        ('contrat', 'Le type de contrat dépend de votre profil et de notre offre actuelle.'),
                                        ('formation', 'Nous offrons des programmes de formation pour développer les compétences nécessaires après votre embauche.'),
                                        ('annonce', 'Les annonces pour les postes ouverts sont disponibles sur notre site de recrutement.'),
                                        ('congé', 'Les conditions de congé sont définies dans le contrat de travail selon le poste.'),
                                        ('candidature', 'Pour déposer votre candidature, remplissez le formulaire en ligne sur la page de l’offre.'),
                                        ('profil', 'Pour consulter ou mettre à jour votre profil, connectez-vous à votre compte.'),
                                        ('salaire', 'Le salaire dépend de votre expérience et du poste auquel vous postulez.'),
                                        ('culture d’entreprise', 'Nous valorisons le respect, la collaboration et l’innovation au sein de notre entreprise.'),
                                        ('aide', 'Je suis là pour vous aider ! Posez-moi vos questions sur le recrutement ou le processus de candidature.'),
                                        ('contact', 'Pour toute question spécifique, vous pouvez nous contacter par email à recrutement@entreprise.com.');
insert into compatibilite(id_annonce,id_talent,pourcentage)
values (1,1,20.23),
       (1,2,50.14),
       (1,3,33.33),
       (1,4,44.44),
       (1,5,82.25),
       (2,1,61.125),
       (2,2,15.122),
       (2,3,39.99),
       (2,4,24.75),
       (2,5,32.25),
       (3,1,12.44),
       (3,2,22.55),
       (3,3,10.26),
       (3,4,52.12),
       (3,5,63.45),
       (4,1,30.25),
       (4,2,72.15),
       (4,3,75),
       (4,4,46),
       (4,5,43.15),
       (5,1,14.25),
       (5,2,37.15),
       (5,3,69.999),
       (5,4,74.365),
       (5,5,17.35),
       (6,1,20.153),
       (6,2,46.58),
       (6,3,73.145),
       (6,4,83.63),
       (6,5,93.333);

insert into experience_talent(id_talent,id_poste,ans)
values (1,1,4),
       (1,5,1),
       (2,2,1),
       (2,4,5),
       (3,1,3),
       (4,6,2),
       (4,3,2),
       (4,10,4),
       (5,8,3),
       (5,11,3);
-- Insertion des questions de projet (subjectives)
INSERT INTO question_projet (question) VALUES
                                              ('Comment géreriez-vous un conflit dans une équipe de projet ?'),
                                              ('Quelle est votre approche pour résoudre un problème de communication entre les membres d''une équipe ?'),
                                              ('Comment motiveriez-vous une équipe face à un projet difficile ?'),
                                              ('Quelle est votre méthode pour prioriser les tâches dans un projet complexe ?'),
                                              ('Comment gérez-vous la pression lorsqu''un projet doit respecter des délais serrés ?');

-- Insertion des domaines
INSERT INTO domaine (domaine) VALUES
                                     ('Gestion de projet'),
                                     ('Communication'),
                                     ('Résolution de conflits'),
                                     ('Leadership'),
                                     ('Planification stratégique');

-- Insertion des questions techniques avec 3 réponses différentes
INSERT INTO question_technique (question, id_domaine) VALUES
                                                             ('Quelles technologies utiliseriez-vous pour gérer un projet Agile ?', 1),
                                                             ('Quelles sont les principales méthodologies de gestion de projet ?', 1),
                                                             ('Comment établir une charte de communication dans un projet ?', 2),
                                                             ('Quels outils recommandez-vous pour la gestion des conflits dans une équipe ?', 3),
                                                             ('Comment définir des indicateurs de performance pour un projet ?', 4),
                                                             ('Quels sont les risques majeurs dans la planification d''un projet et comment les anticiper ?', 5);

-- Insertion des réponses aux questions techniques (avec 3 réponses par question)
INSERT INTO reponse (reponse, note, id_question_technique) VALUES
-- Question 1 : Quelles technologies utiliseriez-vous pour gérer un projet Agile ?
('Utilisation de Jira et Trello pour gérer le backlog et les sprints.', 8.5, 1),
('Utilisation de Monday.com pour la gestion des tâches et de la planification Agile.', 7.5, 1),
('Trello avec des tableaux Kanban pour la gestion des tâches, suivi de l''avancement et des sprints.', 8.0, 1),
-- Question 2 : Quelles sont les principales méthodologies de gestion de projet ?
('Scrum et Kanban sont les méthodologies les plus courantes.', 9.0, 2),
('Waterfall et Agile sont les deux plus utilisées selon la complexité du projet.', 8.0, 2),
('Scrum pour les projets de développement logiciel et Kanban pour la gestion des flux de travail.', 7.5, 2),
-- Question 3 : Comment établir une charte de communication dans un projet ?
('Création de canaux de communication clairs et utilisation de plateformes comme Slack.', 7.5, 3),
('Réunions hebdomadaires et mise en place d''un document partagé sur Google Drive.', 8.0, 3),
('Utilisation d''outils comme Asana pour la gestion de la communication entre les membres de l''équipe.', 8.5, 3),
-- Question 4 : Quels outils recommandez-vous pour la gestion des conflits dans une équipe ?
('Mise en place de réunions de médiation et utilisation de méthodes comme le feedback constructif.', 8.0, 4),
('Utilisation d''outils de feedback 360° pour aider à identifier et résoudre les conflits.', 7.0, 4),
('Organisation de discussions ouvertes et ateliers de team building pour améliorer la cohésion.', 8.5, 4),
-- Question 5 : Comment définir des indicateurs de performance pour un projet ?
('Définition d''objectifs SMART et suivis réguliers des KPI.', 8.0, 5),
('Utilisation de critères comme les délais, le budget et la qualité des livrables.', 8.5, 5),
('Suivi des performances à l''aide de tableaux de bord dynamiques et de KPIs mesurables.', 9.0, 5),
-- Question 6 : Quels sont les risques majeurs dans la planification d''un projet et comment les anticiper ?
('Analyse SWOT et mise en place de plans de contingence.', 7.0, 6),
('Identification des risques techniques, humains et financiers et mise en place de stratégies de mitigation.', 8.0, 6),
('Réévaluation régulière des risques et ajustement des plans selon les imprévus.', 7.5, 6);

-- Insertion des évaluations
INSERT INTO evaluation (date_evaluation) VALUES
                                                ('2024-11-01'),
                                                ('2024-11-02'),
                                                ('2024-11-03'),
                                                ('2024-11-04'),
                                                ('2024-11-05');

-- Insertion des résultats techniques des talents
INSERT INTO resultat_technique (note, date_resultat, id_domaine, id_talent) VALUES
                                                                                   (8.0, '2024-11-01 10:00:00', 1, 1),
                                                                                   (7.5, '2024-11-01 10:30:00', 2, 1),
                                                                                   (9.0, '2024-11-02 11:00:00', 3, 2),
                                                                                   (6.5, '2024-11-03 12:00:00', 4, 3),
                                                                                   (8.0, '2024-11-04 13:00:00', 5, 4),
                                                                                   (7.0, '2024-11-05 14:00:00', 1, 5);

-- Insertion des réponses des talents aux questions de projet
INSERT INTO question_talent (id_talent, id_question, id_evaluation, note, reponse) VALUES
                                                                                          (1, 1, 1, 8.5, 'Il est essentiel d''écouter chaque partie et de trouver un compromis.'),
                                                                                          (1, 2, 1, 7.0, 'Mettre en place des réunions régulières et des canaux de communication adaptés.'),
                                                                                          (2, 3, 2, 9.0, 'Encourager les membres de l''équipe à prendre des initiatives et reconnaître leurs efforts.'),
                                                                                          (3, 4, 3, 6.0, 'Il est important de se concentrer sur les tâches à haute priorité et de déléguer efficacement.'),
                                                                                          (4, 5, 4, 8.0, 'Organiser des réunions régulières pour suivre les progrès et ajuster les priorités en conséquence.'),
                                                                                          (5, 1, 5, 7.5, 'Prendre une approche calme et structurée pour gérer la pression et respecter les délais.');

-- Question 1
INSERT INTO question_technique (question, id_domaine)
VALUES
    ('Quelle est la meilleure pratique pour la gestion de la mémoire dans une application Java ?', 1);

-- Question 2
INSERT INTO question_technique (question, id_domaine)
VALUES
    ('Comment optimiser les requêtes SQL dans une base de données relationnelle ?', 2);

-- Question 3
INSERT INTO question_technique (question, id_domaine)
VALUES
    ('Comment sécuriser une application web utilisant JWT (JSON Web Token) ?', 3);

-- Question 4
INSERT INTO question_technique (question, id_domaine)
VALUES
    ('Quelle approche recommanderiez-vous pour gérer les exceptions dans une application Spring Boot ?', 4);

-- Question 5
INSERT INTO question_technique (question, id_domaine)
VALUES
    ('Quel est l''intérêt de la méthode `Stream.forEach()` en Java ?', 5);

-- Réponses pour la question 1
INSERT INTO reponse (reponse, note, id_question_technique)
VALUES
    ('Utiliser des variables locales et éviter les références globales.', 3.5, 1),
    ('S''assurer que toutes les ressources sont fermées dans un bloc "finally".', 4.0, 1),
    ('Utiliser des structures de données dynamiques comme des ArrayLists.', 2.5, 1);

-- Réponses pour la question 2
INSERT INTO reponse (reponse, note, id_question_technique)
VALUES
    ('Utiliser des index sur les colonnes fréquemment utilisées dans les conditions de jointure ou de filtrage.', 4.5, 2),
    ('Utiliser des requêtes imbriquées pour réduire le nombre de lignes retournées.', 3.0, 2),
    ('Utiliser la pagination pour limiter le nombre de résultats renvoyés par la requête.', 3.5, 2);

-- Réponses pour la question 3
INSERT INTO reponse (reponse, note, id_question_technique)
VALUES
    ('Stocker le token dans le "localStorage" côté client.', 2.5, 3),
    ('Utiliser HTTPS pour chiffrer la communication entre le client et le serveur.', 5.0, 3),
    ('Éviter l''utilisation des tokens JWT pour l''authentification.', 1.5, 3);

-- Réponses pour la question 4
INSERT INTO reponse (reponse, note, id_question_technique)
VALUES
    ('Utiliser des blocs "try-catch" dans chaque méthode pour attraper les exceptions.', 3.0, 4),
    ('Utiliser des contrôleurs globaux annotés avec "@ControllerAdvice" pour centraliser la gestion des erreurs.', 4.0, 4),
    ('Lancer directement les exceptions sans les gérer dans les méthodes.', 2.0, 4);

-- Réponses pour la question 5
INSERT INTO reponse (reponse, note, id_question_technique)
VALUES
    ('Elle permet de traiter les éléments d''une collection de manière parallèle, ce qui améliore les performances.', 4.0, 5),
    ('Elle permet de simplifier l''écriture du code en supprimant les boucles classiques.', 3.0, 5),
    ('Elle ne permet pas d''optimiser les performances, mais rend le code plus lisible.', 2.0, 5);

-- Question 1
INSERT INTO question_projet (question)
VALUES
    ('Comment abordez-vous la gestion des risques dans un projet technique ?');

-- Question 2
INSERT INTO question_projet (question)
VALUES
    ('Quelles stratégies utilisez-vous pour motiver une équipe sur un projet long terme ?');

-- Question 3
INSERT INTO question_projet (question)
VALUES
    ('De quelle manière ajustez-vous un projet lorsque les attentes du client changent en cours de réalisation ?');

-- Question 4
INSERT INTO question_projet (question)
VALUES
    ('Comment assurez-vous que tous les membres de l''équipe comprennent bien leurs rôles et responsabilités ?');

-- Question 5
INSERT INTO question_projet (question)
VALUES
    ('Quelles sont vos méthodes pour faire face à des délais serrés dans un projet ?');
