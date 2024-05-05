package br.com.agenda.cifep.controller.reserva;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.reserva.CreateReservaService;

@RestController
@RequestMapping("/")
public class CreateReservaController {
	
	
	@Autowired 
	CreateReservaService createReservaService;

	
	
	@PostMapping("new/scheduled/reserva")
	public ResponseEntity<String> criarReservaEventual(@RequestBody ReservaDTO reservaDTO) {
	 
		boolean reservaRealizada = createReservaService.novaReservaAgendadaEventual(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva");
		    } 	        
		 
	}
	
	
	@PostMapping("new/scheduled-full-year/reserva")
	public ResponseEntity<String> criarReservaAgendadaAnual(@RequestBody List<ReservaDTO> reservaDTO) {
	 
		boolean reservaRealizada = createReservaService.novaReservaAgendadaAnual(reservaDTO);

	    if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva anual realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva anual.");
		    } 	        
		 
	}
	
	
	
	
	@PostMapping("new/scheduled-multiple/reserva")
	public ResponseEntity<String> criarReservaAgendadaMultipla(@RequestBody List<ReservaDTO>reservaDTO) {
		boolean reservaRealizada = createReservaService.createReservaMultipla(reservaDTO);
		
		if (reservaRealizada) {
	        return ResponseEntity.ok("Reserva multipla realizada com sucesso!");
		    } else {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Erro ao realizar a reserva multipla.");
		    } 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@PutMapping("fechar/reserva")
//	public ResponseEntity<String> finalizarReserva()
	
	
}
