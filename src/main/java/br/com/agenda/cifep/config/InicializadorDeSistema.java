package br.com.agenda.cifep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueEquipamentos;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import jakarta.annotation.PostConstruct;

@Service
public class InicializadorDeSistema {
	
	@Autowired
	EstoqueDeEquipamentoRepository estoqueEquipamentoRepository;	// esta incrementando automaticamente

	/**
	 * O método init irá executar todas as tarefas antes da inicialização final do sistema.
	 * As tarefas:
	 * 		@resetEstoque() - Método fica disponível para recriar a tabela em modo de dev. Basta deletar
	 * os registros direto no banco de dados e inicializar o sistema.
	 * 
	 * 		@atualizadorDeReservasDoDiaAtual() - Esta funcionalidade organiza o estoque antes de ser
	 * renderizado para o cliente, carregando as reservas agendadas e atualizando o estoque.
	 */
	@PostConstruct
	public void init() {
//		System.out.println("Inicializado contexto Spring Boot!!!");
		resetEstoque();
		atualizadorDeReservasAgendadasDoEstoque();
		
	}
	
	
	 

	/**
	 * A sua função principal é pegar as reservas agendadas para o dia letivo e 
	 * subtrair o estoque com a sua quantidade para que ele fique atualizado em tempo de dev 
	 * apenas
	 * 
	 * 
	 *	Então para que ela rode sempre voce precisa fazer um do while para repetir o agendamento
	 *	a cada 24 horas ás 07:00 da manhã
	 */
	private void atualizadorDeReservasAgendadasDoEstoque() {
		
		
		
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
//				>	Traga todas as reservas agendadas para este dia especifico
//				>	Traga também todos os equipamentos e quantidade para este dia específico
//				>	Pegue toda a equantidade de cada equipamento reservado e se os equipamentos forem iguais some-os.
//				>	Com cada equipamentos somado compare o total dessa quantidade com a quantidade em estoque
//
//
//			Basicamente a comparação de estoque se resume em pegar todas as reservas agendadas para o dia específico e subtrair pela quantidade de
//			estoque disponível, pois o estoque só contabiliza por dia.
		
	}
	
	
	/**
	 * Esta funcionalidade é para recriar os dados e valores da tabela de estoque de equipamentos.
	 * Para ela ser validada é necessário deletar os registros confusos existentes. Após deletar
	 * os registros execute o método.
	 */
	private void resetEstoque() {
		
		for (EstoqueEquipamentos item : EstoqueEquipamentos.values()) {
			 
			EstoqueEquipamento estoqueEquipamento = 
					estoqueEquipamentoRepository.findByValor(item.name());
			
			if (estoqueEquipamento == null) {
                // Item não encontrado, realiza o insert
                estoqueEquipamento = new EstoqueEquipamento();
                estoqueEquipamento.setValor(item.name());
                System.out.println("");
                switch (item) {
				case DATASHOW : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Datashow");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case NOTEBOOK : {
					estoqueEquipamento.setQuantidade(5);
					estoqueEquipamento.setDescricao("Notebook");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case LASER_POINTER : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Lazer Pointer");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case CABO_HDMI : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamento.setDescricao("Cabo HDMI");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case EXTENSAO : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Extensão");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case ADAPTADOR : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Adaptador");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case FLIP_CHART : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Flip Chart");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case WEB_CAM : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Webcam");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case PEN_DRIVE : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamento.setDescricao("Pendrive");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case CABO_P2 : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Cabo P2");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case CABO_P10 : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Cabo P10");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case MICROFONE : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Microfone");
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				 
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + item);
				}
                
                
            }
		}
		
	}
	
	
	/**
	 * Método auxiliar de resetEstoque() precisa ser executado antes de resetEstoque()
	 * para deletar os registros da tabela estoque-de-equipamento
	 */
	public void resetEstoqueClient() {
		estoqueEquipamentoRepository.deleteAll();
		resetEstoque();
	}
	
	
}
