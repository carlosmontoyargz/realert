package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import mx.buap.fcc.realert.domain.Administrador;
import mx.buap.fcc.realert.domain.Medico;
import mx.buap.fcc.realert.domain.Paciente;
import mx.buap.fcc.realert.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Carlos Montoya
 * @since 23/04/2019
 */
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProfileController
{
	private final PersonaRepository personaRepository;

	@GetMapping("")
	public String profile(Model model, Principal principal)
	{
		AtomicReference<String> view = new AtomicReference<>("");
		personaRepository
				.findByCorreo(principal.getName())
				.ifPresent(p ->
				{
					if (p instanceof Medico)
					{
						model.addAttribute("medico", p);
						view.set("ver-medico");
					}
					else if (p instanceof Paciente)
					{
						model.addAttribute("paciente", p);
						view.set("ver-expediente");
					}
					else if (p instanceof Administrador)
					{
						model.addAttribute("administrador", p);
						view.set("ver-administrador");
					}
				});
		return view.get();
	}
}
