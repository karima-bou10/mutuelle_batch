package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TraitementRemboursementProcessor implements ItemProcessor<DossierDto, DossierDto> {
    // Calcule le montant remboursable pour chaque médicament référencé en appliquant le pourcentage de remboursement spécifié

    @Override
    public DossierDto process(DossierDto dossierDto) throws Exception {
        for (TraitementDto traitement : dossierDto.getTraitements()) {
            if(traitement.getTauxRemboursement()>0){
            // Calculer le remboursement pour chaque médicament référentiel
            double remboursement = traitement.getPrixMedicament() * traitement.getTauxRemboursement();
            traitement.setPrixMedicament(remboursement);
            }
        }
        return dossierDto;
    }
}
