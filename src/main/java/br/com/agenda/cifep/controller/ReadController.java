package br.com.agenda.cifep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.CreateReservaService;
import br.com.agenda.cifep.service.ReadReservaService;


@RestController
@RequestMapping("/")
public class ReadController {
		
	@Autowired
	private ReadReservaService readReservaService;

	
	@GetMapping("load/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservas() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("load/actives/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservasAtivas() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("load/actives-full-year/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservasAtivasAnual() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivasAnuais();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("load/month/{mes}/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservasMesAtual(@PathVariable int mes) {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasMesAtual(mes);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
}
