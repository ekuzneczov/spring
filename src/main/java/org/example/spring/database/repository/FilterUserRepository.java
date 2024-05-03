package org.example.spring.database.repository;

import org.example.spring.database.entity.Role;
import org.example.spring.database.entity.User;
import org.example.spring.dto.PersonalInfo;
import org.example.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

}
