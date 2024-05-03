package org.example.spring.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.config.condition.JpaCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

//import javax.annotation.PostConstruct;

@Configuration
@Conditional(JpaCondition.class)
@Slf4j
public class JpaConfiguration {

    /*@Bean
    @ConfigurationProperties(prefix = "db")
    public DatabaseProperties  databaseProperties() {
        return new DatabaseProperties();
    }*/

    @PostConstruct
    public void init() {
        log.info("JPA configuration is enabled");
    }
}
