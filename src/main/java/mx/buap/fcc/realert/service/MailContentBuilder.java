package mx.buap.fcc.realert.service;

import lombok.RequiredArgsConstructor;
import mx.buap.fcc.realert.domain.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Carlos Montoya
 * @since 23/04/2019
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MailContentBuilder
{
	private final TemplateEngine templateEngine;

	/**
	 * Genera un reporte en HTML de la receta pasada como parametro, usado el template mail-receta
	 * que se encuentra en el directorio /resources/templates
	 *
	 * @param receta La receta sobre la que se genera el reporte
	 * @return El reporte generado en formato HTML
	 */
	@Transactional
	public String buildTemplateVenta(Receta receta)
	{
		Context context = new Context();
		context.setVariable("receta", receta);
		return templateEngine.process("mail-receta", context);
	}
}
