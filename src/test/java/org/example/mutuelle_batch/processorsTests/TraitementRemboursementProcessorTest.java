package org.example.mutuelle_batch.processorsTests;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.processor.TraitementRemboursementProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TraitementRemboursementProcessorTest {
    @Autowired
    private TraitementRemboursementProcessor traitementRemboursementProcessor;

    @Test
    public void testTraitementRemboursementProcessor() throws Exception {
        DossierDto dossierDto = new DossierDto();

        TraitementDto traitement = new TraitementDto();
        traitement.setCodeBarre(6118001080304L);
        traitement.setNomMedicament("CLAFORAN");
        traitement.setPrixMedicament(100);
        traitement.setTauxRemboursement(0.3);

        dossierDto.setTraitements(List.of(traitement));

        DossierDto dossier = traitementRemboursementProcessor.process(dossierDto);

        Assertions.assertEquals(30.0, dossier.getTraitements().get(0).getPrixMedicament());
    }
}
