package org.example.spring.database.repository;

import org.example.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

//        @Query(name = "Company.findByName")
//    @Query("select c from Company c " +
//            "join fetch c.locales " +
//            "where c.name = ?1")
    Optional<Company> findByName(String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
