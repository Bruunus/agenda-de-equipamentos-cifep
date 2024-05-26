package br.com.agenda.cifep.controller.equipamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.agenda.cifep.dto.ConexaoWebSocketDTO;
import br.com.agenda.cifep.service.equipamento.ReadEquipamentoService;

@Controller
public class ReadEquipamentoControllerWebSocket {

	@Autowired
	ReadEquipamentoService equipamentoService;
	
//	Fase de criar um controller que busca no banco os dados do estoque para r
//	enviar para angular
	public ConexaoWebSocketDTO getEstoqueWS
	
}
