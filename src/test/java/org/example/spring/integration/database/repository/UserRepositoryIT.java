package org.example.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.*;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.PersonalInfo2;
import org.example.spring.dto.UserFilter;
import org.example.spring.integration.IntegrationTestBase;
import org.example.spring.integration.annotation.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserRepositoryIT extends IntegrationTestBase {

    private final UserRepository userRepository;

    private final ApplicationContext context;

    @Test
    void checkBatch() {
        List<User> users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        List<PersonalInfo> users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        System.out.println();
    }

    @Test
    void checkAuditing() {
        User ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        UserFilter filter = new UserFilter(
                null, "%ov%", LocalDate.now()
        );
        List<User> users = userRepository.findAllByFilter(filter);
        System.out.println();
    }

    @Test
    void checkProjections() {

//        List<PersonalInfo> users = userRepository.findAllByCompanyId(1, PersonalInfo.class);

        List<PersonalInfo2> users = userRepository.findAllByCompanyId(1);

        assertEquals(2, users.size());
    }

    @Test
    void checkPageable() {
        PageRequest pageable = PageRequest.of(0, 2, Sort.by("id"));
        Page<User> pages = userRepository.findAllBy(pageable);
        pages.forEach(user -> System.out.println(user.getCompany().getName()));

        while (pages.hasNext()) {
            pages = userRepository.findAllBy(pages.nextPageable());
            pages.forEach(user -> System.out.println(user.getCompany().getName()));
        }
    }

    @Test
    void checkFirstTop() {

        Sort.TypedSort<User> sortBy = Sort.sort(User.class);

        Sort sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        System.out.println();

//        List<User> allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
//
//        assertEquals(3,allUsers.size());
//
//        Optional<User> topUser = userRepository.findTopByOrderByIdDesc();
//
//        assertTrue(topUser.isPresent());
//
//        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void findAllBy() {
        List<User> users = userRepository.findAllBy("%a%", "%ov%");

        User user = users.get(0);

        List<UserChat> userChats = user.getUserChats();
        userChats.size();
        assertEquals(3, users.size());
    }

    @Test
    void updateRole() {

        User ivan = userRepository.getById(1L);

        assertSame(Role.ADMIN, ivan.getRole());


        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        User theSameIvan = userRepository.getById(1L);

        assertSame(Role.USER, theSameIvan.getRole());
    }
}