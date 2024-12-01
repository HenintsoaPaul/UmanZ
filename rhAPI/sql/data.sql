-- Inserer des donnees dans la table etat_entretien (corrige)
INSERT INTO etat_entretien (etat_entretien, niveau)
VALUES ('Refuse', 1),
       ('Candidat', 2),
       ('1e Entretien', 3),
       ('1e Entretien valide', 4),
       ('2e Entretien', 5),
       ('En attente de contrat', 6),
       ('Contrat valide', 7);

-- Inserer des donnees dans la table type_contrat (corrige)
INSERT INTO type_contrat (type_contrat)
VALUES ('Contrat d''essaie'),
       ('Contrat duree determine (CDD)'),
       ('Contrat duree indetermine (CDI)');

INSERT INTO niveau_langue(nom_niveau_langue, niveau_langue)
VALUES ('Debutant', 1),
       ('Intermediaire', 2),
       ('Avance', 3);

INSERT INTO langue(langue)
VALUES ('Malagasy'),
       ('Français'),
       ('Anglais'),
       ('Allemand'),
       ('Mandarin');

INSERT INTO niveau_diplome(nom_niveau_diplome, niveau_diplome)
VALUES ('Baccalaureat', 1),
       ('Licence', 2),
       ('Master', 3),
       ('Doctorat', 4);

INSERT INTO diplome(nom_diplome, id_niveau_diplome)
VALUES ('Serie C', 1),
       ('Serie D', 1),
       ('Serie A', 1),
       ('Informatique', 2),
       ('Informatique', 3),
       ('Informatique', 4),
       ('Gestion', 2),
       ('Gestion', 3),
       ('Gestion', 4),
       ('Mathematique', 2),
       ('Mathematique', 3),
       ('Mathematique', 4);

INSERT INTO type_competence(nom_type_competence)
VALUES ('Hard skill'),
       ('Soft skill');

INSERT INTO competence (competence, id_type_competence)
VALUES ('Cloud Computing', 1),
       ('Machine Learning', 1),
       ('Big Data Analysis', 1),
       ('Gestion de projet agile', 1),
       ('UX/UI Design', 1),
       ('Developpement mobile', 1),
       ('Cybersecurity', 1),
       ('Base de donnees SQL', 1),
       ('JavaScript', 1),
       ('Python', 1),
       ('Developpement backend', 1),
       ('Docker', 1),
       ('Kubernetes', 1);

INSERT INTO competence (competence, id_type_competence)
VALUES ('Communication', 2),
       ('Leadership', 2),
       ('Programmation', 1),
       ('Gestion de projet', 1),
       ('Design', 1);

INSERT INTO talent (nom, prenom, mail, password, is_admin,id_cnaps)
VALUES ('Dupont', 'Jean', 'jean.dupont@example.com', 'password1', true,'CNP1234'),
       ('Martin', 'Alice', 'alice.martin@example.com', 'password2', false,'CNP1564'),
       ('Petit', 'Louis', 'louis.petit@example.com', 'password3', false,'CNP34613'),
       ('Durand', 'Emma', 'emma.durand@example.com', 'password4', false,'CNP32684'),
       ('Leroy', 'Paul', 'paul.leroy@example.com', 'password5', false,'CNP354632'),
       ('Dupont', 'Jean', 'recipientgestion@gmail.com', 'recepgestion', true,'CNP48962');

insert into talent_langue(id_talent, id_langue, id_niveau_langue)
VALUES (1, 1, 3),
       (1, 2, 2),
       (1, 3, 1),
       (2, 1, 3),
       (2, 2, 3),
       (2, 4, 2),
       (3, 1, 2),
       (3, 3, 3),
       (4, 1, 2),
       (4, 2, 2),
       (4, 3, 3),
       (4, 4, 3),
       (5, 1, 1);

insert into talent_diplome(id_talent, id_diplome)
VALUES (1, 1),
       (1, 4),
       (1, 5),
       (2, 2),
       (2, 7),
       (3, 1),
       (3, 10),
       (4, 2),
       (4, 4),
       (5, 1),
       (5, 10),
       (5, 11);

INSERT INTO type_poste(nom_type_poste, rang_type_poste)
VALUES ('Ouvrier', 1),
       ('Employe', 2),
       ('Technicien et/ou Agent de Maîtrise', 3),
       ('Cadre', 4),
       ('Dirigeant', 5);

