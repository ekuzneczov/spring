package org.example.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.dto.CompanyReadDto;
import org.example.spring.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User user) {

        CompanyReadDto company = Optional.ofNullable(user.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getBirthDate(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole(),
                company
        );
    }
}
