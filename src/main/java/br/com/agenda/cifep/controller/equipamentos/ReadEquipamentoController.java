package br.com.agenda.cifep.controller.equipamentos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.equipamentos.EstoqueDeEquipamentosDTO;
import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.reserva.RadarDeReservasAgendadasDTO;
import br.com.agenda.cifep.service.equipamento.ReadEquipamentoService;
import br.com.agenda.cifep.service.error.EstoqueInsuficienteException;
import br.com.agenda.cifep.service.estoque.UpdateEstoqueService;

@RestController
@RequestMapping("load/")
public class ReadEquipamentoController {
	
	@Autowired
	ReadEquipamentoService equipamentoService;
	
	@Autowired
	UpdateEstoqueService updateEstoqueService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getestoque")
	public ResponseEntity<EstoqueDeEquipamentosDTO> carregarEstoqueDeEquipamentos() {
		List<EstoqueDeEquipamentosDTO> list = equipamentoService.carregarTodosOsEquipamentos();
		return new ResponseEntity(list, HttpStatus.OK);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getestoquequantidade")
	public ResponseEntity<EstoqueQuantidadeDTO> carregarEstoqueDeQuantidade() {
		List<EstoqueQuantidadeDTO> list = equipamentoService.getEstoqueQuantidades();
		return new ResponseEntity(list, HttpStatus.OK);
		
	}
	
	
 
 
	@PostMapping("/pesquisa-reservas-futuras")
	public ResponseEntity<List<RadarDeReservasAgendadasDTO>> verificadorDeAgendasFuturas(@RequestBody List<LocalDate> datas) {				
		
		@SuppressWarnings("unused")
		List<RadarDeReservasAgendadasDTO> list = null;
		try {
			list = updateEstoqueService.getEstoqueDisponivelDeAgenda(datas);
			
			return ResponseEntity.status(HttpStatus.OK)
					.build();
			
		} catch (EstoqueInsuficienteException  e) {
			e.printStackTrace();
			List<RadarDeReservasAgendadasDTO> estoqueInsuficienteException = e.estoqueInsuficienteException();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(estoqueInsuficienteException);
		}			
		
		  
	}
	
	
	
	
	
	
	

}
