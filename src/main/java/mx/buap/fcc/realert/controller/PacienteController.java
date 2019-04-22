package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.Paciente;
import mx.buap.fcc.realert.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author Carlos Montoya
 * @since 21/04/2019
 */
@Controller
@RequestMapping("/pacientes")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class PacienteController
{
	private final PacienteRepository pacienteRepository;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("")
	private String listarPacientes(Model model)
	{
		model.addAttribute("pacientes", pacienteRepository.findAll());
		return "lista-pacientes";
	}

	@GetMapping("/ver-expediente")
	public String verPaciente(@RequestParam("id") int id, Model model)
	{
		model.addAttribute("paciente",
				pacienteRepository
						.findById(id)
						.orElse(null));
		return "ver-expediente";
	}

	@GetMapping("/crear-paciente")
	public String crearPaciente(Model model)
	{
		model.addAttribute("paciente", new Paciente());
		return "crear-expediente";
	}

	@PostMapping("/modificar-paciente")
	public String guardarPaciente(@ModelAttribute Paciente paciente)
	{
		log.info(paciente);

		String password = paciente.getPassword();
		if (password != null)
			paciente.setPassword(passwordEncoder.encode(password));

		pacienteRepository.save(paciente);
		return "redirect:ver-expediente?id=" + paciente.getId();
	}
}
