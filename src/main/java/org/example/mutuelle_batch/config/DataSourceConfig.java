package org.example.mutuelle_batch.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    // Primary Data Source (Business Data)
    @Bean(name = "appDataSource")
    @Primary
    public DataSource appDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/assurance")
                .username("your_username")
                .password("your_password")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    // Batch Data Source (Job Repository)
    @Bean(name = "dataSource")
    public DataSource batchDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/batch_metadata_db")
                .username("your_username")
                .password("your_password")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
