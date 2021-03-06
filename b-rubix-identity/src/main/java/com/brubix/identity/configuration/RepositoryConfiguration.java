package com.brubix.identity.configuration;


import com.brubix.common.repository.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = "com.brubix.entity")
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class RepositoryConfiguration {
}
