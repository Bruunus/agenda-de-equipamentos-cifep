package br.com.agenda.cifep.controller.reserva;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.equipamentos.ReservaDeFluxoDeEquipamentoDTO;
import br.com.agenda.cifep.dto.reserva.ReservaDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import br.com.agenda.cifep.service.estoque.UpdateEstoqueService;
import br.com.agenda.cifep.service.reserva.ReadReservaService;

// implanar o cors quando for testar no front-angular
@RestController
@RequestMapping("load/")
//@CrossOrigin("*")
public class ReadReservaController {
		
	@Autowired
	private ReadReservaService readReservaService;
	@Autowired
	private UpdateEstoqueService updateEstoqueService;

	
	
	//ativas 
		
	@GetMapping("actives/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarAsReservasAtivas() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	/* Funcionalidade desativada */
//	@GetMapping("actives-full-year/reservas")
//	public ResponseEntity<List<ReservaDTO>> carregarAsReservasAtivasAnual() {
//		List<ReservaDTO> list = readReservaService.carregarTodasReservasAtivasAnuais();
//		return new ResponseEntity<>(list, HttpStatus.OK);
//		
//	}
	
	@GetMapping("month/{mes}/reservas")
	public ResponseEntity<List<ReservaDTO>> pesquisarReservaPorMesAtivas(@PathVariable int mes) {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasMesAtual(mes);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("current-month/reservas")
	public ResponseEntity<?> carregarReservasDoMesAtual() {
		List<ReservaDTO> list = readReservaService.carregarTodasReservasDoMesAtual();
		
		// ordenar por dia 
//		Collections.sort(list, Comparator.comparing(ReservaDTO::getDataRetirada));
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Não há nenhuma reserva para este mês!");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	@GetMapping("current-day/reservas")
	//@CrossOrigin("*")
	public ResponseEntity<?> carregarReservasDoDia() {
		
		System.out.println("Reservas do dia");
		
		List<ReservaDTO> list = readReservaService.carregarTodasReservasDoDiaAtual();		
		
		if(list.isEmpty()) {
			System.out.println("A lista está vazia");
			 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Não há nenhuma reserva para o dia!");
		} else {
			
			 
			
			return ResponseEntity.ok(list);
		}		
	}
	
	@GetMapping("active-for-sector/reservas")
	public ResponseEntity<?> carregarReservasAtivasPorSetor(@RequestParam("search/sector") String sector) {
		List<ReservaDTO> list = readReservaService.carregarReservasPorSetor(sector);
		
		if(list.isEmpty() || list == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Nenhum resultado na pesquisa.");
		} else {
			return ResponseEntity.ok(list);
		}
	}
	
	@GetMapping("reservas")
	public ResponseEntity<?> pesquisarReservaPorNomeAtivas(@RequestParam("search") String search) {
		List<ReservaDTO> list = readReservaService.pesquisarReservasPorNomeOuSetor(search);
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nenhum resultado na pesquisa.");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	
	
	
	
	
	
	
	// finalizadas	
	
	@GetMapping("finished/reservas")
	public ResponseEntity<List<ReservaDTO>> carregarsReservasFinalizadas() {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadas();
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}	
	
	@GetMapping("finished-for-date/{mes}/reservas")
	public ResponseEntity<List<ReservaDTO>> pesquisarReservaPorMesFanalizadas(@PathVariable int mes) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorMes(mes);
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}	
	
	@GetMapping("finished-for-name/reservas")
	public ResponseEntity<?> pesquisarReservaPorNomeFanalizadas(@RequestParam("search/name") String nome) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorNome(nome);
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nenhum resultado na pesquisa.");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	@GetMapping("finished-for-sector/reservas")
	public ResponseEntity<?> pesquisarReservaPorSetorFanalizadas(@RequestParam("search/sector") String setor) {
		List<ReservaDTO> list = readReservaService.carregarReservasFinalizadasPorSetor(setor);
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Nome fornecido não encontrado!");
		} else {
			return ResponseEntity.ok(list);
		}		
	}
	
	
	
	
	
	
}
