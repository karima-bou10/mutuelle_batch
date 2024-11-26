package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.model.Assure;
import org.example.mutuelle_batch.model.Beneficiaire;
import org.example.mutuelle_batch.repository.AssureRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidationProcessor implements ItemProcessor<DossierDto, DossierDto> {
    private final AssureRepository assureRepository;

    @Autowired
    public ValidationProcessor(AssureRepository assureRepository) {
        this.assureRepository = assureRepository;
    }

    @Override
    public DossierDto process(DossierDto dossier) throws Exception {
        if (dossier.getNomAssure() == null || dossier.getNomAssure().isEmpty()) {
            throw new IllegalArgumentException("Nom de l'assuré manquant.");
        }

        if (dossier.getNumeroAffiliation() == null || dossier.getNumeroAffiliation().isEmpty()) {
            throw new IllegalArgumentException("Numéro d'affiliation manquant.");
        }

        if (dossier.getPrixConsultation() <= 0 || dossier.getMontantTotalFrais() <= 0) {
            throw new IllegalArgumentException("Les montants doivent être positifs.");
        }

        if (dossier.getTraitements() == null || dossier.getTraitements().isEmpty()) {
            throw new IllegalArgumentException("La liste des traitements est vide.");
        }

        // Vérification que le bénéficiaire appartient à l'assuré
        Assure assure = assureRepository.findByNumeroAffiliation(dossier.getNumeroAffiliation());
        if (assure == null) {
            throw new IllegalArgumentException("Assuré introuvable avec le numéro d'affiliation : " + dossier.getNumeroAffiliation());
        }

        List<Beneficiaire> beneficiaires = assure.getBeneficiaires();
        boolean beneficiaireExiste = beneficiaires.stream()
                .anyMatch(b -> b.getNomBeneficiaire().equals(dossier.getNomBeneficiaire()));

        if (!beneficiaireExiste) {
            throw new IllegalArgumentException("Le bénéficiaire " + dossier.getNomBeneficiaire() + " n'est pas associé à l'assuré " + assure.getNomAssure());
        }

        return dossier; // Les données sont valides
    }
}
