package com.workshop.awscognitoidp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.workshop.awscognitoidp.repositories")
public class PersistenceConfig {
  
}
