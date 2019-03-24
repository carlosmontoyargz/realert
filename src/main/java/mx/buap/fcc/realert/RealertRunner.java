package mx.buap.fcc.realert;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import mx.buap.fcc.realert.repository.ExpedienteRepository;
import mx.buap.fcc.realert.repository.MedicamentoRepository;
import mx.buap.fcc.realert.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Carlos Montoya
 * @since 22/03/2019
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class RealertRunner implements CommandLineRunner
{
	private final PersonaRepository personaRepository;
	private final ExpedienteRepository expedienteRepository;
	private final MedicamentoRepository medicamentoRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception
	{
		cargarDatosIniciales();
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
		expedienteRepository.save(e);
		personaRepository.save(p);

		Administrador a = new Administrador();
		a.setNombre("Jose Arcadio");
		a.setCorreo("jose.arcadio@outlook.com");
		a.setClave("CLAVE");
		a.setPassword("sipirili");
		a.setTelefono("2222334466");
		personaRepository.save(a);

		Medicamento md = new Medicamento();
		Presentacion ps = new Presentacion();
		md.setNombre("Buscapina");
		md.setFormula("Butilescopolamina, Metamizol");
		ps.setNombre("Tabletas 10 mg");
		md.agregarPresentacion(ps);
		medicamentoRepository.save(md);

		personaRepository.findAll().forEach(log::debug);
		medicamentoRepository.findAll().forEach(md1 ->
		{
			log.debug(md1);
			md1.getPresentaciones().forEach(log::debug);
		});
	}
}
