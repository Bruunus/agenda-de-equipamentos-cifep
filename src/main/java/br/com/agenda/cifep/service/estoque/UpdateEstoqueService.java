package br.com.agenda.cifep.service.equipamento;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.config.InicializadorDeSistema;
import br.com.agenda.cifep.dto.reserva.AgendaDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.ReservaDeFluxoDeEquipamento;
import br.com.agenda.cifep.model.TipoReserva;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import br.com.agenda.cifep.repository.reserva.ReservaRepository;
import br.com.agenda.cifep.service.error.EstoqueInsuficienteException;


/**
 * @author Bruno Fernandes dos Santos
 */
@Service
public class UpdateEquipamentoService {
	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	
	@Autowired
	private InicializadorDeSistema inicializadorDeSistema;
	
	@SuppressWarnings("unused")
	private static int day = LocalDate.now().getDayOfMonth();
	
	
	
	/**
	 * Este método é uma funcionalidade aplicada nas classes de serviço quando uma reserva
	 * é realizada. Desse reseva é passado uma lista de equipamentos que vem da requisição.
	 * 
	 * O processo é que buscamos todos os dados da tebela 'estoque-equipamentos', em seguida
	 * com a lista fornecida realizamos uma iteração dos seus itens, e, para cada item 
	 * realizamos uma nova iteração porém com a lista dos itens do estoque. E, para casa
	 * item do estoque realizo uma comparação da descrição da lista fornecida com a do campo 
	 * valor do banco de dados. Se a condição for verdadeira pegamos o valor de quantidade
	 * de cada lista e realizamos a atualização do estoque.
	 * 
	 * 
	 * 
	 * @param equipamentosRequest
	 */
	public void atualizacaoDeEstoque(
		List<ReservaDeFluxoDeEquipamento> equipamentosRequest, 	LocalDate data_retirada
	) {		
		
		List<EstoqueEquipamento> estoqueData = estoqueDeEquipamentoRepository.findAll();
		LocalDate now = LocalDate.now();
		
		
		System.out.println("Data da reserva: "+data_retirada +"\n"+ "Data do sistema: "+now);
		
		
		equipamentosRequest.forEach(dataRequest -> {
			
			estoqueData.forEach(dataEmEstoque -> {
				
				if(dataRequest.getDescricao().equals(dataEmEstoque.getValor()) && 
						data_retirada.equals(now)) {
					Integer quantidadeRequest = dataRequest.getQuantidade();
					Integer quantidadeData = dataEmEstoque.getQuantidade();
					Integer updateQuantidade = quantidadeData - quantidadeRequest;
					 
					dataEmEstoque.setQuantidade(updateQuantidade);
					estoqueDeEquipamentoRepository.save(dataEmEstoque);
					System.out.println("Estoque atualizado");
				}
				
			});
			 
		});    	
		    	
    }
	
	
	
public void atualizacaoDeEstoque(List<ReservaDeFluxoDeEquipamento> equipamentosRequest) {		
		
		List<EstoqueEquipamento> estoqueData = estoqueDeEquipamentoRepository.findAll();
		LocalDate now = LocalDate.now();
		
		equipamentosRequest.forEach(dataRequest -> {
			
			estoqueData.forEach(dataEmEstoque -> {
				
				if(dataRequest.getDescricao().equals(dataEmEstoque.getValor())) {
					Integer quantidadeRequest = dataRequest.getQuantidade();
					Integer quantidadeData = dataEmEstoque.getQuantidade();
					Integer updateQuantidade = quantidadeData - quantidadeRequest;
					 
					dataEmEstoque.setQuantidade(updateQuantidade);
					estoqueDeEquipamentoRepository.save(dataEmEstoque);
					System.out.println("Estoque atualizado");
				}
				
			});
			 
		});    	
		    	
    }
	
	
	
