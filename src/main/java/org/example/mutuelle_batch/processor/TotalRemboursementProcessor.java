package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.model.Assure;
import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.repository.AssureRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TotalRemboursementProcessor implements ItemProcessor<DossierDto, Dossier> {
    // Additionne les remboursements de la consultation et des traitements pour calculer le total du remboursement

    private final AssureRepository assureRepository;

    @Autowired
    public TotalRemboursementProcessor(AssureRepository assureRepository) {
        this.assureRepository = assureRepository;
    }

    @Override
    public Dossier process(DossierDto dossierDto) {
        Dossier dossier = new Dossier();
        double totalTraitementsRemboursement = dossierDto.getTraitements().stream()
                .mapToDouble(t -> t.getPrixMedicament())
                .sum();
        dossier.setMontantTotalPaye(dossierDto.getMontantTotalFrais());
        dossier.setMontantTotalRembourse(totalTraitementsRemboursement + dossierDto.getPrixConsultation());

        Assure assure = assureRepository.findByNumeroAffiliation(dossierDto.getNumeroAffiliation());
        dossier.setAssure(assure);
        dossier.setDateDepotDossier(dossierDto.getDateDepotDossier());
        dossier.setNomBeneficiaire(dossierDto.getNomBeneficiaire());
        dossier.setLienParente(dossierDto.getLienParente());

        return dossier;
    }
}
