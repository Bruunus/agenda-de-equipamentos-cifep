package br.com.agenda.cifep.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Equipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.repository.EquipamentoRepository;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class ReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	

	/**
	 * Atribui cada ítem do objeto JSON à tabela do banco até chegar no ítem de 
	 * list que é a lista de equipamentos. Para a lista de equipamento realizamos 
	 * a conversão de tipos de EquipamentoDTO para a tabela Equipamento, por fim 
	 * adicionamos todos os atributos dentro da lista equipamentosList, e por fim 
	 * salvamos os ítens nas duas tabelas.
	 * 	         
	 * @param reservaDTO
	 * @return
	 */
	public Boolean novaReserva(ReservaDTO reservaDTO) {
	    if (reservaDTO.getSetor() == null || reservaDTO.getSetor().isEmpty() ||
	        reservaDTO.getResponsavel() == null || reservaDTO.getResponsavel().isEmpty() ||
	        reservaDTO.getEquipamentos() == null || reservaDTO.getEquipamentos().isEmpty()) {
	    	System.err.println("Erro: Erro detectado no recebimento dos dados - Verifique os campos obrigatórios.");
	        return false;
	    }

	    Reserva reserva = new Reserva();
	    List<Equipamento> equipamentosList = new ArrayList<>();

	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setResponsavel(reservaDTO.getResponsavel());
	    
	    // Próximo objetivo: Adicione data e hora separadamente na tabela, e ao criar a reserva seja registrada aqui, e não pelo usuário.
	   
	    

	    for (EquipamentoDTO equipamentoDTO : reservaDTO.getEquipamentos()) {
	        if (equipamentoDTO == null || equipamentoDTO.getDescricao() == null || equipamentoDTO.getDescricao().isEmpty()) {
	        	System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipamento.");
	            return false; // Se encontrar uma descrição nula ou vazia, retorna false imediatamente
	        }

	        // Verifica se a descrição do equipamento é composta apenas de espaços em branco
	        if (equipamentoDTO.getDescricao().trim().isEmpty()) {
	        	System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipament.");
	            return false; // Se a descrição contiver apenas espaços em branco, retorna false
	        }

	        Equipamento equipamento = new Equipamento();
	        equipamento.setDescricao(equipamentoDTO.getDescricao());
	        equipamento.setQuantidade(equipamentoDTO.getQuantidade());
	        equipamento.setReserva(reserva);

	        equipamentosList.add(equipamento);
	    }

	    // Melhorar esta validação ainda está passando.... não pode ser vazia e nem nula
	    if (equipamentosList.isEmpty()) {
	        return false;
	    }
	    
	    
	    
	    
	    
	    

	    // Salva a reserva e os equipamentos
	    reserva.getEquipamentos().addAll(equipamentosList);
	    reservaRepository.save(reserva);
	    equipamentoRepository.saveAll(equipamentosList);

	    return true;
	}


	
	
	
}
