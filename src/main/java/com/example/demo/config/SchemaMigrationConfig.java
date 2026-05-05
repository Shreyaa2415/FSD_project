package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SchemaMigrationConfig {

    @Bean
    CommandLineRunner migrateClassTable(JdbcTemplate jdbcTemplate) {
        return args -> {
            jdbcTemplate.execute("ALTER TABLE class ADD COLUMN IF NOT EXISTS capacity INTEGER");
            jdbcTemplate.execute("ALTER TABLE class ADD COLUMN IF NOT EXISTS area DOUBLE PRECISION");
            jdbcTemplate.execute("ALTER TABLE class ADD COLUMN IF NOT EXISTS classroom_type VARCHAR(20)");

            jdbcTemplate.execute("UPDATE class SET capacity = 30 WHERE capacity IS NULL");
            jdbcTemplate.execute("UPDATE class SET area = 100.0 WHERE area IS NULL");
            jdbcTemplate.execute("UPDATE class SET classroom_type = 'CLASS' WHERE classroom_type IS NULL");

            jdbcTemplate.execute("ALTER TABLE class ALTER COLUMN capacity SET NOT NULL");
            jdbcTemplate.execute("ALTER TABLE class ALTER COLUMN area SET NOT NULL");
            jdbcTemplate.execute("ALTER TABLE class ALTER COLUMN classroom_type SET NOT NULL");
        };
    }
}

