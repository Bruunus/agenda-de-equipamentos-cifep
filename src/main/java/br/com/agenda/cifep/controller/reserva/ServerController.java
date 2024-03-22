package br.com.agenda.cifep.controller.reserva;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ServerController {

	
	@GetMapping("home")
	public String homeRedirect() {
		
		return "Agenda de equipamentos CIFEP";
	}
	
	
	
	
	
	
	
	
	
}
