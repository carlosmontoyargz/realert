package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * @author Carlos Montoya
 * @since 04/04/2019
 */
@Controller
@RequestMapping("/recetas")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class RecetaController
{
	private final RecetaRepository repository;

	@GetMapping("/lista-recetas")
	public String listaRecetasPaciente(Model model, Principal principal)
	{
		model.addAttribute("recetas",
				repository
						.findByPacienteCorreo(principal.getName()));
		return "lista-recetas";
	}

	@GetMapping("/lista-recetas/receta")
	public String mostrarReceta(@RequestParam(value = "id") int id, Model model)
	{
		model.addAttribute("receta",
				repository
						.findById(id)
						.orElseThrow(NullPointerException::new));
		return "vista-receta";
	}
}
