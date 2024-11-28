package org.example.mutuelle_batch.reader;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class DossierJsonItemReader {
    //JsonItemReader : Un composant Spring Batch pour lire des données au format JSON
    //Utilisé ici pour mapper les données JSON directement à des objets DossierDto
    public List<DossierDto> readDossier() throws Exception {
        JsonItemReader<DossierDto> reader = new JsonItemReaderBuilder<DossierDto>()
                .name("dossierJsonItemReader")
                .jsonObjectReader(new JacksonJsonObjectReader<>(DossierDto.class))
                .resource(new ClassPathResource("data/dossiers.json")) // Chemin vers le fichier JSON
                .build();
        //JacksonJsonObjectReader : Utilise la bibliothèque Jackson pour convertir les objets JSON en instances de DossierDto

        reader.open(new ExecutionContext());  // Initialiser le reader
        List<DossierDto> dossierList = new ArrayList<>();
        DossierDto dossier;
        while ((dossier = reader.read()) != null) {
            dossierList.add(dossier);
        }
        reader.close();
        return dossierList;
    }
}
