package ua.kerberos.search.specification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kerberos.search.specification.dto.Stat;
import ua.kerberos.search.specification.dto.StatSpecification;
import ua.kerberos.search.specification.dto.search.UserSearchSpecificationRequest;
import ua.kerberos.search.specification.entity.User;
import ua.kerberos.search.specification.repository.StatRepo;
import ua.kerberos.search.specification.repository.StatRepository;
import ua.kerberos.search.specification.repository.UserRepository;
import java.util.List;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Validated
@RestController
@RequestMapping("/api/v1/users/stat")
@RequiredArgsConstructor

public class UserStatController {
	private final UserRepository userRepository;
	private final StatRepository statRepository;
	private final StatRepo statRepo;

	@GetMapping
	public List<Stat> findAll(UserSearchSpecificationRequest searchSpecification, Pageable pageable) {

		//return statRepository.findAll(new StatSpecification(searchSpecification));

		return statRepo.getUserStatBySpecification(searchSpecification);

	}

}
