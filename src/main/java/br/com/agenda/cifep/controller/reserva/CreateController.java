package br.com.agenda.cifep.controller.reserva;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.reserva.CreateReservaService;

@RestController
@RequestMapping("/")
public class CreateController {
	
	
	@Autowired 
	CreateReservaService createReservaService;

	
	
	@PostMapping("new/scheduled/reserva")
	public ResponseEntity<String> criarReservaAgendadaNaoAnual(@RequestBody ReservaDTO reservaDTO) {
	 
		boolean reservaRealizada = createReservaService.novaReservaAgendadaEventual(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva");
		    } 	        
		 
	}
	
	
	@PostMapping("new/scheduled-full-year/reserva")
	public ResponseEntity<String> criarReservaAgendadaAnual(@RequestBody ReservaDTO reservaDTO) {
	 
		boolean reservaRealizada = createReservaService.novaReservaAgendadaAnual(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva anual realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva anual.");
		    } 	        
		 
	}
	
	
	//@PostMapping("new/scheduled-multiple/reserva")
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PutMapping("fechar/reserva")
//	public ResponseEntity<String> finalizarReserva()
	
	
}
