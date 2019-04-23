package mx.buap.fcc.realert.service;

/**
 * @author Carlos Montoya
 * @since 23/04/2019
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mx.buap.fcc.realert.domain.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de envio de correos.
 *
 * @author Carlos Montoya
 * @since 26/12/2018
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Log4j2
public class EmailService
{
	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;

	/**
	 * Envia un correo a la direccion del paciente con un reporte sobre la receta
	 * especificada.
	 *
	 * @param receta La receta sobre la que se genera el correo
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void enviarCorreoVenta(Receta receta)
	{
		mailSender.send(mimeMessage ->
		{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(receta.getPaciente().getCorreo());
			messageHelper.setSubject("Tu receta con el medico " + receta.getMedico().getNombre());
			messageHelper.setText(mailContentBuilder.buildTemplateVenta(receta), true);
		});
	}
}
