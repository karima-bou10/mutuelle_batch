package org.example.mutuelle_batch.writer;

import org.example.mutuelle_batch.model.Dossier;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DossierDatabaseWriter implements ItemWriter<Dossier> {
    @Override
    public void write(Chunk<? extends Dossier> chunk) throws Exception {
        for (Dossier dossier : chunk) {
            System.out.println("Écriture du dossier : " + dossier);
        }
    }
}
