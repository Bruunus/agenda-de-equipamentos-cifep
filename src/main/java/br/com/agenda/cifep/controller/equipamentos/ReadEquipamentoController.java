package br.com.agenda.cifep.controller.equipamentos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
 
	@GetMapping("/validador-agenda-estoque")
	public ResponseEntity<List<RadarDeReservasAgendadasDTO>> verificadorDeAgendasFuturas(@RequestBody List<LocalDate> datas) {		
		
//		List<LocalDate> datas = dto.getDatas();
		boolean monitoradorDeEstoqueParaReservasPosteriores = true;
		
		try {
			monitoradorDeEstoqueParaReservasPosteriores = 
					updateEstoqueService.getMonitoradorDeEstoqueParaReservasPosteriores(datas);
			
			if(!monitoradorDeEstoqueParaReservasPosteriores) {				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.build();					 
			} else {
				System.out.println("Caiu no bloco 200");
				return ResponseEntity.status(HttpStatus.OK)
						.build();
			}
			
		} catch (EstoqueInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.estoqueInsuficienteException();
			String mensagem = "EstoqueInsuficienteExeception, estoque insuficiente para as datas solicitas.";
			
			List<RadarDeReservasAgendadasDTO> list = 
					UpdateEstoqueService.getReservasInsuficientes();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.header("Mensagem", mensagem)
					.body(list);
			
		}
		
		
		
		
		
		
		
		
		  
	}
	
	
	
	
	
	
	

}
