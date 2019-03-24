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
	@GetMapping({"/", "index.html"})
	public String index(//@RequestParam(name="name", required=false, defaultValue="World") String name,
	                    Model model)
	{
		//model.addAttribute("name", name);
		return "index";
	}

	@GetMapping("/add-new-post.html")
	public String newPost()
	{
		return "add-new-post";
	}

	@GetMapping("/components-blog-posts.html")
	public String blogPosts()
	{
		return "components-blog-posts";
	}

	@GetMapping("/errors.html")
	public String errors()
	{
		return "errors";
	}

	@GetMapping("/form-components.html")
	public String forms()
	{
		return "form-components";
	}

	@GetMapping("/tables.html")
	public String tables()
	{
		return "tables";
	}

	@GetMapping("/user-profile-lite.html")
	public String userProfile()
	{
		return "user-profile-lite";
	}

	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
}
