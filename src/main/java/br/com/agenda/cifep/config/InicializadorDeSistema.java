package br.com.agenda.cifep.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EstoqueEquipamentos;
import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.reserva.RadarDeReservasAgendadasDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import br.com.agenda.cifep.service.estoque.UpdateEstoqueService;
import jakarta.annotation.PostConstruct;

@Service
public class InicializadorDeSistema {
	
	@Autowired
	EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;	// esta incrementando automaticamente
	@Autowired
	UpdateEstoqueService updateEstoqueService;

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
		
//		List<LocalDate> datas = new ArrayList<>();
//       datas.add(LocalDate.of(2024, 07, 04));
//       datas.add(LocalDate.of(2024, 07, 05));
//       datas.add(LocalDate.of(2024, 07, 06));
       
  
       
		
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
