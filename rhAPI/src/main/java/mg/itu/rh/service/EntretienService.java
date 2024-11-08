package mg.itu.rh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.itu.rh.entity.Entretien;
import mg.itu.rh.entity.EtatEntretien;
import mg.itu.rh.repository.EntretienRepository;
import mg.itu.rh.repository.EtatEntretienRepository;

@Service
public class EntretienService {
    @Autowired
    private EntretienRepository entretienRepository;

    @Autowired
    private EtatEntretienRepository etatEntretienRepository;

    public Entretien save(Entretien entretien){
        return entretienRepository.save(entretien);
    }

    public Entretien valider(Long idEntretien){
        Entretien entretien=entretienRepository.findById(idEntretien).orElseThrow(()->new RuntimeException("Entretien non retrouve"));
        EtatEntretien etatEntretien=etatEntretienRepository.findById(entretien.getEtatEntretien().getIdEtatEntretien()+1).orElseThrow(()->new RuntimeException("On ne peut plus le valider"));
        entretien.setEtatEntretien(etatEntretien);
        return this.save(entretien);
    }

    public List<Entretien> findAll(){
        return entretienRepository.findAll();
    }

    public List<Entretien> findByEtat(Long idEtat){
        EtatEntretien etatEntretien=etatEntretienRepository.findById(idEtat).orElseThrow(()->new RuntimeException("Etat non trouve"));
        return entretienRepository.findByEtatEntretien(etatEntretien);
    }
}
