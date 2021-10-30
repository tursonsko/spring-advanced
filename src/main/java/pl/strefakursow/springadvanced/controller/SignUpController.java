package pl.strefakursow.springadvanced.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.strefakursow.springadvanced.aop.AdditionalAuthentication;
import pl.strefakursow.springadvanced.aop.AdditionalCredentialsDto;
import pl.strefakursow.springadvanced.entity.User;
import pl.strefakursow.springadvanced.service.SignUpService;

@Controller
@Slf4j
public class SignUpController {

	private SignUpService signUpService;

	@Autowired
	public SignUpController(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	//todo tutaj aspect
	@GetMapping(value = "/sign_up")
	public ModelAndView signUp(ModelAndView mav) {
		mav.setViewName("sign_up");
		return mav;
	}

	@PostMapping(value = "/sign_up")
	public ModelAndView signUpPost(ModelAndView mav, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String email) {
		mav.setViewName("redirect:/login");
		User user = User.of(username, password, email);
		signUpService.signUpUser(user);
		return mav;
	}

	@PostMapping(value = "/aop_test")
	@AdditionalAuthentication
	public String getTest(@RequestBody AdditionalCredentialsDto credentialsDto) {
		log.info("Controller: getTest");

		return "HELLO FROM getTest()";
	}

}
