package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ConsultationProcessor implements ItemProcessor<DossierDto, DossierDto> {
    //Calcule le remboursement de la consultation en appliquant un pourcentage fixe sur le prix de la consultation
    private static final double TAUX_REMBOURSEMENT_CONSULTATION = 0.7; // 70%

    @Override
    public DossierDto process(DossierDto dossierDto) throws Exception {
        double remboursementConsultation = dossierDto.getPrixConsultation() * TAUX_REMBOURSEMENT_CONSULTATION;
        dossierDto.setPrixConsultation(remboursementConsultation);

        return dossierDto;
    }
}
