package mx.buap.fcc.realert.repository;

import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Log4j2
public class PersonaRepositoryTest
{
	@Autowired PersonaRepository personaRepository;
	@Autowired ExpedienteRepository expedienteRepository;

	@Test
	@Transactional
	public void db()
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

		personaRepository.findAll().forEach(log::info);
	}
}
