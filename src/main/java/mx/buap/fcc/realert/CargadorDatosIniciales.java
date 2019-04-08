package mx.buap.fcc.realert;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import mx.buap.fcc.realert.repository.MedicamentoRepository;
import mx.buap.fcc.realert.repository.PersonaRepository;
import mx.buap.fcc.realert.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final PasswordEncoder passwordEncoder;

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
		Medico medico = new Medico();
		medico.setNombre("Aureliano Buendia");
		medico.setCedula("10008000");
		medico.setCorreo("aureliano.buendia@hotmail.com");
		medico.setPassword(passwordEncoder.encode("sipirili"));
		medico.setTelefono("2222334466");
		personaRepository.save(medico);

		Medico medico2 = new Medico();
		medico2.setNombre("Pedro Alfaro");
		medico2.setCedula("80001000");
		medico2.setCorreo("pedro.alfaro@hotmail.com");
		medico2.setPassword(passwordEncoder.encode("xdxdxd"));
		medico2.setTelefono("1010202040");
		personaRepository.save(medico2);

		Paciente paciente = new Paciente();
		Expediente e = new Expediente();
		paciente.setNombre("Usula Iguaran");
		paciente.setComentarios("Ha gozado de longevidad");
		paciente.setCorreo("ursula.iguaran@gmail.com");
		paciente.setPassword(passwordEncoder.encode("noporolo"));
		paciente.setTelefono("3344333436");
		e.setContenido("Primera visita: Todos los indicadores parecen estar en orden");
		paciente.setExpediente(e);
		personaRepository.save(paciente);

		Administrador administrador = new Administrador();
		administrador.setNombre("Jose Arcadio");
		administrador.setCorreo("jose.arcadio@outlook.com");
		administrador.setClave("CLAVE");
		administrador.setPassword(passwordEncoder.encode("trololo"));
		administrador.setTelefono("9988334116");
		personaRepository.save(administrador);

		Medicamento md = new Medicamento();
		PresentacionMedicamento ps = new PresentacionMedicamento();
		md.setNombre("Buscapina");
		md.setFormula("Butilescopolamina, Metamizol");
		ps.setNombre("Tabletas");
		ps.setCantidad("10 mg");
		ps.setViaAdministracion("Oral");
		md.agregarPresentacion(ps);
		medicamentoRepository.save(md);

		Receta r = new Receta();
		DetalleReceta dr = new DetalleReceta();
		r.setFecha(LocalDate.now());
		r.setMedico(medico);
		r.setPaciente(paciente);
		dr.setPresentacion(ps);
		dr.setDosis(2);
		dr.setIntervaloHoras(8);
		dr.setDuracionDias(10);
		dr.setMensaje("Una tableta cada 8 horas");
		r.agregarDetalle(dr);
		recetaRepository.save(r);

		r = new Receta();
		dr = new DetalleReceta();
		r.setFecha(LocalDate.now());
		r.setMedico(medico2);
		r.setPaciente(paciente);
		dr.setPresentacion(ps);
		dr.setDosis(3);
		dr.setIntervaloHoras(10);
		dr.setDuracionDias(5);
		dr.setMensaje("Una tableta cada 10 horas");
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
