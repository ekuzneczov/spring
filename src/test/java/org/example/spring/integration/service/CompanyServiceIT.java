package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.config.DatabaseProperties;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.integration.annotation.IntegrationTest;
import org.example.spring.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = ApplicationRunner.class,
        initializers = ConfigDataApplicationContextInitializer.class
)*/
@IntegrationTest
@RequiredArgsConstructor
//@TestConstructor(autowireMode = ALL)
public class CompanyServiceIT {

    public static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {

        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID, null);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }
}
