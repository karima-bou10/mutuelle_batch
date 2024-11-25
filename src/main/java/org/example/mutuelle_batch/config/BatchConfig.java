package org.example.mutuelle_batch.config;

import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.writer.DossierDatabaseWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job dossierProcessingJob(Step processDossiersStep) {
        return new JobBuilder("dossierProcessingJob", jobRepository)
                .start(processDossiersStep)
                .build();
    }

    @Bean
    public Step processDossiersStep(JsonItemReader<DossierDto> reader,
                                    DossierDatabaseWriter writer) {
        return new StepBuilder("processDossiersStep", jobRepository)
                .<DossierDto, Dossier>chunk(5, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }
}
