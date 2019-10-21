package com.example.springmvc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.securitycustorm.CustomSuccessHandler;
import com.example.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		// No login
		http.authorizeRequests().antMatchers("/resources/**", "/login", "/logout","/user/home").permitAll();
		// Page login ROLE_USER or ROLE_ADMIN.
		// If login fail redirect /login.
		http.authorizeRequests().antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

		// Login Page ADMIN
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");

		// Exception permission redirect page 403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Setup Login Form.
		http.authorizeRequests().and().formLogin()//
				// Submit URL của trang login
				.loginPage("/login").usernameParameter("userName").passwordParameter("password")
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.successHandler(myAuthenticationSuccessHandler()).failureUrl("/login?error=true")//
				// setup Logout Page.
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).and() ;

		// Setup Remember Me.
//		http.authorizeRequests().and() //
//				.rememberMe().tokenRepository(this.persistentTokenRepository()) //
//				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

	}

//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}
}
