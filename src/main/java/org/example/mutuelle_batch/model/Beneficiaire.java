package org.example.mutuelle_batch.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBeneficiaire;
    private String nomBeneficiaire;
    private String lienParente;


    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "assure_id")
    private Assure assure;

    public Beneficiaire(String nomBeneficiaire, String lienParente, Assure assure) {
        this.nomBeneficiaire = nomBeneficiaire;
        this.lienParente = lienParente;
        this.assure = assure;
    }
}