INSERT INTO poste (nom_poste, description_poste, id_type_poste)
VALUES ('Developpeur', 'Responsable du developpement des applications', 2),
       ('Chef de projet', 'Gestion et coordination des projets', 4),
       ('Designer', 'Creation des interfaces graphiques et visuels', 2),
       ('Analyste', 'Analyse des besoins fonctionnels et techniques', 3),
       ('Support technique', 'Assistance et resolution des problemes techniques', 3);

INSERT INTO annonce (date_annonce, entreprise, date_expiration, id_poste)
VALUES ('2024-01-01', 'Tech Corp', '2025-03-01', 1),
       ('2024-02-15', 'Tech Corp', '2025-04-15', 3),
       ('2024-03-10', 'Tech Corp', '2025-05-10', 4),
       ('2024-04-05', 'Tech Corp', '2025-06-05', 2),
       ('2024-05-01', 'Tech Corp', '2025-07-01', 5);

INSERT INTO formation (nom_formation, date_debut, date_fin)
VALUES ('Formation Java', '2023-01-10', '2023-03-10'),
       ('Gestion de projet', '2023-02-01', '2023-04-01'),
       ('Design UX/UI', '2023-05-15', '2023-07-15'),
       ('Securite informatique', '2023-06-20', '2023-08-20'),
       ('Big Data', '2023-09-10', '2023-11-10');

INSERT INTO type_rupture (nom_type_rupture)
VALUES ('Fin de contrat'),
       ('Demission'),
       ('Licenciement'),
       ('Rupture amiable'),
       ('Retraite');

INSERT INTO contrat (contrat, date_debut, date_fin, salaire_horaire, nb_jour_semaine, nb_jour_conge_an, nb_heure_jour,
                     id_poste, id_talent, id_type_contrat)
VALUES ('Contrat CDI Developpeur', '2023-01-01', '2024-01-01', 20.00, 5, 25, 8.0, 1, 1, 3),
       ('Contrat CDD Designer', '2023-02-01', '2023-08-01', 18.00, 5, 20, 7.5, 3, 2, 2),
       ('Contrat CDD Analyste', '2023-05-15', NULL, 22.00, 5, 30, 8.0, 4, 3, 2),
       ('Contrat d''essai Chef de Projet', '2023-06-01', NULL, 25.00, 4, 15, 7.0, 2, 4, 1),
       ('Contrat CDI Support technique', '2024-01-01', NULL, 19.50, 5, 25, 7.5, 5, 5, 3);

-- INSERT INTO entretien (date_creation, date_validation, note, motif, id_enfant, id_annonce, id_etat_entretien, id_talent)
-- VALUES ('2023-02-15', '2023-02-20', 85, 'Entretien positif', NULL, 1, 4, 1),
--        ('2023-03-10', '2023-03-15', 90, 'Candidat qualifie', NULL, 2, 5, 2),
--        ('2023-04-05', NULL, NULL, 'En attente de validation', 1, 3, 1, 3),
--        ('2023-05-01', NULL, 70, 'Competences insuffisantes', NULL, 4, 2, 4),
--        ('2023-06-01', NULL, NULL, 'Annulation', 2, 5, 6, 5);

INSERT INTO rupture (date_rupture, motif, id_type_rupture, id_contrat)
VALUES ('2024-01-01', 'Fin de contrat', 1, 1),
       ('2023-08-01', 'Fin de CDD', 1, 2),
       ('2023-10-01', 'Licenciement', 3, 3);

INSERT INTO poste (nom_poste, description_poste, id_type_poste)
VALUES ('Ingenieur DevOps', 'Assurer le deploiement et l''integration continue des applications', 3),
       ('Data Scientist', 'Analyser et interpreter des donnees complexes pour guider les decisions strategiques', 3),
       ('Responsable RH', 'Gestion des ressources humaines et developpement du personnel', 4),
       ('Consultant', 'Conseiller les entreprises dans leur strategie de croissance', 4),
       ('Developpeur Frontend', 'Developpement de l''interface utilisateur des applications web', 2),
       ('Architecte Logiciel', 'Conception et gestion de l''architecture des systemes informatiques', 3);

INSERT INTO annonce (date_annonce, entreprise, date_expiration, id_poste)
VALUES ('2024-10-15', 'Innovative Solutions', '2025-01-15', 6),
       ('2024-11-01', 'DataXplore', '2025-02-01', 7),
       ('2024-11-05', 'GreenTech', '2025-03-01', 8),
       ('2024-11-07', 'Consultancy Hub', '2025-04-10', 9),
       ('2024-11-09', 'WebGenius', '2025-05-15', 10),
       ('2024-11-10', 'CodeCrafters', '2025-06-20', 11);

