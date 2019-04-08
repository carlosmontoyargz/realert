package mx.buap.fcc.realert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para la aplicacion web.
 *
 * @author Carlos Montoya
 * @since 24/03/2019
 */
@Controller
public class IndexController
{
	@GetMapping({"/", "/index", "/index.html"})
	public String index(//@RequestParam(name="name", required=false, defaultValue="World") String name,
	                    Model model)
	{
		//model.addAttribute("name", name);
		return "index";
	}

	@GetMapping("/login")
	public String login()
	{
		return "login";
	}

	@GetMapping("/hello")
	public String hello()
	{
		return "hello";
	}

	@GetMapping("/template-base")
	public String templateBase()
	{
		return "template-base.html";
	}
}
