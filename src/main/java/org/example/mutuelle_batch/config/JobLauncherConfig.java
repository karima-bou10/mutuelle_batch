package org.example.mutuelle_batch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
public class JobLauncherConfig {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;
}
