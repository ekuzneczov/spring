package org.example.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.Role;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserReadDto;
import org.example.spring.integration.IntegrationTestBase;
import org.example.spring.service.UserService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Integer COMPANY_1 = 1;
    public static final Long USER_1 = 1L;
    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();

        assertEquals(5, result.size());
    }

    @Test
    void findById() {
        Optional<UserReadDto> mayBeUser = userService.findById(USER_1);

        assertTrue(mayBeUser.isPresent());
        mayBeUser.ifPresent(user -> {
            assertEquals("ivan@gmail.com", user.getUsername());
        });
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        UserReadDto actualResult = userService.create(userDto);

        assertEquals(userDto.getUsername(), actualResult.getUsername());
        assertEquals(userDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userDto.getLastname(), actualResult.getLastname());
        assertEquals(userDto.getCompanyId(), actualResult.getCompany().id());
        assertSame(userDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        Optional<UserReadDto> actualResult = userService.update(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(213912482L));
    }
}
