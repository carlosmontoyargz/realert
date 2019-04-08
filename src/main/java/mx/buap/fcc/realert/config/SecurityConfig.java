package mx.buap.fcc.realert.config;

import lombok.RequiredArgsConstructor;
import mx.buap.fcc.realert.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private final UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.authorizeRequests()
						.antMatchers("/css/**", "/fonts/**", "/css/**", "/images/**", "/js/**", "/scripts/**", "/styles/**", "/vendor/**")
								.permitAll()
						.antMatchers("/recetas/lista-recetas-paciente")
								.hasAnyAuthority(Rol.PACIENTE.toString())
						.antMatchers("/add-new-post")
								.hasAnyAuthority(Rol.ADMINISTRADOR.toString())
						.anyRequest()
								.authenticated()
				.and()
				.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll()
				.and()
				.logout()
						.logoutSuccessUrl("/login")

				// Para que la consola h2 funcione
				.and()
				.headers()
						.frameOptions().disable()
				.and()
				.csrf()
						.disable();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
}
