package org.example.mutuelle_batch.processorsTests;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.processor.ValidationProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class ValidationProcessorTest {
    @Autowired
    private ValidationProcessor validationProcessor;

    @Test
    public void testValidationProcessor() throws Exception {
        DossierDto dossierDto = new DossierDto();
        dossierDto.setNomAssure("Ibrahimi");
        dossierDto.setNumeroAffiliation("AFF123456");
        dossierDto.setNomBeneficiaire("Omar");
        dossierDto.setPrixConsultation(100.0);
        dossierDto.setMontantTotalFrais(200.0);

        TraitementDto traitement = new TraitementDto();
        traitement.setCodeBarre(1234567890L);
        traitement.setNomMedicament("Paracétamol");
        traitement.setPrixMedicament(50.0);

        dossierDto.setTraitements(List.of(traitement));

        DossierDto result = validationProcessor.process(dossierDto);

        Assertions.assertNotNull(result);
    }
}
