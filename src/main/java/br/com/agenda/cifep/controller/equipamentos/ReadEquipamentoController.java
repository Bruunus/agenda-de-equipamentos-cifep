package br.com.agenda.cifep.controller.equipamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.cifep.dto.EstoqueDeEquipamentosDTO;
import br.com.agenda.cifep.service.equipamento.ReadEquipamentoService;

@RestController
@RequestMapping("load/")
public class ReadEquipamentoController {
	
	@Autowired
	ReadEquipamentoService equipamentoService;
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getestoque")
	public ResponseEntity<EstoqueDeEquipamentosDTO> carregarEstoqueDeEquipamentos() {
		List<EstoqueDeEquipamentosDTO> list = equipamentoService.carregarTodosOsEquipamentos();
		return new ResponseEntity(list, HttpStatus.OK);
		
	}
	

}
