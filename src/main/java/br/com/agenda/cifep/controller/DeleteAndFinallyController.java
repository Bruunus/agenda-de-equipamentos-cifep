package br.com.agenda.cifep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.service.DeleteAndFinallyReservaService;

@RestController
@RequestMapping("/")
public class DeleteAndFinallyController {
	
	
	@Autowired
	private DeleteAndFinallyReservaService deleteAndFinallyReservaService; 
	
	
	@PutMapping("reserva/{id}/close-on")
	public ResponseEntity<String> finalizarReserva(@PathVariable Long id) {
		boolean statusFecharReserva = deleteAndFinallyReservaService.finalizaReserva(id);
		
		if(statusFecharReserva) {
			return ResponseEntity.ok("Reserva finalizada com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro no processamento de finalizar a reserva");
		}
	}
	

}
