package org.example.mutuelle_batch.writer;

import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.repository.DossierRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DossierDatabaseWriter implements ItemWriter<Dossier> {
    private final DossierRepository dossierRepository;

    public DossierDatabaseWriter(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }
    @Override
    public void write(Chunk<? extends Dossier> items) throws Exception {
        dossierRepository.saveAll(items.getItems());
    }
}