insert into langue_poste (id_poste, id_langue, id_niveau_langue)
VALUES (1, 1, 3),
       (1, 2, 2),
       (1, 3, 1),
       (2, 2, 3),
       (2, 3, 2),
       (2, 1, 3),
       (3, 1, 1),
       (3, 4, 3),
       (3, 5, 1),
       (4, 1, 3),
       (5, 1, 2),
       (5, 2, 2),
       (6, 1, 1),
       (6, 5, 3);

insert into diplome_poste (id_poste, id_diplome)
values (1, 4),
       (2, 5),
       (2, 10),
       (3, 2),
       (4, 7),
       (5, 12),
       (6, 1),
       (6, 2);

-- Ingenieur DevOps (poste 6)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (14, 1, 5), -- Docker
       (15, 1, 5), -- Kubernetes
       (12, 1, 4);
-- Leadership

-- Data Scientist (poste 7)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (2, 2, 5),  -- Machine Learning
       (3, 2, 5),  -- Big Data Analysis
       (10, 2, 4), -- Python
       (1, 2, 4);
-- Cloud Computing

-- Responsable RH (poste 8)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (12, 3, 5), -- Leadership
       (10, 3, 4), -- Communication
       (1, 3, 3);
-- Cloud Computing

-- Consultant (poste 9)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (4, 4, 5),  -- Gestion de projet agile
       (12, 4, 4), -- Leadership
       (10, 4, 3);
-- Communication

-- Developpeur Frontend (poste 10)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (9, 5, 5), -- JavaScript
       (5, 5, 4), -- UX/UI Design
       (13, 5, 4);
-- Developpement frontend

-- Architecte Logiciel (poste 11)
INSERT INTO competence_poste (id_competence, id_poste, point_requis)
VALUES (13, 6, 5), -- Developpement backend
       (14, 6, 4), -- Docker
       (1, 6, 4);
-- Cloud Computing

-- Ingenieur DevOps (poste 6)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (6, 1, 3);
-- 3 ans d'experience en Ingenierie DevOps

-- Data Scientist (poste 7)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (7, 2, 2);
-- 2 ans d'experience en Data Science

-- Responsable RH (poste 8)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (8, 3, 5);
-- 5 ans d'experience en gestion des ressources humaines

-- Consultant (poste 9)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (9, 4, 4);
-- 4 ans d'experience en consultation

-- Developpeur Frontend (poste 10)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (10, 5, 3);
-- 3 ans d'experience en developpement frontend

-- Architecte Logiciel (poste 11)
INSERT INTO experience_poste (id_poste, id_annonce, ans)
VALUES (11, 6, 6);
-- 6 ans d'experience en architecture logicielle

INSERT INTO type_justificatif (nom_type_justificatif)
VALUES ('Certificat Medical'),
       ('Certificat maternite');

INSERT INTO justificatif (date_justificatif, image_justificatif, id_type_justificatif)
VALUES ('2024-01-01', '/img/fuf.png', 1),
       ('2024-01-01', '/img/fuf.png', 2);

INSERT INTO type_conge(nom_type_conge)
VALUES ('Annuel'),
       ('Medical'),
       ('Evènement spécial');

INSERT INTO conge (date_debut, date_validation, nb_jour, motif, id_contrat, id_type_conge)
VALUES ('2024-12-01', '2024-12-01', 5, 'Conge annuel', 1, 1),  -- Conge annuel pour le contrat 1
       ('2024-12-15', '2024-12-15', 3, 'Conge maladie', 2, 2), -- Conge maladie pour le contrat 2
       ('2024-11-20', null, 7, 'Conge parental', 3, 1),        -- Conge parental pour le contrat 3
       ('2024-11-05', null, 10, 'Conge sabbatique', 4, 1),     -- Conge sabbatique pour le contrat 4
       ('2024-12-10', '2024-12-10', 4, 'Conge urgent', 5, 3),
       ('2024-08-10', '2024-08-10', 5, 'Conge annuel', 1, 1);

