package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.model.Dossier;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CompositeProcessor implements ItemProcessor<DossierDto, Dossier> {
    //Enchaîne tous les processeurs
    private final ValidationProcessor validationProcessor;
    private final ConsultationProcessor consultationProcessor;
    private final TraitementMappingProcessor traitementMappingProcessor;
    private final TraitementRemboursementProcessor traitementRemboursementProcessor;
    private final TotalRemboursementProcessor totalRemboursementProcessor;

    public CompositeProcessor(ValidationProcessor validationProcessor,
                              ConsultationProcessor consultationProcessor,
                              TraitementMappingProcessor traitementMappingProcessor,
                              TraitementRemboursementProcessor traitementRemboursementProcessor,
                              TotalRemboursementProcessor totalRemboursementProcessor) {
        this.validationProcessor = validationProcessor;
        this.consultationProcessor = consultationProcessor;
        this.traitementMappingProcessor = traitementMappingProcessor;
        this.traitementRemboursementProcessor = traitementRemboursementProcessor;
        this.totalRemboursementProcessor = totalRemboursementProcessor;
    }

    @Override
    public Dossier process(DossierDto dossierDto) throws Exception {
        return new CompositeItemProcessorBuilder<DossierDto, Dossier>()
                .delegates(Arrays.asList(
                        validationProcessor,
                        consultationProcessor,
                        traitementMappingProcessor,
                        traitementRemboursementProcessor,
                        totalRemboursementProcessor
                ))
                .build()
                .process(dossierDto);
    }
}
