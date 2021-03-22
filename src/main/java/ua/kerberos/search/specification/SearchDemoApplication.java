package ua.kerberos.search.specification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Maksym Kovieshnikov on 13/08/2020
 */
@SpringBootApplication
@EnableSwagger2
public class SearchDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchDemoApplication.class, args);
	}

}