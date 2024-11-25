package org.example.mutuelle_batch.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Assure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAssure;
    private String nomAssure;
    private String numeroAffiliation;
    private String immatriculation;

    @OneToMany(mappedBy = "assure")
    private List<Beneficiaire> beneficiaires;
}
