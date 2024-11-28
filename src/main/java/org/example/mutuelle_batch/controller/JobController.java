package org.example.mutuelle_batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dossierProcessingJob;

    //POST /api/jobs/dossier-processing
    @PostMapping("/dossier-processing")
    public ResponseEntity<String> runDossierProcessingJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // timestamp permet d'éviter les duplications
                    .toJobParameters();

            // Lancer le job
            JobExecution jobExecution = jobLauncher.run(dossierProcessingJob, jobParameters);

            // Retourner l'état du job
            return ResponseEntity.ok("Job lancé avec succès avec l'ID : " + jobExecution.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erreur lors de l'exécution du job : " + e.getMessage());
        }
    }
}
