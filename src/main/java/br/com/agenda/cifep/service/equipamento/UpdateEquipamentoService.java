package br.com.agenda.cifep.service.equipamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.model.ReservaDeEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;

@Service
public class UpdateEquipamentoService {
	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	
	
	
	
	public void atualizaEstoqueAbrirReserva(List<ReservaDeEquipamento> equipamentosRequest) {
		
		List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();
		
		equipamentosRequest.forEach(dataRequest -> {
			
			listData.forEach(dataEmEstoque -> {
				
				if(dataRequest.getDescricao().equals(dataEmEstoque.getValor())) {
					Integer quantidadeRequest = dataRequest.getQuantidade();
					Integer quantidadeData = dataEmEstoque.getQuantidade();
					Integer updateQuantidade = quantidadeData - quantidadeRequest;
					 
					dataEmEstoque.setQuantidade(updateQuantidade);
					estoqueDeEquipamentoRepository.save(dataEmEstoque);
				}
				
			});
			 
		});
		
//		listData.forEach(atualizado -> {
//			System.out.println("Update: "+atualizado.getDescricao()+" "+
//			atualizado.getQuantidade());
//		});
		    	
		    	
    }
	
	
	
	public void atualizaEstoqueAoFecharReserva(Long id)    {
		
		
		//	crie uma query fazendo para buscar na tabela de equipamentos emprestados pelo id que voce recebeu
		/*
		  	SELECT r.*
			FROM reserva r
			JOIN reserva_equipamento re 
			ON r.reserva_id = re.reserva_id
			WHERE re.reserva_id = <valor_da_chave_de_ligacao>     que é => Long id
 
		 */
		
		
		// execute o carregamento do estoque para poder comparar
		// List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();
		
		
		
		// Depois itere sobre esta lista e compare os dois valores das duas listas	
		
		
//		equipamentosRequest.forEach(dataRequest -> {
//			
//			listData.forEach(data -> {
//				
//				if(dataRequest.getDescricao().equals(data.getDescricao())) {
//					Integer quantidadeRequest = dataRequest.getQuantidade();
//					Integer quantidadeData = data.getQuantidade();
//					Integer updateQuantidade = quantidadeData + quantidadeRequest;
//					 
//					data.setQuantidade(updateQuantidade);
//					estoqueDeEquipamentoRepository.save(data);
//				}
//				
//			});
//			 
//		});
//		
//		listData.forEach(atualizado -> {
//			System.out.println("Update: "+atualizado.getDescricao()+" "+
//			atualizado.getQuantidade());
//		});
	}

}
