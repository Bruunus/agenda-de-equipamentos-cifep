package br.com.agenda.cifep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.agenda.cifep.dto.equipamentos.EstoqueEquipamentos;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import jakarta.annotation.PostConstruct;

@Component
public class InicializadorDeSistema {
	
	@Autowired
	EstoqueDeEquipamentoRepository estoqueEquipamentoRepository;	// esta incrementando automaticamente

	@PostConstruct
	public void init() {
//		System.out.println("Inicializado contexto Spring Boot!!!");
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
}
