package org.example.mutuelle_batch;

import org.example.mutuelle_batch.Service.ExcelToDatabaseService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class MutuelleBatchApplication implements CommandLineRunner {
    @Autowired
    private ExcelToDatabaseService excelToDatabaseService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dossierProcessingJob; // The batch job you created

    public static void main(String[] args) {
        SpringApplication.run(MutuelleBatchApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // Chemin local du fichier Excel
        String filePath = "C:\\Users\\hp\\Downloads\\ref-des-medicaments-cnops-2014.xlsx";

        // Appeler le service pour importer les données
        //excelToDatabaseService.importExcelData(filePath);
        //System.out.println("Importation des médicaments terminée !");

        // Trigger the job
        jobLauncher.run(dossierProcessingJob, new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
               .toJobParameters());
        //L'utilisation d'un paramètre comme timestamp garantit que chaque exécution est unique,
        //même si vous exécutez le même job plusieurs fois
    }
}
