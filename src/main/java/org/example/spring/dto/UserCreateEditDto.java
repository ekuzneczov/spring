package org.example.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.example.spring.database.entity.Role;
import org.example.spring.validation.annotation.UserInfo;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo/*(groups = CreateAction.class)*/
public class UserCreateEditDto {

    @Email
    String username;
    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
}
