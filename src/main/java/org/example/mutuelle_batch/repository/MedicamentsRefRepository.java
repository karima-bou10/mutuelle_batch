package org.example.mutuelle_batch.repository;

import org.example.mutuelle_batch.model.MedicamentsRef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentsRefRepository extends JpaRepository<MedicamentsRef, Long> {
    Optional<MedicamentsRef> findByNomMedicament(String nomMedicament);
    Optional<MedicamentsRef> findByCodeBarre(Long codeBarre);
}
