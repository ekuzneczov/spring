package org.example.spring.mapper;

import org.example.spring.database.entity.Company;
import org.example.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company company) {
        return new CompanyReadDto(
                company.getId(),
                company.getName()
        );
    }
}
