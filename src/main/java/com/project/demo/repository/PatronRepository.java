package com.project.demo.repository;

import com.project.demo.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    Optional<Patron> findByUsername(String username);
}