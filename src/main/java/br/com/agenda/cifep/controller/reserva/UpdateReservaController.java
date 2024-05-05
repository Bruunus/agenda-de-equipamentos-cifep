package br.com.agenda.cifep.controller.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.reserva.UpdateReservaService;

@RestController
@RequestMapping("update/")
public class UpdateReservaController {

	@Autowired
	private UpdateReservaService updateReservaService;
	
	 
	@PutMapping("reserva/{id}")
	public ResponseEntity<ReservaDTO> atualizarReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
		ReservaDTO updateReserva = updateReservaService.atualizarReserva(id, reservaDTO);		
		return ResponseEntity.ok(updateReserva);
	}
	
	
	
}
