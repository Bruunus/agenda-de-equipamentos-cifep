package br.com.agenda.cifep.config;

import org.springframework.stereotype.Service;

import br.com.agenda.cifep.repository.EstoqueDeEquipamentoRepository;

@Service
public class RepositoruBean {

	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	    
	    public RepositoruBean(EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository) {
	        this.estoqueDeEquipamentoRepository = estoqueDeEquipamentoRepository;
	    }
	    
	 
}
