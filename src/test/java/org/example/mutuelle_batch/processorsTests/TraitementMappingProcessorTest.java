package org.example.mutuelle_batch.processorsTests;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.processor.TraitementMappingProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class TraitementMappingProcessorTest {
    @Autowired
    private TraitementMappingProcessor traitementMappingProcessor;

    @Test
    public void testTraitementMappingProcessor() throws Exception {
        DossierDto dossierDto = new DossierDto();
        TraitementDto traitement = new TraitementDto();
        traitement.setCodeBarre(6118001080304L);
        traitement.setNomMedicament("CLAFORAN");
        dossierDto.setTraitements(List.of(traitement));

        DossierDto result = traitementMappingProcessor.process(dossierDto);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getTraitements().get(0).isExiste());
        Assertions.assertEquals(69.0, result.getTraitements().get(0).getPrixMedicament());
    }
}
