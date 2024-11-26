package org.example.mutuelle_batch.processor;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.DTOs.TraitementDto;
import org.example.mutuelle_batch.model.MedicamentsRef;
import org.example.mutuelle_batch.repository.MedicamentsRefRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TraitementMappingProcessor implements ItemProcessor<DossierDto, DossierDto> {
    //Mappe chaque traitement du dossier au médicament référentiel et vérifie sa disponibilité dans le référentiel

    private final MedicamentsRefRepository medicamentsRefRepository;

    @Autowired
    public TraitementMappingProcessor(MedicamentsRefRepository medicamentsRefRepository) {
        this.medicamentsRefRepository = medicamentsRefRepository;
    }

    @Override
    public DossierDto process(DossierDto dossierDto) throws Exception{
        for (TraitementDto traitement : dossierDto.getTraitements()) {
            Optional<MedicamentsRef> medicamentRef = medicamentsRefRepository.findByCodeBarre(traitement.getCodeBarre());

            if (medicamentRef.isPresent()) {
                MedicamentsRef medicament = medicamentRef.get();
                traitement.setPrixMedicament(medicament.getPrixReference());
                traitement.setTauxRemboursement(medicament.getPourcentageRemboursement());
                traitement.setExiste(true);
            } else {
                traitement.setExiste(false);
                throw new IllegalArgumentException("Le médicament " + traitement.getNomMedicament() + " n'est pas disponible dans le référentiel.");
            }
        }
        return dossierDto;
    }
}
