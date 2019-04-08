package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import mx.buap.fcc.realert.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Carlos Montoya
 * @since 04/04/2019
 */
@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RecetaController
{
	private final RecetaRepository repository;

	@GetMapping("/lista-recetas-paciente")
	public String listaRecetasPaciente(Model model, Principal principal)
	{
		model.addAttribute("recetas",
				repository
						.findByPacienteCorreo(principal.getName()));
		return "lista-recetas-paciente";
	}
}
