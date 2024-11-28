package org.example.mutuelle_batch.repository;

import org.example.mutuelle_batch.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierRepository extends JpaRepository<Dossier, Integer> {
}