INSERT INTO chat (mot_cle, reponse)
VALUES ('au revoir', 'Au revoir :)'),
       ('bye', 'Au revoir :)'),
       ('salut', 'Bonjour, comment puis-je vous aider?'),
       ('bonjour', 'Bonjour, comment puis-je vous aider?'),
       ('postuler', 'Pour postuler, veuillez vous rendre sur notre page de recrutement et creer un compte.'),
       ('processus',
        'Le processus de recrutement comprend la soumission de votre CV, un test technique, et un entretien.'),
       ('test technique',
        'Le test technique se deroule en ligne. Vous recevrez un lien pour y acceder une fois votre candidature validee.'),
       ('entretien', 'Apres le test technique, nous organiserons un entretien avec notre equipe RH.'),
       ('suivi',
        'Vous pouvez suivre l''avancement de votre candidature sur votre tableau de bord personnel dans votre compte.'),
       ('competences',
        'Nous recherchons des competences specifiques selon les postes. Veuillez consulter l''annonce pour plus de details.'),
       ('contrat', 'Le type de contrat depend de votre profil et de notre offre actuelle.'),
       ('formation',
        'Nous offrons des programmes de formation pour developper les competences necessaires apres votre embauche.'),
       ('annonce', 'Les annonces pour les postes ouverts sont disponibles sur notre site de recrutement.'),
       ('conge', 'Les conditions de conge sont definies dans le contrat de travail selon le poste.'),
       ('candidature', 'Pour deposer votre candidature, remplissez le formulaire en ligne sur la page de l''offre.'),
       ('profil', 'Pour consulter ou mettre a jour votre profil, connectez-vous a votre compte.'),
       ('salaire', 'Le salaire depend de votre experience et du poste auquel vous postulez.'),
       ('culture d''entreprise',
        'Nous valorisons le respect, la collaboration et l''innovation au sein de notre entreprise.'),
       ('aide',
        'Je suis la pour vous aider ! Posez-moi vos questions sur le recrutement ou le processus de candidature.'),
       ('contact',
        'Pour toute question specifique, vous pouvez nous contacter par email a recrutement@entreprise.com.');

insert into compatibilite(id_poste, id_talent, pourcentage)
values (1, 1, 20.23),
       (1, 2, 50.14),
       (1, 3, 33.33),
       (1, 4, 44.44),
       (1, 5, 82.25),
       (2, 1, 61.125),
       (2, 2, 15.122),
       (2, 3, 39.99),
       (2, 4, 24.75),
       (2, 5, 32.25),
       (3, 1, 12.44),
       (3, 2, 22.55),
       (3, 3, 10.26),
       (3, 4, 52.12),
       (3, 5, 63.45),
       (4, 1, 30.25),
       (4, 2, 72.15),
       (4, 3, 75),
       (4, 4, 46),
       (4, 5, 43.15),
       (5, 1, 14.25),
       (5, 2, 37.15),
       (5, 3, 69.999),
       (5, 4, 74.365),
       (5, 5, 17.35),
       (6, 1, 20.153),
       (6, 2, 46.58),
       (6, 3, 73.145),
       (6, 4, 83.63),
       (6, 5, 93.333);

insert into experience_talent(id_talent, id_poste, ans)
values (1, 1, 4),
       (1, 5, 1),
       (2, 2, 1),
       (2, 4, 5),
       (3, 1, 3),
       (4, 6, 2),
       (4, 3, 2),
       (4, 10, 4),
       (5, 8, 3),
       (5, 11, 3);
-- Insertion des domaines
INSERT INTO domaine (domaine)
VALUES ('Gestion de projet'),
       ('Programmation'),
       ('Reseaux'),
       ('SQL'),
       ('Gestion');

INSERT INTO question (question, id_domaine)
VALUES
-- Gestion de projet
('Quels sont les objectifs principaux d''un diagramme de Gantt ?', 1),
('Quelle est la methodologie Agile la plus utilisee ?', 1),
('Qu''est-ce qu''un livrable dans un projet ?', 1),

-- Programmation
('Quelle est la difference entre une classe abstraite et une interface en Java ?', 2),
('Qu''est-ce qu''une fonction recursive ?', 2),
('Quelle est l''utilite de la programmation orientee objet ?', 2),

-- Reseaux
('a quoi sert un pare-feu reseau ?', 3),
('Qu''est-ce qu''une adresse IP ?', 3),
('Comment fonctionne le protocole DNS ?', 3),

-- Base de donnees
('Qu''est-ce qu''une cle primaire ?', 4),
('Quelle est la difference entre une base de donnees relationnelle et non relationnelle ?', 4),
('Comment fonctionne la normalisation d''une base de donnees ?', 4),

