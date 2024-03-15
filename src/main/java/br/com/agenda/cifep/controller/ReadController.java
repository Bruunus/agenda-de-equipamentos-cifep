package br.com.agenda.cifep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.service.ReadReservaService;


@RestController
@RequestMapping("load/")
public class ReadController {
		
	@Autowired
	private ReadReservaService readReservaService;

	
		
	@GetMapping("actives/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservasAtivas() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("finished/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarsReservasFinalizadas() {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("actives-full-year/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarTodasAsReservasAtivasAnual() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivasAnuais();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("month/{mes}/reservas")
	public ResponseEntity<List<ReservaDTO>> pesquisarReservaPorMesAtivas(@PathVariable int mes) {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasMesAtual(mes);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("finished-for-date/{mes}/reservas")
	public ResponseEntity<List<ReservaDTO>> pesquisarReservaPorMesFanalizadas(@PathVariable int mes) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorMes(mes);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	
	@GetMapping("finished-for-name/reservas")
	public ResponseEntity<?> pesquisarReservaPorNomeFanalizadas(@RequestParam("name") String nome) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorNome(nome);
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nome fornecido não encontrado!");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	@GetMapping("finished-for-sector/reservas")
	public ResponseEntity<?> pesquisarReservaPorSetorFanalizadas(@RequestParam("sector") String setor) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorSetor(setor);
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nome fornecido não encontrado!");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	
	
	
	
	
}
