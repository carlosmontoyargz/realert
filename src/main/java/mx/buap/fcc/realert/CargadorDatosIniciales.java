package mx.buap.fcc.realert;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import mx.buap.fcc.realert.repository.MedicamentoRepository;
import mx.buap.fcc.realert.repository.PersonaRepository;
import mx.buap.fcc.realert.repository.RecetaRepository;
import mx.buap.fcc.realert.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class CargadorDatosIniciales implements CommandLineRunner
{
	private final PersonaRepository personaRepository;
	private final MedicamentoRepository medicamentoRepository;
	private final RecetaRepository recetaRepository;
	private final RolRepository rolRepository;

	@Override
	@Transactional
	public void run(String... args)
	{
		cargarDatosIniciales();
		mostrarDatosIniciales();
	}

	@Transactional
	protected void cargarDatosIniciales()
	{
		Rol rol;

		Medico medico = new Medico();
		rol = new Rol();
		medico.setNombre("Aureliano Buendia");
		medico.setCedula("10008000");
		medico.setCorreo("aureliano.buendia@hotmail.com");
		medico.setPassword("sipirili");
		medico.setTelefono("2222334466");
		rol.setAuthority("medico");
		medico.setRol(rol);
		personaRepository.save(medico);

		Paciente paciente = new Paciente();
		Expediente e = new Expediente();
		rol = new Rol();
		paciente.setNombre("Usula Iguaran");
		paciente.setComentarios("Ha gozado de longevidad");
		paciente.setCorreo("ursula.iguaran@gmail.com");
		paciente.setPassword("noporolo");
		paciente.setTelefono("3344333436");
		e.setContenido("Primera visita: Todos los indicadores parecen estar en orden");
		paciente.setExpediente(e);
		rol.setAuthority("paciente");
		paciente.setRol(rol);
		personaRepository.save(paciente);

		Administrador administrador = new Administrador();
		rol = new Rol();
		administrador.setNombre("Jose Arcadio");
		administrador.setCorreo("jose.arcadio@outlook.com");
		administrador.setClave("CLAVE");
		administrador.setPassword("trololo");
		administrador.setTelefono("9988334116");
		rol.setAuthority("admin");
		administrador.setRol(rol);
		personaRepository.save(administrador);

		Medicamento md = new Medicamento();
		PresentacionMedicamento ps = new PresentacionMedicamento();
		md.setNombre("Buscapina");
		md.setFormula("Butilescopolamina, Metamizol");
		ps.setNombre("Tabletas");
		ps.setCantidad("10 mg");
		md.agregarPresentacion(ps);
		medicamentoRepository.save(md);

		Receta r = new Receta();
		DetalleReceta dr = new DetalleReceta();
		r.setFecha(LocalDate.now());
		r.setMedico(medico);
		r.setPaciente(paciente);
		dr.setPresentacion(ps);
		dr.setDosis("Una tableta cada 8 horas");

		r.agregarDetalle(dr);
		recetaRepository.save(r);
	}

	@Transactional
	protected void mostrarDatosIniciales()
	{
		log.debug("Personas:");
		personaRepository.findAll().forEach(log::debug);

		log.debug("Medicamentos:");
		medicamentoRepository.findAll().forEach(medicamento ->
		{
			log.debug(medicamento);
			medicamento.getPresentaciones().forEach(log::debug);
		});

		log.debug("Recetas:");
		recetaRepository.findAll().forEach(receta ->
		{
			log.debug(receta);
			receta.getDetalles().forEach(log::debug);
		});
	}
}
