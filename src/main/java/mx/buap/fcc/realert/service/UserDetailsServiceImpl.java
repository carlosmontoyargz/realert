package mx.buap.fcc.realert.service;

import lombok.RequiredArgsConstructor;
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

/**
 * Implementacion de servicio para obtener al usuario desde la base de datos.
 *
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
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
		return User
				.withUsername(persona.getCorreo())
				.password(persona.getPassword())
				.authorities(new SimpleGrantedAuthority(persona.getRol().toString()))
				.build();
	}
}
