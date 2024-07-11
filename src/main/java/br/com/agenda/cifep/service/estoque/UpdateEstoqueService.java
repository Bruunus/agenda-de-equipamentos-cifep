package br.com.agenda.cifep.service.estoque;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
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
	public static List<RadarDeReservasAgendadasDTO> reservasInsuficientes = new ArrayList<>();
	
	
	
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
	
	
 
	
	
	
	/**
	 * Este metodo realiza manipulação no banco de dados de acordo com as datas enviadas na requisção pelo cliente. Esta lista de datas
	 * é usada como parâmetro para trazer todas as reservas criadas e ATIVAS. No recebimento das reservas realizamos iterações para 
	 * investigar a quantidade de equipamento agendado para cada data, uma das funcionalidades mais essenciais é que ao iterar o sistema
	 * observa se a descrição do equipamento irá se repetir, se repetir ele entende que tem mais de uma reserva com o mesmo equipamento 
	 * então ele soma todos eles nos gerando um valor total disponível para o dia.
	 * 
	 * No bloco seguinte analizamos junto ao estoque para termos uma base de quantidade padrão de empréstimo por dia, se o valor total 
	 * somando de cada equipamento de acordo com cada data for maior que a quantidade padrão do estoque é lançada um exception e retorna
	 * a lista dessas datas solicitadas pelo estoque para o cliente informando as datas que não foram aceitas.ERRADO CORRIGINDO --->>>
	 * 
	 * 
	 * Este método receba uma lista de datas e tráz todas as reservas ja criadas pelas datas, o método retorna uma lista com as reservas
	 * das datas solicitadas e o cliente analisa de acordo com o que ele quer, se uma data é passada e não retorna nenhuma reserva nela
	 * é porque não existe.
	 * 
	 * @param datas
	 * @return boolean
	 */
	private List<RadarDeReservasAgendadasDTO> monitoradorDeEstoqueParaReservasPosteriores(List<LocalDate> datas) {
		  
//		AtomicBoolean equipamentoDisponivel = new AtomicBoolean(true);
//		boolean equipamentoDisponivel = false;
		Map<String, Integer> somaQuantidades = new HashMap<>();
		
		LocalDate dataItem = null;
		LocalTime horaRetirada = null;
		LocalDate dataDevolucao = null;
		LocalTime horaDevolucao = null;		
		
		List<RadarDeReservasAgendadasDTO> totalSolicitado = new ArrayList<>();
		
//		Trago todas as reservas existentes pelas datas passadas
		List<RadarDeReservasAgendadasDTO> quantidadeReservadaDoDia = 
				estoqueDeEquipamentoRepository.getQuantidadeReservadaPorData(datas);
		
//		System.out.println(
//				"\nDESCRIÇAO\tQUANTIDADE\tID\tNOME\tSETOR\tDATA RETIRADA\tHORA RETIRADA\tDATA DEVOLUÇÃO\tHORA DEVOLUÇÃO");
		for (RadarDeReservasAgendadasDTO qtdDisponivel: quantidadeReservadaDoDia) {	
//			{Debug}
//			System.out.println(
//					qtdDisponivel.getDescricao()+"\t"+
//					qtdDisponivel.getQuantidadeEquipamento()+"\t"+
//					qtdDisponivel.getId()+"\t"+
//					qtdDisponivel.getNome()+"\t"+
//					qtdDisponivel.getSetor()+"\t"+
//					qtdDisponivel.getDataRetirada()+"\t"+
//					qtdDisponivel.getHoraRetirada()+"\t"+
//					qtdDisponivel.getDataDevolucao()+"\t"+
//					qtdDisponivel.getHoraDevolucao() 
//					);
			
			dataItem = qtdDisponivel.getDataRetirada();
			horaRetirada = qtdDisponivel.getHoraRetirada();
			dataDevolucao = qtdDisponivel.getDataDevolucao();
			horaDevolucao = qtdDisponivel.getHoraDevolucao();
            String descricaoItem = qtdDisponivel.getDescricao();
            int quantidadeItem = qtdDisponivel.getQuantidadeEquipamento();

            String chave = dataItem.toString() + "-" + descricaoItem;
			 
            // se as cjaves forem iguais
            if (somaQuantidades.containsKey(chave)) {
//            	então eu somo a quantidade
                int somaQuantidade = somaQuantidades.get(chave);
                somaQuantidades.put(chave, somaQuantidade + quantidadeItem);
            } else {
//            	senão crio uma nova posição, uma nova chave
                somaQuantidades.put(chave, quantidadeItem);
            }			
		} 
		
		// itero sobre o mapa de armazeno cada chave e converto cada dado ao seu tipo para poder adicionar na lista
		for (Map.Entry<String, Integer> entry : somaQuantidades.entrySet()) {
            String chave = entry.getKey();
            int somaQuantidade = entry.getValue();
            LocalDate data = LocalDate.parse(chave.substring(0, 10));
            String descricao = chave.substring(11);            

            totalSolicitado.add(new RadarDeReservasAgendadasDTO(data, horaRetirada, dataDevolucao, horaDevolucao,  descricao, somaQuantidade));
        }				
		
//		{Debug}	
		System.out.println("Reservas agendadas do dia(s) "+dataItem);
        for (RadarDeReservasAgendadasDTO total : totalSolicitado) {
            System.out.println(
        		total.getDataRetirada()+"\t"+
        		total.getHoraRetirada()+"\t"+
        		total.getDataDevolucao()+"\t"+ 
        		total.getHoraDevolucao()+"\t"+
        		total.getDescricao()+"\t"+
        		total.getSomaQuantidade()
    		);
        }
        
        if(totalSolicitado.isEmpty()) {
        	System.out.println("A lista está vazia");
        }
        
        validacaoDoEstoque(totalSolicitado);
               
    	return totalSolicitado;        
        		
	}
	
	
	
	
	/**
	 * Método de validação de quantidade limite no estoque 
	 * @param reservasAgendadas
	 */
	private void validacaoDoEstoque(List<RadarDeReservasAgendadasDTO> reservasAgendadas) {
		List<EstoqueQuantidadeDTO> estoque = estoqueDeEquipamentoRepository.getEstoqueQuantidades();
		List<RadarDeReservasAgendadasDTO> reservasInsuficiente = new ArrayList<>();
		
		for (RadarDeReservasAgendadasDTO reservas: reservasAgendadas) {
			for (EstoqueQuantidadeDTO itemEstoque: estoque) {
				
				if(reservas.getDescricao().equals(itemEstoque.getValor())) {
					Integer qtdReserva = reservas.getSomaQuantidade();
        			Integer qtdEstoque = itemEstoque.getQuantidade();
        			
        			if(qtdEstoque < qtdReserva || qtdEstoque == qtdReserva) {
        				reservasInsuficiente.add(
        						new RadarDeReservasAgendadasDTO(reservas.getDescricao(), reservas.getSomaQuantidade(), reservas.getDataRetirada())
        						);
        			} 
				}				
			}
		}
		
//		{Debug}
//		System.out.println("Reservas insuficientes");
//		for (RadarDeReservasAgendadasDTO itensInsuficientes: reservasInsuficiente) {
////			if(itensInsuficientes.getDescricao())
////			System.out.println(itensInsuficientes);
//		}
		
		if(!reservasInsuficiente.isEmpty()) {
			System.out.println("A lista não está vazia então será lançada uma throw com os itens");
			throw new EstoqueInsuficienteException("EstoqueInsuficienteExeception: Estoque indisponível.", reservasInsuficiente);			 
		}		
		
	}
	
	





	
	

	/**
	 * Getter público do método monitoradorDeEstoqueParaReservasPosteriores
	 */
	public List<RadarDeReservasAgendadasDTO> getEstoqueDisponivelDeAgenda(List<LocalDate> datas) {
		return monitoradorDeEstoqueParaReservasPosteriores(datas);
	}


	/**
	 * Invocado no controller para poder retornar o agendamento insuficiente
	 * @return
	 */
	public static List<RadarDeReservasAgendadasDTO> getReservasInsuficientes() {		
	 
		return reservasInsuficientes;
	}


}


//SPRINT HIPER IMPORTANTE


//Outro desafio:
//
//Quando um prazo de devolução não é cumprido e, existe reservas que vão precisar dessa quantidade faltante para o dia, então o sistema deve
//alertar que existe reservas pendente de devolução e que precisam ser resgatadas
//
//	Emita um alerta na pagian home mostrando um caminho para ver com mais detalhes.
