package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.Medico;
import mx.buap.fcc.realert.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Carlos Montoya
 * @since 22/04/2019
 */
@Controller
@RequestMapping("/medicos")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class MedicoController
{
	private final MedicoRepository medicoRepository;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("")
	private String listarMedicos(Model model)
	{
		model.addAttribute("medicos", medicoRepository.findAll());
		return "lista-medicos";
	}

	@GetMapping("/ver")
	public String verMedico(@RequestParam("id") int id, Model model)
	{
		model.addAttribute("medico",
				medicoRepository
						.findById(id)
						.orElse(null));
		return "ver-medico";
	}

	@GetMapping("/crear-medico")
	public String crearMedico(Model model)
	{
		model.addAttribute("medico", new Medico());
		return "crear-medico";
	}

	@PostMapping("/modificar-medico")
	public String guardarMedico(@ModelAttribute Medico medico)
	{
		log.info(medico);

		String password = medico.getPassword();

		if (password != null)
			medico.setPassword(passwordEncoder.encode(password));
		medicoRepository.save(medico);
		return "redirect:ver?id=" + medico.getId();
	}
}
