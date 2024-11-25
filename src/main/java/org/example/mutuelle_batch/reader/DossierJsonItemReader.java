package org.example.mutuelle_batch.reader;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration  //Indique que cette classe configure un bean Spring
public class DossierJsonItemReader {

    @Bean
    public JsonItemReader<DossierDto> jsonItemReader() {
        //JsonItemReader :
        //Un composant prêt à l'emploi dans Spring Batch pour lire des données au format JSON
        //Utilisé ici pour mapper les données JSON directement à des objets Dossier
        return new JsonItemReaderBuilder<DossierDto>()
                .name("dossierJsonItemReader")
                .jsonObjectReader(new JacksonJsonObjectReader<>(DossierDto.class))
                .resource(new ClassPathResource("data/dossier.json")) // Chemin vers le fichier JSON
                .build();
        //JacksonJsonObjectReader :
        //Utilise la bibliothèque Jackson pour convertir les objets JSON en instances de Dossier
    }
}
