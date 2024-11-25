package org.example.mutuelle_batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentsRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedicamentsRef;
    private String nomMedicament;
    private Double prixReference;
    private int  pourcentageRemboursement;
}
