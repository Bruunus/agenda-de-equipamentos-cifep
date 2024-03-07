package br.com.agenda.cifep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.ReservaService;


@RestController
@RequestMapping
public class ReadController {
	
	@Autowired
	private ReservaService reservaService;

	
	@GetMapping("load/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservas() {
		List<ReservaDTO> list = reservaService.carregarTodasReservas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
}
