select * from annonce where date_rupture is null and date_expiration>NOW();

SELECT c.*
FROM contrat c
         JOIN talent t ON c.id_talent = t.id_talent
WHERE c.id_contrat NOT IN (
    SELECT r.id_contrat
    FROM rupture r
)
  AND t.id_talent = 5
  AND c.date_fin < CURRENT_DATE;

SELECT * FROM entretien e
        WHERE e.id_etat_entretien = 2
        AND e.id_annonce = 4;

SELECT * FROM chat
WHERE LOWER('Comment se déroule le processus de recrutement')
          LIKE '%' || LOWER(mot_cle) || '%'
LIMIT 1;

SELECT c FROM Contrat c join c.talent t 
WHERE c.idContrat NOT IN (SELECT r.contrat.idContrat FROM 
Rupture r join r.contrat) and t.idTalent=:idTalent and c.dateFin > CURRENT_DATE