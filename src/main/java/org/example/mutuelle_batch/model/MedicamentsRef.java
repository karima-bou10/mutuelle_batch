package org.example.mutuelle_batch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentsRef {
    @Id
    private long codeBarre;
    private String nomMedicament;
    private double prixReference;
    private double pourcentageRemboursement;
}
