package org.example.mutuelle_batch.processorsTests;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.processor.ConsultationProcessor;
import org.example.mutuelle_batch.processor.TotalRemboursementProcessor;
import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.processor.TraitementMappingProcessor;
import org.example.mutuelle_batch.processor.TraitementRemboursementProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TotalRemboursementProcessorTest {

    @Autowired
    private ConsultationProcessor consultationProcessor;

    @Autowired
    private TraitementMappingProcessor traitementMappingProcessor;

    @Autowired
    private TraitementRemboursementProcessor traitementRemboursementProcessor;

    @Autowired
    private TotalRemboursementProcessor totalRemboursementProcessor;


    @Test
    public void testTotalRemboursementProcessor() throws Exception {
        DossierDto dossierDto = new DossierDto();
        dossierDto.setNumeroAffiliation("AFF123456");
        dossierDto.setPrixConsultation(100.0);

        TraitementDto traitement = new TraitementDto();
        traitement.setCodeBarre(6118001080304L);
        traitement.setNomMedicament("CLAFORAN");

        dossierDto.setTraitements(List.of(traitement));

        DossierDto dossier = consultationProcessor.process(dossierDto);
        dossier = traitementMappingProcessor.process(dossierDto);
        dossier = traitementRemboursementProcessor.process(dossierDto);
        Dossier result = totalRemboursementProcessor.process(dossierDto);


        Assertions.assertNotNull(result);
        Assertions.assertEquals(118.3, result.getMontantTotalRembourse());
    }
}
