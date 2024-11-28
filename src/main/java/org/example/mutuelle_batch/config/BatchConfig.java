package org.example.mutuelle_batch.config;

import org.example.mutuelle_batch.reader.DossierJsonItemReader;
import org.example.mutuelle_batch.DTOs.DossierDto;
import org.example.mutuelle_batch.listener.CustomSkipListener;
import org.example.mutuelle_batch.model.Dossier;
import org.example.mutuelle_batch.policy.CustomRetryPolicy;
import org.example.mutuelle_batch.policy.CustomSkipPolicy;
import org.example.mutuelle_batch.processor.CompositeProcessor;
import org.example.mutuelle_batch.writer.DossierDatabaseWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration //Indique que cette classe configure un bean Spring
@EnableBatchProcessing
@EnableTransactionManagement
public class BatchConfig {

    private final DossierDatabaseWriter writer;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    private CustomSkipPolicy customSkipPolicy;

    @Autowired
    private CustomRetryPolicy customRetryPolicy;

    @Autowired
    private CustomSkipListener customSkipListener;

    @Qualifier("batchDataSource")  //refer to the batch data source
    private final DataSource batchDataSource;
    private final CompositeProcessor compositeProcessor;
    public BatchConfig(DossierDatabaseWriter writer,
                       @Qualifier("dataSource") DataSource batchDataSource,
                       PlatformTransactionManager transactionManager,
                       CompositeProcessor compositeProcessor1) {
        this.writer = writer;
        this.batchDataSource = batchDataSource;
        this.transactionManager = transactionManager;
        this.compositeProcessor = compositeProcessor1;
    }

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factoryBean = new JobRepositoryFactoryBean();
        factoryBean.setDataSource(batchDataSource);
        factoryBean.setTransactionManager(transactionManager);
        factoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factoryBean.setTablePrefix("BATCH_");
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public Job dossierProcessingJob(Step processDossiersStep, JobRepository jobRepository) {
        return new JobBuilder("dossierProcessingJob", jobRepository)
                .start(processDossiersStep)
                .build();
    }

    @Bean
    public Step processDossiersStep(ItemReader<DossierDto> reader,
                                    ItemWriter<Dossier> writer) throws Exception {
        return new StepBuilder("processDossiersStep", jobRepository())
                .<DossierDto, Dossier>chunk(5, transactionManager)  // Process 5 dossiers at a time per transaction
                .reader(reader)
                .processor(compositeProcessor)  // Apply the composite processor to each DossierDto
                .writer(writer)  // Write the Dossiers to the database
                .faultTolerant() // Activer la tolérance aux erreurs
                .skipPolicy(customSkipPolicy)
                .retryPolicy(customRetryPolicy)
                .listener(customSkipListener) // suivre les erreurs ignorées
                .build();
    }

    // Define ItemReader to read multiple dossiers from JSON
    @Bean
    public ItemReader<DossierDto> jsonItemReader() throws Exception {
        List<DossierDto> dossierList = new DossierJsonItemReader().readDossier();
        return new ItemReader<>() {
            private final Iterator<DossierDto> iterator = dossierList.iterator();
            @Override
            public DossierDto read() {
                return iterator.hasNext() ? iterator.next() : null;
            }
        };
    }
}
