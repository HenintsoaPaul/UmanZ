-- Désactiver les contraintes de clé étrangère
ALTER TABLE talent_competence DROP CONSTRAINT talent_competence_id_competence_fkey;
ALTER TABLE talent_competence DROP CONSTRAINT talent_competence_id_talent_fkey;
ALTER TABLE competence_annonce DROP CONSTRAINT competence_annonce_id_competence_fkey;
ALTER TABLE competence_annonce DROP CONSTRAINT competence_annonce_id_annonce_fkey;
ALTER TABLE experience_talent DROP CONSTRAINT experience_talent_id_talent_fkey;
ALTER TABLE experience_talent DROP CONSTRAINT experience_talent_id_poste_fkey;
ALTER TABLE experience_poste DROP CONSTRAINT experience_poste_id_poste_fkey;
ALTER TABLE experience_poste DROP CONSTRAINT experience_poste_id_annonce_fkey;
ALTER TABLE competence_cible DROP CONSTRAINT competence_cible_id_competence_fkey;
ALTER TABLE competence_cible DROP CONSTRAINT competence_cible_id_formation_fkey;
ALTER TABLE participation DROP CONSTRAINT participation_id_contrat_fkey;
ALTER TABLE participation DROP CONSTRAINT participation_id_formation_fkey;
ALTER TABLE entretien DROP CONSTRAINT entretien_id_enfant_fkey;
ALTER TABLE entretien DROP CONSTRAINT entretien_id_annonce_fkey;
ALTER TABLE entretien DROP CONSTRAINT entretien_id_etat_entretien_fkey;
ALTER TABLE entretien DROP CONSTRAINT entretien_id_talent_fkey;
ALTER TABLE annonce DROP CONSTRAINT annonce_id_poste_fkey;
ALTER TABLE contrat DROP CONSTRAINT contrat_id_poste_fkey;
ALTER TABLE contrat DROP CONSTRAINT contrat_id_talent_fkey;
ALTER TABLE contrat DROP CONSTRAINT contrat_id_type_contrat_fkey;
ALTER TABLE rupture DROP CONSTRAINT rupture_id_type_rupture_fkey;
ALTER TABLE rupture DROP CONSTRAINT rupture_id_contrat_fkey;
ALTER TABLE conge DROP CONSTRAINT conge_id_contrat_fkey;
ALTER TABLE absence DROP CONSTRAINT absence_id_contrat_fkey;

-- Supprimer toutes les données des tables
TRUNCATE TABLE talent_competence, competence_annonce, experience_talent, experience_poste, competence_cible, participation, entretien, annonce, contrat, rupture, conge, absence, talent, competence, poste, etat_entretien, type_contrat, formation, type_rupture RESTART IDENTITY;

-- Réactiver les contraintes de clé étrangère
ALTER TABLE talent_competence ADD CONSTRAINT talent_competence_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence(id_competence);
ALTER TABLE talent_competence ADD CONSTRAINT talent_competence_id_talent_fkey FOREIGN KEY (id_talent) REFERENCES talent(id_talent);
ALTER TABLE competence_annonce ADD CONSTRAINT competence_annonce_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence(id_competence);
ALTER TABLE competence_annonce ADD CONSTRAINT competence_annonce_id_annonce_fkey FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce);
ALTER TABLE experience_talent ADD CONSTRAINT experience_talent_id_talent_fkey FOREIGN KEY (id_talent) REFERENCES talent(id_talent);
ALTER TABLE experience_talent ADD CONSTRAINT experience_talent_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste(id_poste);
ALTER TABLE experience_poste ADD CONSTRAINT experience_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste(id_poste);
ALTER TABLE experience_poste ADD CONSTRAINT experience_poste_id_annonce_fkey FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce);
ALTER TABLE competence_cible ADD CONSTRAINT competence_cible_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence(id_competence);
ALTER TABLE competence_cible ADD CONSTRAINT competence_cible_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES formation(id_formation);
ALTER TABLE participation ADD CONSTRAINT participation_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES contrat(id_contrat);
ALTER TABLE participation ADD CONSTRAINT participation_id_formation_fkey FOREIGN KEY (id_formation) REFERENCES formation(id_formation);
ALTER TABLE entretien ADD CONSTRAINT entretien_id_enfant_fkey FOREIGN KEY (id_enfant) REFERENCES entretien(id_entretien);
ALTER TABLE entretien ADD CONSTRAINT entretien_id_annonce_fkey FOREIGN KEY (id_annonce) REFERENCES annonce(id_annonce);
ALTER TABLE entretien ADD CONSTRAINT entretien_id_etat_entretien_fkey FOREIGN KEY (id_etat_entretien) REFERENCES etat_entretien(id_etat_entretien);
ALTER TABLE entretien ADD CONSTRAINT entretien_id_talent_fkey FOREIGN KEY (id_talent) REFERENCES talent(id_talent);
ALTER TABLE annonce ADD CONSTRAINT annonce_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste(id_poste);
ALTER TABLE contrat ADD CONSTRAINT contrat_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste(id_poste);
ALTER TABLE contrat ADD CONSTRAINT contrat_id_talent_fkey FOREIGN KEY (id_talent) REFERENCES talent(id_talent);
ALTER TABLE contrat ADD CONSTRAINT contrat_id_type_contrat_fkey FOREIGN KEY (id_type_contrat) REFERENCES type_contrat(id_type_contrat);
ALTER TABLE rupture ADD CONSTRAINT rupture_id_type_rupture_fkey FOREIGN KEY (id_type_rupture) REFERENCES type_rupture(id_type_rupture);
ALTER TABLE rupture ADD CONSTRAINT rupture_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES contrat(id_contrat);
ALTER TABLE conge ADD CONSTRAINT conge_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES contrat(id_contrat);
ALTER TABLE absence ADD CONSTRAINT absence_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES contrat(id_contrat);
