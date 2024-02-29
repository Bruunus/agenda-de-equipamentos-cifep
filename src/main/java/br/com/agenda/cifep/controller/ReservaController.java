package br.com.agenda.cifep.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.ReservaService;

@RestController
@RequestMapping("/")
public class ReservaController {
	
	
	@Autowired ReservaService reservaService;

	
	@PostMapping("reserva")
	public ResponseEntity<String> criarReserva(@RequestBody ReservaDTO reservaDTO) {
		try {
			
			reservaService.novaReserva(reservaDTO);
			return ResponseEntity.ok("Reserva realizada com sucesso!");
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        e.printStackTrace(pw);
	        String stackTrace = sw.toString();
	        
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	        		.body("Erro ao realizar a reserva" + e.getMessage() + "\n\nStack Trace:\n" + stackTrace);
	        
		}
			 
	}
	
	
	
	
	
	
	
	
	
}
