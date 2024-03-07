package br.com.agenda.cifep.controller;

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
import br.com.agenda.cifep.service.ReservaService;

@RestController
@RequestMapping("/")
public class ReservaController {
	
	
	@Autowired 
	ReservaService reservaService;

	
	@PostMapping("new/reserva")
	public ResponseEntity<String> criarReservaDiaria(@RequestBody ReservaDTO reservaDTO) {
	 
		boolean reservaRealizada = reservaService.novaReserva(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva");
		    } 	        
		 
	}
	
	
	@PostMapping("new/scheduled/reserva")
	public ResponseEntity<String> criarReservaAgendada(@RequestBody ReservaDTO reservaDTO) {
	 
		boolean reservaRealizada = reservaService.novaReservaAgendada(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva");
		    } 	        
		 
	}
	
	
	
	
	
	
	
	@PutMapping("reserva/{id}/close-on")
	public ResponseEntity<String> finalizarReserva(@PathVariable Long id) {
		boolean statusFecharReserva = reservaService.finalizaReserva(id);
		
		if(statusFecharReserva) {
			return ResponseEntity.ok("Reserva finalizada com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro no processamento de finalizar a reserva");
		}
	}
	
	
//	@PutMapping("fechar/reserva")
//	public ResponseEntity<String> finalizarReserva()
	
	
}
