package br.com.agenda.cifep.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueEquipamentos;
import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.reserva.RadarDeReservasAgendadasDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import jakarta.annotation.PostConstruct;

@Service
public class InicializadorDeSistema {
	
	@Autowired
	EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;	// esta incrementando automaticamente

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
		
		
		// eu preciso de um pré-calculo de estoque (estoque-agendado)!
		//POrque o novo mecanismo vai carregar o estoque disponível todo dia toda as 06:30 
//		com base nesse estoque-agendado
		
		
//		TESTE DE FUNCIONALIDADE PARA O ESTOQUE
		
		
//		/*
//		 * Esse exemplo deu certo porém tome cuidado porque a data se repete na lista quando tem outro equipamento
//		 */
//		
//		/*
//		 * A impressão sai assim 
//		 2024-07-06	NOTEBOOK	7
//		 2024-07-06	DATASHOW	1
//		 2024-07-05	NOTEBOOK	1
//		 2024-07-04	DATASHOW	2
//		 2024-07-05	DATASHOW	1
//		 */ 
//		
//		
//		
//		List<RadarDeReservasAgendadasDTO> totalDisponivel = new ArrayList<>();
//
//        // Dados da request service
//        List<LocalDate> datas = new ArrayList<>();
//        datas.add(LocalDate.of(2024, 07, 04));
//        datas.add(LocalDate.of(2024, 07, 05));
//        datas.add(LocalDate.of(2024, 07, 06));
//
//        Map<String, Integer> somaQuantidades = new HashMap<>(); // Mapa para armazenar as somas de quantidade por data e descrição
//
//        List<RadarDeReservasAgendadasDTO> quantidadeDisponivelDoDia = estoqueDeEquipamentoRepository.getQuantidadeDisponivelPorData(datas);
//
//        for (RadarDeReservasAgendadasDTO qtd : quantidadeDisponivelDoDia) {
//            LocalDate dataItem = qtd.getDataRetirada();
//            String descricaoItem = qtd.getDescricao();
//            int quantidadeItem = qtd.getQuantidadeEquipamento();
//
//            String chave = dataItem.toString() + "-" + descricaoItem; // Criação da chave para o mapa
//
//            if (somaQuantidades.containsKey(chave)) {
//                int somaQuantidade = somaQuantidades.get(chave);
//                somaQuantidades.put(chave, somaQuantidade + quantidadeItem);
//            } else {
//                somaQuantidades.put(chave, quantidadeItem);
//            }
//        }
//
//        for (Map.Entry<String, Integer> entry : somaQuantidades.entrySet()) {
//            String chave = entry.getKey();
//            int somaQuantidade = entry.getValue();
//            LocalDate data = LocalDate.parse(chave.substring(0, 10));
//            String descricao = chave.substring(11);
//
//            totalDisponivel.add(new RadarDeReservasAgendadasDTO(data, descricao, somaQuantidade));
//        }
//
//        // Impressão dos resultados
//        for (RadarDeReservasAgendadasDTO total : totalDisponivel) {
//            System.out.println(total.getDataRetirada() + "\t" + total.getDescricao() + "\t" + total.getSomaQuantidade());
//        }
		
		
		
		
		
		
		
		
		
		
		
		
//		Deu quase certo
//		List<RadarDeReservasAgendadasDTO> totalDisponivel = new ArrayList<>();
//		
////		dados da request service
//		List<LocalDate> datas = new ArrayList<>();
//		datas.add(LocalDate.of(2024, 07, 04));
//		datas.add(LocalDate.of(2024, 07, 05));
//		datas.add(LocalDate.of(2024, 07, 06));
//		
//		
//		List<String> descricaoTotais = new ArrayList<>();
//		
//		LocalDate dataAtual = null;
//		String descricaoAtual = null;
//		int somaQuantidade = 0;
//		
//		
//		List<RadarDeReservasAgendadasDTO> quantidadeDisponivelDoDia = 
//				estoqueDeEquipamentoRepository.getQuantidadeDisponivelPorData(datas);
//		
//		
//		for(RadarDeReservasAgendadasDTO qtd : quantidadeDisponivelDoDia) {
//			LocalDate dataItem = qtd.getDataRetirada();
//			String descricaoItem = qtd.getDescricao();			
//			int quantidadeItem = qtd.getQuantidadeEquipamento();
//			
//			if (dataAtual == null || !dataAtual.equals(dataItem)) {
//				
//				if (descricaoAtual != null) {
//                    totalDisponivel.add(new RadarDeReservasAgendadasDTO(dataAtual, descricaoAtual, somaQuantidade));
//                }
//				
//				dataAtual = dataItem;	
//				descricaoAtual = descricaoItem;
//				somaQuantidade = quantidadeItem;
//		        
//			} else if (descricaoAtual.equals(descricaoItem)) {
//				somaQuantidade += quantidadeItem;
//		    }
//			
//		}
//		
//		if (descricaoAtual != null) {
//            totalDisponivel.add(new RadarDeReservasAgendadasDTO(dataAtual, descricaoAtual, somaQuantidade));
//        }
//		
//		
//		// Adicionar o último total à lista
//		if (descricaoAtual != null) {
//		    descricaoTotais.add("Data: " + dataAtual + ", Descrição: " + descricaoAtual 
//		    		+ ", Total: " + somaQuantidade);
//		}
//		
//		 
//
////		Aqui pe a chave: aqui devolve toda a lista com os equipamentos totais somados de acordo com cada data passada.
//		for (RadarDeReservasAgendadasDTO total : totalDisponivel) {
//
//		    System.out.println(
//		    		total.getDataRetirada()+"\t"+
//		    		total.getDescricao()+"\t"+
//		    		total.getSomaQuantidade()
//		    		);
//		    
//		}
//		
		
//		***********************************************************
		
		
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		final AtomicInteger calculo_soma_quantidade = new AtomicInteger(0);
//		
//		List<LocalDate> datas = new ArrayList<>();
//		datas.add(LocalDate.of(2024, 07, 04));
//		datas.add(LocalDate.of(2024, 07, 05));
//		datas.add(LocalDate.of(2024, 07, 06));
//		
//		
//		List<RadarDeReservasAgendadasDTO> quantidadeDisponivelDoDia = 
//				estoqueDeEquipamentoRepository.getQuantidadeDisponivelPorData(datas);
//		
//		
//		EstoqueEquipamentos equipamentosDescricao = null;
//		
//		for(LocalDate data: datas) {
//			 System.out.println("Para data " + data);
//			 
//			 for(RadarDeReservasAgendadasDTO descricao: quantidadeDisponivelDoDia) {
//				 if(descricao.getDataRetirada().equals(data)) {
//					 System.out.println("Descrição: " + descricao.getDescricao() 
//					 + "\tQUANTIDADE: " + descricao.getQuantidadeEquipamento());
//					 
//					 String descricaoItem = descricao.getDescricao();
//					 int quantidade = descricao.getQuantidadeEquipamento();
//					 
//					 
//					 
//				 }
//			 }
//			 
//		}
//		
//		System.out.println("Equipamento do item: "+ equipamentosDescricao);
//		System.out.println("SOMA DA QUANTIDADE PARA NOTEBOOK: " + calculo_soma_quantidade.get());
//		
		
		
		
		
//		quantidadeDisponivelDoDia.forEach(item -> {
//			calculo_soma_quantidade.addAndGet(item.getQuantidadeEquipamento());
//			System.out.println("AS DATAS SÃO: "+item.getDescricao()+"\tQUANTIDADE: "+item.getQuantidadeEquipamento());
//		});
//		System.out.println("Soma final do cálculo "+calculo_soma_quantidade);
		
		
		/*
		 
		O resultado está vindo assim:  
		 
	 	AS DATAS SÃO: NOTEBOOK	QUANTIDADE: 1
		AS DATAS SÃO: DATASHOW	QUANTIDADE: 6
		AS DATAS SÃO: NOTEBOOK	QUANTIDADE: 1
		AS DATAS SÃO: DATASHOW	QUANTIDADE: 1
		AS DATAS SÃO: NOTEBOOK	QUANTIDADE: 7
		AS DATAS SÃO: DATASHOW	QUANTIDADE: 1
		AS DATAS SÃO: DATASHOW	QUANTIDADE: 1
		AS DATAS SÃO: DATASHOW	QUANTIDADE: 1
		Soma final do cálculo 19
		
		Preciso organizar por data e, cada data fazer o seu forech e para cada foreach de uma data
		eu procuro se tem algum equipamento pela descrição igual e se tiver eu pego a sua quantidade.
		
		 
		 
		 
		 
		  */
		
	}
	
	
	 

	 
	 


 



























	
	
	
	/**
	 * Esta funcionalidade é para recriar os dados e valores da tabela de estoque de equipamentos.
	 * Para ela ser validada é necessário deletar os registros confusos existentes. Após deletar
	 * os registros execute o método.
	 */
	private void resetEstoque() {
		
		for (EstoqueEquipamentos item : EstoqueEquipamentos.values()) {
			 
			EstoqueEquipamento estoqueEquipamento = 
					estoqueDeEquipamentoRepository.findByValor(item.name());
			
			if (estoqueEquipamento == null) {
                // Item não encontrado, realiza o insert
                estoqueEquipamento = new EstoqueEquipamento();
                estoqueEquipamento.setValor(item.name());
                System.out.println("");
                switch (item) {
				case DATASHOW : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Datashow");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case NOTEBOOK : {
					estoqueEquipamento.setQuantidade(5);
					estoqueEquipamento.setDescricao("Notebook");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case LASER_POINTER : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Lazer Pointer");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case CABO_HDMI : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamento.setDescricao("Cabo HDMI");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case EXTENSAO : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Extensão");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case ADAPTADOR : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Adaptador");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case FLIP_CHART : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamento.setDescricao("Flip Chart");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case WEB_CAM : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Webcam");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case PEN_DRIVE : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamento.setDescricao("Pendrive");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case CABO_P2 : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Cabo P2");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case CABO_P10 : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamento.setDescricao("Cabo P10");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				case MICROFONE : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamento.setDescricao("Microfone");
					estoqueDeEquipamentoRepository.save(estoqueEquipamento);
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
		estoqueDeEquipamentoRepository.deleteAll();
		resetEstoque();
	}
	
	
}
