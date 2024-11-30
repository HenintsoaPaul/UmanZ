package mg.itu.rh.service;

import mg.itu.rh.entity.Employe;
import mg.itu.rh.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeService {

    private final EmployeRepository employeeRepository;

    @Autowired
    public EmployeService(EmployeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employe> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employe> getEmployeesByCategory(Long idCategories) {
        return employeeRepository.findByCategory_IdCategories(idCategories);
    }
}

