package org.example.mutuelle_batch;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootTest
public class DossierJsonItemReaderTest {
    @Autowired
    private JsonItemReader<DossierDto> reader;

    @Test
    public void testJsonReader() throws Exception {
        // Initialisez le contexte d'exécution
        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext); // Ouvre le reader avec le contexte

        DossierDto dossier;
        do {
            dossier = reader.read();
            if (dossier != null) {
                System.out.println(dossier); // Affiche chaque dossier lu
            }
        } while (dossier != null);

        reader.close(); // Fermez le reader après utilisation
    }
    @Test
    public void testJsonFileExists() {
        Resource resource = new ClassPathResource("data/dossier.json");
        Assertions.assertTrue(resource.exists(), "Le fichier JSON n'existe pas dans le chemin spécifié.");
    }
}
