package br.com.agenda.cifep.service.equipamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueDeEquipamentosDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.EstoqueDeEquipamentoRepository;

@Service
public class ReadEquipamentoService {
	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;

	
	/**
	 * 	Carrega a lista de equipamentos em estoque
	 * @return lista do banco de dados
	 */
	public List<EstoqueDeEquipamentosDTO> carregarTodosOsEquipamentos() {		
		
		List<EstoqueDeEquipamentosDTO> list = new ArrayList<>();
	    List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();

	    listData.forEach(data -> {	    	
	    	EstoqueDeEquipamentosDTO listaEstoqueDTO = new EstoqueDeEquipamentosDTO();
	    	listaEstoqueDTO.setId(data.getId());
	    	listaEstoqueDTO.setDescricao(data.getDescricao());	
	    	listaEstoqueDTO.setValor(data.getValor());
	    	listaEstoqueDTO.setQuantidade(data.getQuantidade());
	    	list.add(listaEstoqueDTO);
	    });	     

	    return list;
	}
	

	
	

	
}