	/**
 * Veririca se tem os equipamentos solicitados disponíveis em estoque. Realiza iteração da
 * lista do cliente e a lista do estoque. Dentro da iteração ele segue esse ordem: 
 * 1° Verifico se tem algum nome de equipamento igual entre as duas listas
 * 2º Se houver algum igual separo a quantidade de ambos em variáveis
 * 3° Verifico a quantidade entre elas, se caso a quantidade em estoque for menor que a 
 * quantidade solicitada retorna 'estoque insuficiente'	
 * @param equipamentosRequest
 * @return
 */
public boolean validacaoAoAtualizaEstoque(List<ReservaDeFluxoDeEquipamento> equipamentosRequest)	{
	return verificacaoDeEstoque(equipamentosRequest);
}



	/**
	 * Veririca se tem os equipamentos solicitados disponíveis em estoque. Realiza iteração da
	 * lista do cliente e a lista do estoque. Dentro da iteração ele segue esse ordem: 
	 * 1° Verifico se tem algum nome de equipamento igual entre as duas listas
	 * 2º Se houver algum igual separo a quantidade de ambos em variáveis
	 * 3° Verifico a quantidade entre elas, se caso a quantidade em estoque for menor que a 
	 * quantidade solicitada retorna 'estoque insuficiente'	
	 * @param equipamentosRequest
	 * @return
	 */
	public boolean verificacaoDeEstoque(List<ReservaDeFluxoDeEquipamento> equipamentosRequest)	{
		 
		List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();
		
		AtomicBoolean estoqueValido = new AtomicBoolean(true);
		
		equipamentosRequest.forEach(dataRequest -> {
					
			listData.forEach(dataEmEstoque -> {
				
				if(dataRequest.getDescricao().equals(dataEmEstoque.getValor())) {
					
					Integer quantidadeRequest = dataRequest.getQuantidade();
					Integer quantidadeData = dataEmEstoque.getQuantidade();
					
					if(quantidadeData < quantidadeRequest) {
						estoqueValido.set(false);
						// erro 
						throw new EstoqueInsuficienteException
						("Quantidade em estoque insuficiente para " + dataRequest.getDescricao() + 
								"\nCaso o equipamento foi devolvido, favor realizar"
								+ "a baixa e tentar novamente.");
					} else {
						estoqueValido.set(true);
						System.out.println("Quantidade de solicitação ao estoque validada !");
					}
					
				}
				
			});
			 
		});
		
		return estoqueValido.get();
	}
	
	
	
	/**
	 * Este método trabalha apenas com os serviços de criação de reserva MULTIPLA a ANUAL. O objetivo dele
	 * é evitar evitar mais verbosidade nos métodos de serviço para criação de reserva MULTIPLA e ANUAL. 
	 * Ele precisa acessar o objeto gerado na reserva de AgendaDTO e localiza a data de retirada do(s)
	 * equipamento(s), pega apenas o dia desse valor LocalDate e realiza a comparação se for igual ao dia
	 * de hoje, se sim então se realiza a atualização do estoque da lista passada dentro do parâmetro,
	 * 
	 * @param agendaDTO
	 * @param equipamentosRequest
	 */
	public void atualizaEstoqueMultiplaEAnual(AgendaDTO agendaDTO, List<ReservaDeFluxoDeEquipamento> equipamentosRequest) {
		atualizaEstoqueMultiplaOuAnual(agendaDTO, equipamentosRequest);
	}



	/**
	 * Este método trabalha apenas com os serviços de criação de reserva MULTIPLA a ANUAL. O objetivo dele
	 * é evitar evitar mais verbosidade nos métodos de serviço para criação de reserva MULTIPLA e ANUAL. 
	 * Ele precisa acessar o objeto gerado na reserva de AgendaDTO e localiza a data de retirada do(s)
	 * equipamento(s), pega apenas o dia desse valor LocalDate e realiza a comparação se for igual ao dia
	 * de hoje, se sim então se realiza a atualização do estoque da lista passada dentro do parâmetro,
	 * 
	 * @param agendaDTO
	 * @param equipamentosRequest
	 */
	public void atualizaEstoqueMultiplaOuAnual(AgendaDTO agendaDTO, List<ReservaDeFluxoDeEquipamento> equipamentosRequest) {
      LocalDate dataRetirada = agendaDTO.getDataRetirada();
      int diaDeHoje = dataRetirada.getDayOfMonth();
      
      if(diaDeHoje == day) {
      	atualizacaoDeEstoque(equipamentosRequest);
      }
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
