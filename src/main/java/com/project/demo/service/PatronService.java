package com.project.demo.service;

import com.project.demo.model.Patron;
import com.project.demo.repository.PatronRepository;

import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PatronService {
    private final PatronRepository patronRepository;
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow();
    }
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }
    public Patron updatePatron(Long id, Patron newPatron) {
        Patron patron = getPatronById(id);
        patron.setUsername(newPatron.getUsername());
        patron.setPhoneNumber(newPatron.getPhoneNumber());
        patron.setAddress(newPatron.getAddress());
        return patronRepository.save(patron);
    }
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}

