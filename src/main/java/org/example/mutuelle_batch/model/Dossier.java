package org.example.mutuelle_batch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.Date;
@Data
public class Dossier {
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "assure_id")
    private Assure assure;

    private Beneficiaire beneficiaire;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateDepotDossier;

    private Double montantTotalPaye;
    private Double montantTotalRembourse;
}
