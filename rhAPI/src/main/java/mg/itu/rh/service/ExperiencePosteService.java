package mg.itu.rh.service;

import mg.itu.rh.entity.ExperiencePoste;
import mg.itu.rh.repository.ExperiencePosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperiencePosteService {

    @Autowired
    private ExperiencePosteRepository experiencePosteRepository;

    public List<ExperiencePoste> findAll() {
        return experiencePosteRepository.findAll();
    }

    public List<ExperiencePoste> findByIds(List<Long> ids) {
        return experiencePosteRepository.findByIdIn(ids);
    }
}
