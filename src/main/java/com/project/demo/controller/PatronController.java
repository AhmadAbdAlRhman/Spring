package com.project.demo.controller;

import com.project.demo.model.Patron;
import com.project.demo.service.PatronService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PutMapping("/{id}")
    public Patron updatePatron(@PathVariable Long id, @RequestBody Patron newPatron) {
        return patronService.updatePatron(id, newPatron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
    }
}
