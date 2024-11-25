package org.example.mutuelle_batch.DTOs;

import lombok.Data;
import java.util.Date;
import java.util.List;
@Data
public class DossierDto {
    private String nomAssure;
    private String numeroAffiliation;
    private String immatriculation;
    private String lienParente;
    private double montantTotalFrais;
    private double prixConsultation;
    private int nombrePiecesJointes;
    private String nomBeneficiaire;
    private Date dateDepotDossier;
    private List<TraitementDto> traitements;
}
