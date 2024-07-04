package br.com.agenda.cifep.service.estoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.reserva.AgendaDTO;
import br.com.agenda.cifep.dto.reserva.RadarDeReservasAgendadasDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.model.ReservaDeFluxoDeEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import br.com.agenda.cifep.service.error.EstoqueInsuficienteException;


/**
 * @author Bruno Fernandes dos Santos
 */
@Service
public class UpdateEstoqueService {
	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	
	@Autowired
 
	
	@SuppressWarnings("unused")
	private static int day = LocalDate.now().getDayOfMonth();
	
	
	
	/**
	 * Este funcionalidade realiza a função de subtrair o equipamento solicitado na reserva do 
	 * estoque, porém é somente para o dia vigente, o dia de hoje.
	 * 
	 * 
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
	public void atualizacaoDeEstoque(List<ReservaDeFluxoDeEquipamento> equipamentosRequest) {		
		
		List<EstoqueEquipamento> estoqueData = estoqueDeEquipamentoRepository.findAll();
		
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
	
	
	
//	public void atualizacaoDeEstoque(List<ReservaDeFluxoDeEquipamento> equipamentosRequest) {		
//		
//		List<EstoqueEquipamento> estoqueData = estoqueDeEquipamentoRepository.findAll();
//		LocalDate now = LocalDate.now();
//		
//		equipamentosRequest.forEach(dataRequest -> {
//			
//			estoqueData.forEach(dataEmEstoque -> {
//				
//				if(dataRequest.getDescricao().equals(dataEmEstoque.getValor())) {
//					Integer quantidadeRequest = dataRequest.getQuantidade();
//					Integer quantidadeData = dataEmEstoque.getQuantidade();
//					Integer updateQuantidade = quantidadeData - quantidadeRequest;
//					 
//					dataEmEstoque.setQuantidade(updateQuantidade);
//					estoqueDeEquipamentoRepository.save(dataEmEstoque);
//					System.out.println("Estoque atualizado");
//				}
//				
//			});
//			 
//		});    	
//		    	
//    }
//	
	
	
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
	 * Verifica se tem os devidos equipamentos solicitados disponíveis em estoque pelo parâmetro. 
	 * Realiza iteração da lista do cliente e a lista do estoque. Dentro da iteração ele segue esse ordem: 
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
	
	
 
//	private boolean monitoradorDeEstoqueParaReservasPosteriores() {		
//		
//		final AtomicInteger calculo_soma_quantidade_do_dia = new AtomicInteger(0);
//		final AtomicInteger calculo_estoque_do_dia_disponivel = new AtomicInteger(0);
//		AtomicBoolean estoqueValido = new AtomicBoolean(true);
//		
//		
////		>	Traga todas as reservas agendadas para este dia especifico
//		LocalDate now = LocalDate.now();
//		
//		
//		List<Object[]> quantidadeDisponivelDoDia = estoqueDeEquipamentoRepository.getQuantidadeDisponivelDoDia(now);
//		List<EstoqueQuantidadeDTO> estoqueData = estoqueDeEquipamentoRepository.getEstoqueQuantidades();
//		
////		quantidadeDisponivelDoDia.forEach(item -> {
////			Integer colunaQuantidade = (Integer) item[1];
////			calculo_soma_quantidade_do_dia.addAndGet(colunaQuantidade);			
////			String descricao = (String) item[0];
////			Integer quantidade = Integer.parseInt(item[1].toString());
////			
////			java.sql.Date dataRetiradaSQL = (java.sql.Date) item[2];	// convertendo os dados da coluna do banco de dados 
////			LocalDate dataRetirada = dataRetiradaSQL.toLocalDate();		// para ser reconhecido no Java
////			
////			System.out.println("Descrição: "+descricao+
////					"\tQuantidade: "+quantidade+
////					"\tData de retirada: "+dataRetirada);
////		});
//		
//		
//			System.out.println("Resposta do estoque: ");
//			estoqueData.forEach(estoque -> {
//			
//			quantidadeDisponivelDoDia.forEach(itensDasReservas -> {				
//				 
//				if(itensDasReservas[0].equals(estoque.getValor())) {
//					
//					Integer quantidadeItensReserva = Integer.parseInt(itensDasReservas[1].toString());	// quando voce olha no caderno
//					Integer quantidadeEmEstoque = estoque.getQuantidade();		// quando voce olha no armario
//					if(quantidadeItensReserva > quantidadeEmEstoque) {
//						System.out.println("Os itens que foram solicitados para a data "+now+" que ultrapassa o limite do estoque são:");
//						System.out.println("Descricão: "+itensDasReservas[0]+"\tQuantidade solicitada: "+itensDasReservas[1]
//								+"(total de todas as reservas do dia)");
//						System.out.println("Porém para : "+estoque.getValor()+"\t a quantidade disponível é de: "+estoque.getQuantidade());
//					}
//					
//					
//					 
//					
////					System.out.print("Existem elementos iguais -->>>");
////					System.out.println("Qtd. do dia: "+itensDasReservas[0]+"\tEstoque: "+estoque.getValor());
//				} 
//				
//			});			
//			
//		});
//		
//		
////		System.out.println("Calculo soma de quantidade: "+calculo_soma_quantidade);
////		System.out.println("***Estoque disponível***");
//		estoqueData.forEach(item -> {
//			String descricao = item.getValor();
//			Integer quantidade = item.getQuantidade();
////			System.out.println("Descrição: "+descricao+"\tQuantidade: "+quantidade);
//			
////			fazer a iteração dentre os dois
//			
//			
//			
//		});
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		Toda a vez que voce criar uma reserva o sistema deve fazer uma busca no banco de dados para saber se 
//		aquele equipamento para aquela data e aquele
//		horário solicitado ja tem criado, porém é preciso levar em consideração a quantidade de equipamento 
//		porque se tiver mais que um no dia em estoque
//		então pode ser criada.
//
//		Resolução:
//
//			Vai até a tabela de reservas e:
//			

//				>	Traga também todos os equipamentos e quantidade para este dia específico
//				>	Pegue toda a equantidade de cada equipamento reservado e se os equipamentos forem iguais some-os.
//				>	Com cada equipamentos somado compare o total dessa quantidade com a quantidade em estoque
//
//
//			Basicamente a comparação de estoque se resume em pegar todas as reservas agendadas para o dia específico e subtrair pela quantidade de
//			estoque disponível, pois o estoque só contabiliza por dia.
//		
//	}
	
	
	
	private boolean monitoradorDeEstoqueParaReservasPosteriores(List<LocalDate> datas) {
		  
		AtomicBoolean estoqueValido = new AtomicBoolean(true);
//		>	Traga todas as reservas agendadas para este dia especifico
//		LocalDate now = LocalDate.now();
		
//		List<LocalDate> datasSolicitadas = new ArrayList<>();
//		datasSolicitadas.add(LocalDate.of(2024, 07, 04));
//		datasSolicitadas.add(LocalDate.of(2024, 07, 05));
//		datasSolicitadas.add(LocalDate.of(2024, 07, 06));
		
		
		
		List<RadarDeReservasAgendadasDTO> quantidadeDisponivelDoDia = 
				estoqueDeEquipamentoRepository.getQuantidadeDisponivelPorData(datas);
		List<EstoqueQuantidadeDTO> estoqueAgendado = estoqueDeEquipamentoRepository.getEstoqueQuantidades();
		
		preCalculoDeEstoque();
		
		Map<String, Integer> radarEstoqueEstourado = new HashMap<>();	// mapas de chaves e valores		 
		
		
		
//		LÓGICA PERFEIRA E PRONTA ! NÃO MEXER
		
//		quantidadeDisponivelDoDia.forEach(item -> {
//			Integer colunaQuantidade = (Integer) item[1];
//			calculo_soma_quantidade_do_dia.addAndGet(colunaQuantidade);			
//			String descricao = (String) item[0];
//			Integer quantidade = Integer.parseInt(item[1].toString());
//			
//			java.sql.Date dataRetiradaSQL = (java.sql.Date) item[2];	// convertendo os dados da coluna do banco de dados 
//			LocalDate dataRetirada = dataRetiradaSQL.toLocalDate();		// para ser reconhecido no Java
//			
//			System.out.println("Descrição: "+descricao+
//					"\tQuantidade: "+quantidade+
//					"\tData de retirada: "+dataRetirada);
//		});
		
		
//			System.out.println("Resposta do estoque: ");
		
		
		
			estoqueAgendado.forEach(estoque -> {				

			quantidadeDisponivelDoDia.forEach(itensDasReservas -> {				
				
				 
				if(itensDasReservas.getDescricao().equals(estoque.getValor())) {
					
					Integer quantidadeItensReserva = Integer.parseInt(itensDasReservas.getQuantidadeEquipamento().toString());	// quando voce olha no caderno
					Integer quantidadeEmEstoque = estoque.getQuantidade();		// quando voce olha no armario
					if(quantidadeItensReserva > quantidadeEmEstoque) {
						System.out.println("***Quantidade que estouraram o limite do estoque são:");
						System.out.println("Descricão: "+itensDasReservas.getDescricao()+
								"\tQuantidade solicitada: "+itensDasReservas.getQuantidadeEquipamento()
								+"(total de todas as reservas do dia)");
						System.out.println("Porém para : "+estoque.getValor()+"\t a quantidade disponível é de: "+estoque.getQuantidade());
						estoqueValido.set(false);
						throw new EstoqueInsuficienteException
						("Quantidade em estoque insuficiente para " + itensDasReservas.getDescricao() + 
								"\nCaso o equipamento foi devolvido, favor realizar"
								+ "a baixa e tentar novamente.");
					} else {
						System.out.println("Estoque de equipamento validado !");
						estoqueValido.set(true);
					}
//					System.out.print("Existem elementos iguais -->>>");
//					System.out.println("Qtd. do dia: "+itensDasReservas[0]+"\tEstoque: "+estoque.getValor());
				} 
				
			});			
			
		});		
			
			
			if(!radarEstoqueEstourado.isEmpty()) {
				System.out.println("***Quantidade que estouraram o limite do estoque são:");
				for (Map.Entry<String, Integer> entry : radarEstoqueEstourado.entrySet()) {
			        String descricao = entry.getKey();
			        Integer quantidade = entry.getValue();
			        System.out.println("Descrição: " + descricao + "\tQuantidade: " + quantidade);
				}
			}			
			
//		System.out.println("Calculo soma de quantidade: "+calculo_soma_quantidade);
//		System.out.println("***Estoque disponível***");
//		estoqueData.forEach(item -> {
//			String descricao = item.getValor();
//			Integer quantidade = item.getQuantidade();
//			System.out.println("Descrição: "+descricao+"\tQuantidade: "+quantidade);			
//		});
			
			return estoqueValido.get();
		
		}
	
	
	 


	private List<RadarDeReservasAgendadasDTO> preCalculoDeEstoque() {
		
		final AtomicInteger calculo_soma_quantidade = new AtomicInteger(0);
		
		List<LocalDate> datas = new ArrayList<>();
		datas.add(LocalDate.of(2024, 07, 04));
		datas.add(LocalDate.of(2024, 07, 05));
		datas.add(LocalDate.of(2024, 07, 06));
		
		
		List<RadarDeReservasAgendadasDTO> quantidadeDisponivelDoDia = 
				estoqueDeEquipamentoRepository.getDescricaoPorData(datas);
		
		quantidadeDisponivelDoDia.forEach(item -> {
			System.out.println("AS DATAS SÃO: "+item);
		});
		
		
		// aqui eu vou somar a quantidade de cada item de acordo com a data agendada
		
		return quantidadeDisponivelDoDia;
	}
	

	

	/**
	 * Getter público do método monitoradorDeEstoqueParaReservasPosteriores
	 */
	public void getMonitoradorDeEstoqueParaReservasPosteriores(List<LocalDate> datas) {
		monitoradorDeEstoqueParaReservasPosteriores(datas);
	}


}


//SPRINT HIPER IMPORTANTE


//Outro desafio:
//
//Quando um prazo de devolução não é cumprido e, existe reservas que vão precisar dessa quantidade faltante para o dia, então o sistema deve
//alertar que existe reservas pendente de devolução e que precisam ser resgatadas
//
//	Emita um alerta na pagian home mostrando um caminho para ver com mais detalhes.
