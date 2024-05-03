package org.example.spring.database.repository;

import jakarta.persistence.LockModeType;
import org.example.spring.database.entity.Role;
import org.example.spring.database.entity.User;
import org.example.spring.dto.PersonalInfo2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.history.RevisionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository,
        RevisionRepository<User, Long, Integer>, JpaSpecificationExecutor<User> {

    @Query("select u from User u " +
            "join fetch u.userChats " +
            "where u.firstname like %?1% and u.lastname like %?2%")
    List<User> findAllBy(String firstname, String lastname);

//    @Query(value = "SELECT * FROM users " +
//            "WHERE firstname LIKE :firstname AND lastname LIKE :lastname",
//    nativeQuery = true)
//    List<User> findAllBy(String firstname, String lastname);

    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    @Modifying(clearAutomatically = true)
    int updateRole(Role role, Long... ids);

    Optional<User> findTopByOrderByIdDesc();

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findTop3ByBirthDateBefore(LocalDate date, Sort sort);

    @EntityGraph(attributePaths = {"company", "company.locales"})
//    @Query("select u from User u join fetch u.company")
    Page<User> findAllBy(Pageable pageable);

    <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query(value = "SELECT firstname," +
            "lastname," +
            "birth_date as birthDate " +
            "FROM users " +
            "WHERE company_id = :companyId",
            nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

}
