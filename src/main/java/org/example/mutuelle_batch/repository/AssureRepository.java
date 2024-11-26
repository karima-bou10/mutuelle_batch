package org.example.mutuelle_batch.repository;

import org.example.mutuelle_batch.model.Assure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssureRepository extends JpaRepository<Assure, Integer> {
    Assure findByNumeroAffiliation(String numeroAffiliation);
}
