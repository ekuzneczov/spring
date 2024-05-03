package org.example.spring.integration;

import org.example.spring.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@Sql({
        "classpath:sql/data.sql"
})
@IntegrationTest
public abstract class IntegrationTestBase {

    public static final PostgreSQLContainer<?> CONTAINER =
            new PostgreSQLContainer<>("postgres");
//                    .withUsername("postrges")
//                    .withPassword("postrges");

    @BeforeAll
    static void runContainer() {
        CONTAINER.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    }
}
