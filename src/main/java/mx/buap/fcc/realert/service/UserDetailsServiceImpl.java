package mx.buap.fcc.realert.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.Persona;
import mx.buap.fcc.realert.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final PersonaRepository personaRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
	{
		Persona persona = personaRepository
				.findByCorreo(s)
				.orElseThrow(() -> new UsernameNotFoundException(s));

		log.trace("Usuario encontrado: {}", persona);

		return new User(persona.getCorreo(), persona.getPassword(), true,
				true, true, true,
				Collections.singleton(new SimpleGrantedAuthority(persona.getRol().getAuthority())));
	}
}
