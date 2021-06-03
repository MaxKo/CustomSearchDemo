package ua.kerberos.search.specification.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.kerberos.search.specification.dto.search.UserSearchSpecificationRequest;
import ua.kerberos.search.specification.entity.User;
import ua.kerberos.search.specification.exception.NotFoundException;
import ua.kerberos.search.specification.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Min;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
	private final UserRepository userRepository;
	private final ApplicationContext ac;

	@GetMapping("/{id}")
//	@Operation(
//			parameters = {
//					@Parameter(
//							name = "cookie",
//							in = ParameterIn.COOKIE,
//							schema = @Schema(implementation = String.class)
//					)
//			}
//	)
	public User find(@PathVariable @Min(1) Long id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@GetMapping
	public Page<User> findAll(UserSearchSpecificationRequest searchSpecification, Pageable pageable) {
		return userRepository.findAll(searchSpecification, pageable);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable @Min(1) Long id) {
		userRepository.deleteById(id);
	}


//    @Value("${springdoc.swagger-ui.enabled}")
//    private String swaggerUi;
//
//    @Value("${springdoc.api-docs.enabled}")
//    private String apiDocs;

//	@PostConstruct
//	public void post() throws IOException {
//		FileOutputStream fos = new FileOutputStream(new File("d:/CustomSearchDemo.txt"));
////        fos.write(("\t\nswaggerUi:" + swaggerUi).getBytes(Charset.defaultCharset()));
////        fos.write(("\t\napiDocs:" + apiDocs).getBytes(Charset.defaultCharset()));
//
//		writeBean(ac, fos, "openApiContext");
//
//
//		Arrays.stream(ac.getBeanDefinitionNames())
//				.filter(s -> s.contains("swagg") || s.contains("open"))
//				.forEach(n -> writeBean(ac, fos, n));
//
//
//		fos.close();
//	}

	@SneakyThrows
	private void writeBean(final ApplicationContext ac1, final FileOutputStream fos, final String bean) {
		fos.write(("\t\nbean " + bean + ": " + ac1.containsBean(bean)).getBytes(Charset.defaultCharset()));
	}

}
