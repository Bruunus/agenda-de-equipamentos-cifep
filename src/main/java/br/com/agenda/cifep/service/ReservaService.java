package br.com.agenda.cifep.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Equipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
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
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}

	    Reserva reserva = new Reserva();
	    
	    
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setResponsavel(reservaDTO.getResponsavel());	
	    
	    reserva.setDataRetirada(LocalDate.now());
	    reserva.setHoraRetirada(LocalTime.now());
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    
	    
	    List<Equipamento> equipamentosList = new ArrayList<>();
	    
		    for (EquipamentoDTO equipamentoDTO : reservaDTO.getEquipamentos()) {
		        Equipamento equipamento = new Equipamento();
		        equipamento.setDescricao(equipamentoDTO.getDescricao());
		        equipamento.setQuantidade(equipamentoDTO.getQuantidade());
		        equipamento.setReserva(reserva); // Associar o equipamento à reserva
		        equipamentosList.add(equipamento);
		    
		    
		    reserva.getEquipamentos().addAll(equipamentosList);
	
		    reservaRepository.save(reserva);
	
	
		    }
		    
	    return true;
	}


	
	
	
	
	
	
	public List<ReservaDTO> carregarReservas() {
		 
	 
		List<Reserva> reservas =  reservaRepository.findAll();
		List<ReservaDTO> listaDeDados = new ArrayList<>();
		
		reservas.forEach(loadData -> {
			ReservaDTO reservaDTO = new ReservaDTO();
			
			reservaDTO.setSetor(loadData.getSetor());
			reservaDTO.setResponsavel(loadData.getResponsavel());
			
			// para todo join na tabela
			List<EquipamentoDTO> equipamentosDTO = new ArrayList<>();
			loadData.getEquipamentos().forEach(equipamento -> {
				EquipamentoDTO equipDTO = new EquipamentoDTO();
				
				equipDTO.setId(equipamento.getId());
				equipDTO.setDescricao(equipamento.getDescricao());
				equipDTO.setQuantidade(equipamento.getQuantidade());	
				
				equipamentosDTO.add(equipDTO);
			});
			
			
			reservaDTO.setEquipamentos(equipamentosDTO);
			reservaDTO.setDataRetirada(loadData.getDataRetirada());
			reservaDTO.setHorRetirada(loadData.getHoraRetirada());
			// loadStatus
			// load dataDevolucao
			// load horaDevolucao
			
			
			listaDeDados.add(reservaDTO);
			
		});	
		
		
		
		
		return listaDeDados;
	}
	
	
 



	
	
	
}
