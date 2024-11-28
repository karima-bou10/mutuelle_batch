package org.example.mutuelle_batch.readerTest;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.reader.DossierJsonItemReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.util.List;

@SpringBootTest
public class DossierJsonItemReaderTest {
    @Autowired
    private DossierJsonItemReader dossierJsonItemReader;

    @Test
    public void testJsonReader() throws Exception {
        List<DossierDto> dossierList = dossierJsonItemReader.readDossier();

        Assertions.assertFalse(dossierList.isEmpty(), "La liste des dossiers ne doit pas etre vide");
        Assertions.assertEquals(2, dossierList.size(), "Le nombre de dossiers doit correspond au fichier JSON");

        for(DossierDto dossier : dossierList){
            if (dossier != null) {
                System.out.println(dossier);
            }
        }
    }
    @Test
    public void testJsonFileExists() {
        Resource resource = new ClassPathResource("data/dossiers.json");
        Assertions.assertTrue(resource.exists(), "Le fichier JSON existe dans le chemin spécifié");
    }
}
