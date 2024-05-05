package br.com.agenda.cifep.service.equipamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.model.ReservaDeEquipamento;
import br.com.agenda.cifep.repository.EstoqueDeEquipamentoRepository;

@Service
public class EquipamentoService {

	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	
	//	faça uma query no banco de quantidade armazenada
    //	Subtraia a quantidade armazenada - a quantidade a ser reservada
    //	Devolva esse valor para o frontend
    	
	public void teste() {
		
		List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();
		
		System.out.println("teste");
	}
    	
    	
    
	
    
    
    
	
	
}
