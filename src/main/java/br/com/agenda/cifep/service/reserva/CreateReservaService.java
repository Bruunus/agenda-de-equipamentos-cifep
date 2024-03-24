package br.com.agenda.cifep.service.reserva;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.AgendaDTO;
import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Agenda;
import br.com.agenda.cifep.model.Equipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class CreateReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	

	

	public boolean novaReservaAgendadaEventual(ReservaDTO reservaDTO) {
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}

	    Reserva reserva = new Reserva();
	    
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setResponsavel(reservaDTO.getResponsavel());
	    
	    List<Agenda> agenda = new ArrayList<>();
	    
	    
	    for(AgendaDTO agendaDTO : reservaDTO.getAgenda()) {
	    	Agenda agendaDb = new Agenda();
	    	
	    	agendaDb.setDataRetirada(agendaDTO.getDataRetirada());
	    	agendaDb.setHoraRetirada(agendaDTO.getHoraRetirada());
	    	agendaDb.setDataDevolucao(agendaDTO.getDataDevolucao());
	    	agendaDb.setHoraDevolucao(agendaDTO.getHoraDevolucao());
	    	
	    	agendaDb.setReserva(reserva);
	    	agenda.add(agendaDb);
	    }
	    
	    reserva.setAgenda(agenda);
	    
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    reserva.setTipo(TipoReserva.EVENTUAL);
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
	    
	    
	    List<Agenda> agenda = new ArrayList<>();
	    
	    for(AgendaDTO agendaDTO : reservaDTO.getAgenda()) {
	    	Agenda agendaDb = new Agenda();
	    	
	    	agendaDb.setDataRetirada(agendaDTO.getDataRetirada());
	    	agendaDb.setHoraRetirada(agendaDTO.getHoraRetirada());
	    	agendaDb.setDataDevolucao(agendaDTO.getDataDevolucao());
	    	agendaDb.setHoraDevolucao(agendaDTO.getHoraDevolucao());
	    	
	    	agendaDb.setReserva(reserva);
	    	agenda.add(agendaDb);
	    	
	    }
	    
	    reserva.setAgenda(agenda);
	     
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    reserva.setTipo(TipoReserva.ANUAL);
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
