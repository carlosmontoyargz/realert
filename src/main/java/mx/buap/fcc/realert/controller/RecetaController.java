package mx.buap.fcc.realert.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import mx.buap.fcc.realert.repository.DetalleRecetaRepository;
import mx.buap.fcc.realert.repository.PersonaRepository;
import mx.buap.fcc.realert.repository.RecetaRepository;
import mx.buap.fcc.realert.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

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
	private final PersonaRepository personaRepository;
	private final EmailService emailService;

	@GetMapping({"", "/"})
	public String listaRecetas(Model model, Authentication authentication)
	{
		List<Receta> recetas;

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		log.info(userDetails.getUsername());

		Rol rol = Rol.valueOf(userDetails
				.getAuthorities().stream()
				.findFirst().orElseThrow(NullPointerException::new)
				.getAuthority());

		if (Rol.PACIENTE.equals(rol))
			recetas = recetaRepository
					.findByPacienteCorreo(userDetails.getUsername());

		else if (Rol.MEDICO.equals(rol))
			recetas = recetaRepository
					.findByMedicoCorreo(userDetails.getUsername());

		else if (Rol.ADMINISTRADOR.equals(rol))
			recetas = recetaRepository
					.findAll();

		else recetas = Collections.emptyList();

		model.addAttribute("recetas", recetas);
		return "lista-recetas";
	}

	@GetMapping("/ver-receta")
	public String verReceta(@RequestParam("id") int id, Model model)
	{
		model.addAttribute("receta",
				recetaRepository
						.findById(id)
						.orElseThrow(NullPointerException::new));
		return "ver-receta";
	}

	@GetMapping("/crear-receta")
	public String crearReceta(@RequestParam("idPaciente") int idPaciente, Principal principal)
	{
		Receta receta = new Receta();
		personaRepository
				.findByCorreo(principal.getName())
				.ifPresent(m ->
				{
					if (!(m instanceof Medico)) return;
					personaRepository
							.findById(idPaciente)
							.ifPresent(p ->
							{
								if (!(p instanceof Paciente)) return;
								receta.setMedico((Medico) m);
								receta.setPaciente((Paciente) p);
								recetaRepository.save(receta);
							});
				});
		return "redirect:ver-receta?id=" + receta.getId();
	}

	@GetMapping("agregar-detalle")
	public String agregarDetalle(@RequestParam("id") int id, Model model)
	{
		Receta rc = new Receta();
		DetalleReceta dr = new DetalleReceta();
		rc.setId(id);
		dr.setReceta(rc);

		model.addAttribute("detalleReceta", dr);
		return "modificar-detalle-receta";
	}

	@GetMapping("/modificar-detalle")
	public String modificarDetalleReceta(@RequestParam("id") int id, Model model)
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

	@GetMapping("/enviar-alerta")
	public String enviarCorreoAlerta(@RequestParam("id") int id)
	{
		recetaRepository
				.findById(id)
				.ifPresent(emailService::enviarCorreoVenta);

		return "redirect:ver-receta?id=" + id;
	}
}
