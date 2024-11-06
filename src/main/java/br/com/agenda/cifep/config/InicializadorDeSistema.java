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
	        // Verifica se o equipamento já existe no banco de dados
	        EstoqueEquipamento estoqueEquipamento = estoqueDeEquipamentoRepository.findByDescricao(item.getDescricao());

	        // Se não existir, cria um novo registro
	        if (estoqueEquipamento == null) {
	            estoqueEquipamento = new EstoqueEquipamento();
	            estoqueEquipamento.setDescricao(item.getDescricao()); // Usando getDescricao()

	            // Define a quantidade com base no tipo de equipamento
	            switch (item) {
	                case DATASHOW:
	                    estoqueEquipamento.setQuantidade(4);
	                    break;
	                case NOTEBOOK:
	                    estoqueEquipamento.setQuantidade(5);
	                    break;
	                case LASER_POINTER:
	                    estoqueEquipamento.setQuantidade(1);
	                    break;
	                case CABO_HDMI:
	                    estoqueEquipamento.setQuantidade(0);
	                    break;
	                case EXTENSAO:
	                    estoqueEquipamento.setQuantidade(4);
	                    break;
	                case ADAPTADOR:
	                    estoqueEquipamento.setQuantidade(1);
	                    break;
	                case FLIP_CHART:
	                    estoqueEquipamento.setQuantidade(1);
	                    break;
	                case WEB_CAM:
	                    estoqueEquipamento.setQuantidade(3);
	                    break;
	                case PEN_DRIVE:
	                    estoqueEquipamento.setQuantidade(0);
	                    break;
	                case CABO_P2:
	                    estoqueEquipamento.setQuantidade(3);
	                    break;
	                case CABO_P10:
	                    estoqueEquipamento.setQuantidade(4);
	                    break;
	                case MICROFONE:
	                    estoqueEquipamento.setQuantidade(3);
	                    break;
	                default:
	                    throw new IllegalArgumentException("Unexpected value: " + item);
	            }

	            // Salva o novo equipamento no banco de dados
	            estoqueDeEquipamentoRepository.save(estoqueEquipamento);
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
