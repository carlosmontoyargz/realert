package mx.buap.fcc.realert.controller;

import mx.buap.fcc.realert.domain.Rol;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para la aplicacion web.
 *
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Controller
public class IndexController
{
	@GetMapping({"/", "/index", "/index.html"})
	public String index(//@RequestParam(name="name", required=false, defaultValue="World") String name,
	                    Authentication authentication)
	{
		Rol authority = Rol.valueOf(((UserDetails) authentication
				.getPrincipal())
				.getAuthorities()
				.stream()
				.findFirst()
				.orElseThrow(NullPointerException::new)
				.getAuthority());

		if (Rol.PACIENTE.equals(authority) || Rol.MEDICO.equals(authority))
			return "redirect:recetas";

		return "index";
	}

	@GetMapping("/login")
	public String login()
	{
		return "login";
	}

	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "index";
	}

	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}

	@GetMapping("/crear-expediente")
	public String expediente()
	{
		return "crear-expediente";
	}

	@GetMapping("/crear-receta")
	public String crearReceta()
	{
		return "crear-receta";
	}

	@GetMapping("/modificar-receta")
	public String modificarReceta()
	{
		return "modificar-receta";
	}

	@GetMapping("/ver-pacientes")
	public String verPacientes()
	{
		return "ver-pacientes";
	}

	@GetMapping("/template-base")
	public String templateBase()
	{
		return "template-base.html";
	}
}
