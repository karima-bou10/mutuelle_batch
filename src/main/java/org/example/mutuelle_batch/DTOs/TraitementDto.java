package org.example.mutuelle_batch.DTOs;

import lombok.Data;

@Data
public class TraitementDto {
    private String codeBarre;
    private boolean existe;
    private String nomMedicament;
    private String typeMedicament;
    private double prixMedicament;
}
