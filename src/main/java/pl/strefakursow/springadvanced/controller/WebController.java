package pl.strefakursow.springadvanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.strefakursow.springadvanced.entity.User;
import pl.strefakursow.springadvanced.event.UserPanelEnterPublisher;

@Controller
public class WebController {

	private UserPanelEnterPublisher publisher;
	private String greetings;

	@Autowired
	public WebController(UserPanelEnterPublisher publisher, @Value("${user.panel.greetings}") String greetings) {
		this.publisher = publisher;
		this.greetings = greetings;
	}

	@RequestMapping(value = "/user_panel", method = RequestMethod.GET)
	public ModelAndView userPanel(ModelAndView mav) {
		mav.setViewName("user_panel");
		User principal =  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		publisher.publish(principal.getUsername());
		mav.addObject("greetings", greetings);
		return mav;
	}

	@RequestMapping(value = "/admin_panel", method = RequestMethod.GET)
	public ModelAndView adminPanel(ModelAndView mav) {
		mav.setViewName("admin_panel");

		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login");

		return mav;
	}
}