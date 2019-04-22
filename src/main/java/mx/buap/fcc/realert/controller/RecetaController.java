package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.DetalleReceta;
import mx.buap.fcc.realert.domain.Receta;
import mx.buap.fcc.realert.repository.DetalleRecetaRepository;
import mx.buap.fcc.realert.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
	private final RecetaRepository recetaRepository;
	private final DetalleRecetaRepository detalleRepository;

	@GetMapping({"", "/"})
	public String listaRecetasPaciente(Model model, Principal principal)
	{
		model.addAttribute("recetas",
				recetaRepository
						.findByPacienteCorreo(principal.getName()));
		return "lista-recetas";
	}

	@GetMapping("/ver-receta")
	public String verReceta(Model model, @RequestParam("id") int id)
	{
		model.addAttribute("receta",
				recetaRepository
						.findById(id)
						.orElseThrow(NullPointerException::new));
		return "ver-receta";
	}

	@GetMapping("agregar-detalle")
	public String agregarDetalle(Model model, @RequestParam("id") int id)
	{
		Receta rc = new Receta();
		DetalleReceta dr = new DetalleReceta();
		rc.setId(id);
		dr.setReceta(rc);

		model.addAttribute("detalleReceta", dr);
		return "modificar-detalle-receta";
	}

	@GetMapping("/modificar-detalle")
	public String modificarDetalleReceta(Model model, @RequestParam("id") int id)
	{
		model.addAttribute("detalleReceta",
				detalleRepository
						.findById(id)
						.orElseThrow(NullPointerException::new));
		return "modificar-detalle-receta";
	}

	@PostMapping("/modificar-detalle")
	public String guardarDetalle(@ModelAttribute DetalleReceta detalleReceta)
	{
		log.trace(detalleReceta);
		detalleRepository.save(detalleReceta);
		return "redirect:ver-receta?id=" + detalleReceta.getReceta().getId();
	}

	@GetMapping("/eliminar-detalle")
	public String eliminarDetalle(@RequestParam("id") int id, HttpServletRequest request)
	{
		detalleRepository.deleteById(id);
		return "redirect:" + request.getHeader("Referer");
	}

	@GetMapping("/eliminar-receta")
	public String eliminarReceta(@RequestParam("id") int id)
	{
		recetaRepository.deleteById(id);
		return "redirect:/recetas";
	}
}