-- Gestion
('Quelles sont les fonctions principales d''un gestionnaire ?', 5),
('Qu''est-ce que le cycle PDCA en gestion ?', 5),
('Qu''est-ce qu''un tableau de bord de gestion ?', 5);

INSERT INTO reponse (reponse, note, id_question)
VALUES
-- Reponses pour les questions de Gestion de projet
('Il sert a planifier les taches dans le temps.', 5.0, 1),
('Il permet de suivre l''avancement d''un projet.', 4.0, 1),
('Il aide a definir les ressources necessaires.', 3.5, 1),

('Scrum est la methodologie la plus utilisee.', 5.0, 2),
('Kanban est aussi couramment utilisee.', 4.5, 2),
('Extreme Programming (XP) est une autre option.', 4.0, 2),

('Un livrable est un resultat tangible d''un projet.', 5.0, 3),
('C''est une etape dans le cycle de vie du projet.', 4.0, 3),
('Un rapport final est un exemple de livrable.', 4.5, 3),

-- Reponses pour les questions de Programmation
('Une classe abstraite peut contenir des methodes implementees.', 5.0, 4),
('Une interface ne contient que des methodes non implementees (avant Java 8).', 4.5, 4),
('Les deux servent a structurer le code.', 4.0, 4),

('C''est une fonction qui s''appelle elle-meme.', 5.0, 5),
('Elle est utilisee pour resoudre des problemes repetitifs.', 4.0, 5),
('Elle peut remplacer certaines boucles.', 3.5, 5),

('Elle ameliore la reutilisation du code.', 5.0, 6),
('Elle permet de structurer les programmes en objets.', 4.5, 6),
('Elle simplifie le developpement de grands projets.', 4.0, 6),

-- Reponses pour les questions de Reseaux
('Il filtre les connexions entrantes et sortantes.', 5.0, 7),
('Il protege le reseau contre les intrusions.', 4.5, 7),
('Il limite l''acces aux ressources non autorisees.', 4.0, 7),

('C''est une adresse unique pour identifier un appareil.', 5.0, 8),
('Elle permet la communication sur un reseau.', 4.5, 8),
('Il existe des adresses IPv4 et IPv6.', 4.0, 8),

('Le DNS convertit les noms de domaine en adresses IP.', 5.0, 9),
('Il fonctionne via des serveurs DNS hierarchiques.', 4.5, 9),
('Il facilite la navigation sur Internet.', 4.0, 9),

-- Reponses pour les questions de Base de donnees
('Une cle primaire identifie de maniere unique un enregistrement.', 5.0, 10),
('Elle ne peut pas contenir de valeurs nulles.', 4.5, 10),
('Elle est souvent utilisee pour creer des relations.', 4.0, 10),

('Les bases relationnelles utilisent des tables.', 5.0, 11),
('Les bases non relationnelles utilisent des documents ou des graphes.', 4.5, 11),
('Elles s''adaptent a differents types de donnees.', 4.0, 11),

('La normalisation reduit la redondance.', 5.0, 12),
('Elle organise les donnees en plusieurs tables.', 4.5, 12),
('Elle ameliore l''integrite des donnees.', 4.0, 12),

-- Reponses pour les questions de Gestion
('Planifier, organiser, diriger et contrôler.', 5.0, 13),
('Assurer l''atteinte des objectifs.', 4.5, 13),
('Motiver les equipes pour de meilleures performances.', 4.0, 13),

('C''est un cycle d''amelioration continue.', 5.0, 14),
('Il signifie Plan, Do, Check, Act.', 4.5, 14),
('Il est utilise pour resoudre des problemes en gestion.', 4.0, 14),

('C''est un outil pour suivre les indicateurs de performance.', 5.0, 15),
('Il aide a prendre des decisions informees.', 4.5, 15),
('Il contient des donnees graphiques et chiffrees.', 4.0, 15);

INSERT INTO question (question, id_domaine)
VALUES
-- Gestion de projet
('Quelles sont les etapes principales de la gestion de projet ?', 1),
('Comment identifier les risques dans un projet ?', 1),
('Pourquoi la communication est essentielle en gestion de projet ?', 1),

-- Programmation
('Quelle est la difference entre une boucle for et une boucle while ?', 2),
('Qu''est-ce qu''un tableau dynamique ?', 2),
('Comment gerer les exceptions en Java ?', 2),

