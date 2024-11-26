package org.example.mutuelle_batch;

import org.example.mutuelle_batch.Service.ExcelToDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutuelleBatchApplication implements CommandLineRunner {
    @Autowired
    private ExcelToDatabaseService excelToDatabaseService;

    public static void main(String[] args) {
        SpringApplication.run(MutuelleBatchApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // Chemin local du fichier Excel
        String filePath = "C:\\Users\\hp\\Downloads\\ref-des-medicaments-cnops-2014.xlsx";

        // Appeler le service pour importer les données
        //excelToDatabaseService.importExcelData(filePath);

        System.out.println("Importation des médicaments terminée !");
    }
}
