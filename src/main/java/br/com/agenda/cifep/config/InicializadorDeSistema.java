package br.com.agenda.cifep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.agenda.cifep.dto.TiposEquipamentos;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.EstoqueDeEquipamentoRepository;
import jakarta.annotation.PostConstruct;

@Component
public class InicializadorDeSistema {
	
	@Autowired
	EstoqueDeEquipamentoRepository estoqueEquipamentoRepository;

	@PostConstruct
	public void init() {
//		System.out.println("Inicializado contexto Spring Boot!!!");
		for (TiposEquipamentos item : TiposEquipamentos.values()) {
			 
			EstoqueEquipamento estoqueEquipamento = 
					estoqueEquipamentoRepository.findByDescricao(item.name());
			
			if (estoqueEquipamento == null) {
                // Item não encontrado, realiza o insert
                estoqueEquipamento = new EstoqueEquipamento();
                estoqueEquipamento.setDescricao(item.name());
                
                switch (item) {
				case DATASHOW : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case NOTEBOOK : {
					estoqueEquipamento.setQuantidade(5);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case LASER_POINTER : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case CABO_HDMI : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case EXTENSAO : {
					estoqueEquipamento.setQuantidade(4);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case ADAPTADOR : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case FLIP_CHART : {
					estoqueEquipamento.setQuantidade(1);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case WEB_CAM : {
					estoqueEquipamento.setQuantidade(3);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				case PEN_DRIVE : {
					estoqueEquipamento.setQuantidade(0);
					estoqueEquipamentoRepository.save(estoqueEquipamento);
					break;					 
				}
				
				default:
					throw new IllegalArgumentException("Unexpected value: " + item);
				}
                
                
            }
		}
	}
}
