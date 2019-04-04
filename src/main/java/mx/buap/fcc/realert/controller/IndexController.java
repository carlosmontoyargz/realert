package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import mx.buap.fcc.realert.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para la aplicacion web.
 *
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class IndexController
{
	private final RecetaRepository recetaRepository;

	@GetMapping({"/", "index.html"})
	public String index(//@RequestParam(name="name", required=false, defaultValue="World") String name,
	                    Model model)
	{
		//model.addAttribute("name", name);
		return "index";
	}

	@GetMapping("/lista-recetas-paciente")
	public String listaRecetas(Model model)
	{
		model.addAttribute("recetas", recetaRepository.findAll());
		return "lista-recetas-paciente";
	}

	@GetMapping("/login")
	public String login()
	{
		return "login";
	}

	@GetMapping("/template-base")
	public String templateBase()
	{
		return "template-base.html";
	}
}