-- Reseaux
('Qu''est-ce qu''un VLAN ?', 3),
('Quelle est la difference entre TCP et UDP ?', 3),
('Pourquoi utilise-t-on une passerelle par defaut ?', 3),

-- Base de donnees
('Qu''est-ce qu''une vue dans une base de donnees ?', 4),
('Comment fonctionne une jointure SQL ?', 4),
('Qu''est-ce qu''un index dans une base de donnees ?', 4),

-- Gestion
('Comment evaluer la performance d''une equipe ?', 5),
('Quels sont les avantages de la delegation ?', 5),
('Qu''est-ce que la gestion participative ?', 5);

INSERT INTO reponse (reponse, note, id_question)
VALUES
-- Reponses pour Gestion de projet
('Les etapes sont: initiation, planification, execution, contrôle et clôture.', 5.0, 16),
('Chaque etape est essentielle pour le succes du projet.', 4.5, 16),
('Le contrôle garantit le respect des delais et du budget.', 4.0, 16),

('Analyser les scenarios possibles et leurs impacts.', 5.0, 17),
('Creer un registre des risques pour le suivi.', 4.5, 17),
('Prioriser les risques selon leur probabilite et gravite.', 4.0, 17),

('Elle permet de reduire les malentendus.', 5.0, 18),
('Elle maintient les parties prenantes alignees.', 4.5, 18),
('Elle ameliore la collaboration entre les equipes.', 4.0, 18),

-- Reponses pour Programmation
('La boucle for est utilisee pour un nombre connu d''iterations.', 5.0, 19),
('La boucle while s''execute tant qu''une condition est vraie.', 4.5, 19),
('Les deux permettent de repeter des instructions.', 4.0, 19),

('C''est un tableau dont la taille peut etre modifiee.', 5.0, 20),
('Il est souvent utilise avec des structures comme ArrayList.', 4.5, 20),
('Il offre plus de flexibilite qu''un tableau statique.', 4.0, 20),

('On utilise try-catch pour gerer les exceptions.', 5.0, 21),
('Le bloc finally est execute apres le traitement.', 4.5, 21),
('Les exceptions personnalisees permettent une gestion specifique.', 4.0, 21),

-- Reponses pour Reseaux
('Un VLAN divise un reseau en segments logiques.', 5.0, 22),
('Il ameliore la securite et reduit le trafic inutile.', 4.5, 22),
('Il permet de regrouper les appareils par fonction.', 4.0, 22),

('TCP est oriente connexion et fiable.', 5.0, 23),
('UDP est sans connexion et plus rapide.', 4.5, 23),
('TCP est utilise pour le web, UDP pour le streaming.', 4.0, 23),

('Elle permet aux appareils de communiquer en dehors du reseau local.', 5.0, 24),
('La passerelle envoie les paquets au bon routeur.', 4.5, 24),
('Sans passerelle, aucun trafic externe n''est possible.', 4.0, 24),

-- Reponses pour Base de donnees
('Une vue est une table virtuelle basee sur une requete.', 5.0, 25),
('Elle permet de simplifier les acces aux donnees.', 4.5, 25),
('Elle est utile pour la securite en masquant certaines colonnes.', 4.0, 25),

('Les jointures relient des tables via des cles.', 5.0, 26),
('Il existe des jointures INNER, LEFT, RIGHT et FULL.', 4.5, 26),
('Elles permettent de combiner des donnees de plusieurs tables.', 4.0, 26),

('Un index ameliore la vitesse des requetes.', 5.0, 27),
('Il est cree sur une ou plusieurs colonnes.', 4.5, 27),
('Trop d''index peuvent ralentir les insertions.', 4.0, 27),

-- Reponses pour Gestion
('evaluer les resultats par rapport aux objectifs.', 5.0, 28),
('Utiliser des indicateurs de performance cles (KPI).', 4.5, 28),
('Analyser les feedbacks des collaborateurs.', 4.0, 28),

('Elle libere du temps pour le gestionnaire.', 5.0, 29),
('Elle motive les collaborateurs a prendre des responsabilites.', 4.5, 29),
('Elle ameliore l''efficacite globale.', 4.0, 29),

('Elle implique les collaborateurs dans les decisions.', 5.0, 30),
('Elle ameliore la satisfaction des employes.', 4.5, 30),
('Elle favorise un environnement de travail collaboratif.', 4.0, 30);
