package org.example.mutuelle_batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BatchTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dossierProcessingJob;

    @Test
    public void testJobExecution() throws Exception {
        JobExecution execution = jobLauncher.run(dossierProcessingJob, new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters());

        System.out.println("Job Status: " + execution.getStatus());
        assertEquals(BatchStatus.COMPLETED, execution.getStatus(), "The job did not complete successfully.");

    }
}
