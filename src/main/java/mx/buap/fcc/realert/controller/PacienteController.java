package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/pacientes")
	private String listarPacientes(Model model)
	{
		model.addAttribute("pacientes", pacienteRepository.findAll());
		return "lista-pacientes";
	}
}
