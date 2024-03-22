package br.com.agenda.cifep.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Equipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;
import br.com.agenda.cifep.repository.EquipamentoRepository;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class CreateReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	

	

	public boolean novaReservaAgendadaNaoAnual(ReservaDTO reservaDTO) {
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}

	    Reserva reserva = new Reserva();
	    
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setResponsavel(reservaDTO.getResponsavel());
	    reserva.setDataRetirada(reservaDTO.getDataRetirada());
	    reserva.setHoraRetirada(reservaDTO.getHoraRetirada());
	    reserva.setDataDevolucao(reservaDTO.getDataDevolucao());
	    reserva.setHoraDevolucao(reservaDTO.getHoraDevolucao());
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    reserva.setTipo(TipoReserva.AGENDADA_NAO_ANUAL);
	    reserva.setRecorrenciaDeToda("");
	    
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
	
	
	
	public boolean novaReservaAgendadaAnual(ReservaDTO reservaDTO) {
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}

	    Reserva reserva = new Reserva();	    
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setResponsavel(reservaDTO.getResponsavel());
	    reserva.setDataRetirada(reservaDTO.getDataRetirada());
	    reserva.setHoraRetirada(reservaDTO.getHoraRetirada());
	    reserva.setDataDevolucao(reservaDTO.getDataDevolucao());
	    reserva.setHoraDevolucao(reservaDTO.getHoraDevolucao());
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    reserva.setTipo(TipoReserva.AGENDADA_ANUAL);
	    reserva.setRecorrenciaDeToda(reservaDTO.getRecorrenciaDeToda());
	    
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
	
	
	
	
	
	
	


	






	








	
	
	
 



	
	
	
}
