package org.example.mutuelle_batch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDossier;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "assure_id")
    private Assure assure;

    private String nomBeneficiaire;
    private String lienParente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateDepotDossier;

    private Double montantTotalPaye;
    private Double montantTotalRembourse;
}
