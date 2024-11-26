package org.example.mutuelle_batch;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.processor.CompositeProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class CompositeProcessorTest {
    @Autowired
    private CompositeProcessor compositeProcessor;

    @Test
    public void testCompositeProcessor() throws Exception {
        DossierDto dossierDto = new DossierDto();
        dossierDto.setNomAssure("Ibrahimi");
        dossierDto.setNumeroAffiliation("AFF123456");
        dossierDto.setNomBeneficiaire("Omar");
        dossierDto.setDateDepotDossier(new Date());
        dossierDto.setPrixConsultation(100.0);
        dossierDto.setMontantTotalFrais(200.0);

        TraitementDto traitement1 = new TraitementDto();
        traitement1.setCodeBarre(6118001080304L);
        traitement1.setNomMedicament("CLAFORAN");

        TraitementDto traitement2 = new TraitementDto();
        traitement2.setCodeBarre(6118000080497L);
        traitement2.setNomMedicament("RHINOFEBRAL");

        dossierDto.setTraitements(List.of(traitement1,traitement2));

        Dossier dossier = compositeProcessor.process(dossierDto);

        Assertions.assertNotNull(dossier);
        Assertions.assertEquals(132.6, dossier.getMontantTotalRembourse());
    }
}
