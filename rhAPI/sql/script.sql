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