package mx.buap.fcc.realert.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Carlos Montoya
 * @since 07/04/2019
 */
@ControllerAdvice
@Log4j2
public class ErrorController
{
	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String exception(final Throwable throwable, final Model model)
	{
		log.error(throwable);
		model.addAttribute("errorMessage",
				(throwable != null ? throwable.getMessage() : "Unknown error"));
		return "error";
	}
}
