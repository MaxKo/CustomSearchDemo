package ua.kerberos.search.specification.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import ua.kerberos.search.specification.dto.search.UserSearchSpecificationRequest;
import ua.kerberos.search.specification.entity.User;
import ua.kerberos.search.specification.exception.NotFoundException;
import ua.kerberos.search.specification.repository.UserRepository;

import javax.validation.constraints.Min;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;


    @ApiOperation(value = "Find user by ID")
    @GetMapping("/{id}")
    public User find(@PathVariable @Min(1) Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @ApiOperation(value = "Search for users")
    @GetMapping
    public Page<User> findAll(UserSearchSpecificationRequest searchSpecification, @ApiIgnore Pageable pageable) {
        return userRepository.findAll(searchSpecification, pageable);
    }

    @ApiOperation(value = "Delete user by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Min(1) Long id) {
        userRepository.deleteById(id);
    }
}
