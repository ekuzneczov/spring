package org.example.spring.specifications;

import org.example.spring.database.entity.User;
import org.example.spring.database.entity.User_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class UserSpecs {

    public static Specification<User> birthDateLessThan(LocalDate date) {
        return (root, query, builder) -> builder.lessThan(root.get(User_.birthDate), date);
    }

    public static Specification<User> containingFirstname(String firstname) {
        return (root, query, builder) -> builder.like(root.get(User_.firstname), "%" + firstname + "%");
    }

    public static Specification<User> containingLastname(String lastname) {
        return (root, query, builder) -> builder.like(root.get(User_.lastname), "%" + lastname + "%");
    }
}
