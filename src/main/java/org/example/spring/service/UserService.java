package org.example.spring.service;

import lombok.RequiredArgsConstructor;
import org.example.spring.database.entity.User;
import org.example.spring.database.repository.UserRepository;
import org.example.spring.dto.UserCreateEditDto;
import org.example.spring.dto.UserFilter;
import org.example.spring.dto.UserReadDto;
import org.example.spring.mapper.UserCreateEditMapper;
import org.example.spring.mapper.UserReadMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.example.spring.specifications.UserSpecs.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {

        Specification<User> specification = birthDateLessThan(filter.birthDate())
                .and(containingFirstname(filter.firstname()))
                .and(containingLastname(filter.lastname()));

        return userRepository.findAll(specification, pageable)
                .map(userReadMapper::map);

//        return userRepository.findAllByFilter(filter).stream()
//                .map(userReadMapper::map)
//                .toList();
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
