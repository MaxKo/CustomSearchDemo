//package ua.kerberos.search.specification.conf;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and()
//				.csrf().disable()
//				.exceptionHandling()
////				.authenticationEntryPoint(authenticationEntryPoint())
//				.and()
//				.authorizeRequests()
//				.antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
//				.anyRequest().permitAll()
//				.and()
////				.addFilter(jwtAuthorizationFilter())
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//		http.headers().frameOptions().disable(); // this is for h2 console
//	}
//
//	private static final String[] SWAGGER_AUTH_WHITELIST = {
//			"/swagger-resources/**",
//			"/swagger-ui.html",
//			"/swagger-ui/**",
//			"/actuator/mappings/",
//			"/v2/api-docs",
//			"/v3/api-docs",
//			"/webjars/**", "/api/v1/images/**", "/actuator", "/actuator/**"
//	};
//}