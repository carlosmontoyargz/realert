package mx.buap.fcc.realert;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import mx.buap.fcc.realert.repository.MedicamentoRepository;
import mx.buap.fcc.realert.repository.PersonaRepository;
import mx.buap.fcc.realert.repository.RecetaRepository;
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
		Medico m = new Medico();
		m.setNombre("Aureliano Buendia");
		m.setCedula("10008000");
		m.setCorreo("aureliano.buendia@hotmail.com");
		m.setPassword("sipirili");
		m.setTelefono("2222334466");
		personaRepository.save(m);

		Paciente p = new Paciente();
		Expediente e = new Expediente();
		p.setNombre("Usula Iguaran");
		p.setComentarios("Ha gozado de longevidad");
		p.setCorreo("ursula.iguaran@gmail.com");
		p.setPassword("sipirili");
		p.setTelefono("2222334466");
		e.setContenido("Primera visita: Todos los indicadores parecen estar en orden");
		p.setExpediente(e);
		personaRepository.save(p);

		Administrador a = new Administrador();
		a.setNombre("Jose Arcadio");
		a.setCorreo("jose.arcadio@outlook.com");
		a.setClave("CLAVE");
		a.setPassword("sipirili");
		a.setTelefono("2222334466");
		personaRepository.save(a);

		Medicamento md = new Medicamento();
		PresentacionMedicamento ps = new PresentacionMedicamento();
		md.setNombre("Buscapina");
		md.setFormula("Butilescopolamina, Metamizol");
		ps.setNombre("Tabletas 10 mg");
		md.agregarPresentacion(ps);
		medicamentoRepository.save(md);

		Receta r = new Receta();
		DetalleReceta dr = new DetalleReceta();
		r.setFecha(LocalDate.now());
		r.setMedico(m);
		r.setPaciente(p);
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
