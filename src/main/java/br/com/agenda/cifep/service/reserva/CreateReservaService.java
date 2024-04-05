package br.com.agenda.cifep.service.reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	    }	    
	    
	    reservaRepository.save(reserva);
	    
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
	    
	    }
	    
	    reservaRepository.save(reserva);
	    
		return true;
		
	}


	// em contrução
	public boolean createReservaMultipla(List<ReservaDTO> reservaDTO) {
		
		for (ReservaDTO reserva : reservaDTO) {
			
		    Reserva reservaEntity = new Reserva();

		    // Configura os dados da reserva na entidade
		    reservaEntity.setSetor(reserva.getSetor());
		    reservaEntity.setResponsavel(reserva.getResponsavel());

		    // Salva a reserva no banco de dados usando o repository
		    reservaRepository.save(reservaEntity);

		    // Agora precisamos salvar cada agenda associada a esta reserva
		    for (Agenda agenda : reserva.getAgenda()) {
		        Agenda agendaEntity = new Agenda();

		        // Configura os dados da agenda na entidade
		        agendaEntity.setDataRetirada(agenda.getDataRetirada());
		        agendaEntity.setHoraRetirada(agenda.getHoraRetirada());
		        agendaEntity.setDataDevolucao(agenda.getDataDevolucao());
		        agendaEntity.setHoraDevolucao(agenda.getHoraDevolucao());
		        // Configura a relação entre a agenda e a reserva
		        agendaEntity.setReserva(reservaEntity);

		        // Salva a agenda no banco de dados usando o repository
		        agendaRepository.save(agendaEntity);
		    }
		}

		
		
		
		
//		
////		if(!reservaDTO.validationItens(reservaDTO)) {
////			return false;
////		}		
//		
//		// System.out.println(reservaDTO);
//		 
//		 
//		 
//		 List<ReservaDTO> reservas = new ArrayList<>();
//		 
//		 
//
//		 for (AgendaDTO agenda : reservaDTO.getAgenda()) {
//			 
//			 ReservaDTO novaReserva = new ReservaDTO();
//		     
//		     novaReserva.setSetor(reservaDTO.getSetor());
//		     novaReserva.setResponsavel(reservaDTO.getResponsavel());
//		     novaReserva.setEquipamentos(reservaDTO.getEquipamentos());
//
//		     List<AgendaDTO> novaAgenda = new ArrayList<>();
//		     novaAgenda.add(agenda);
//		     novaReserva.setAgenda(novaAgenda);
//
//		     reservas.add(novaReserva);
//		     
//		     System.out.println(novaReserva);
//		 }

		 // Agora, 'reservas' contém uma lista separada para cada data de retirada diferente,
		 // cada uma contendo a agenda correspondente e os outros dados da reserva iguais.

		 
		 
//		 Set<LocalDate> datasRetirada = new HashSet<>();
//		 boolean dataRepetida = false;
//
//		 for (AgendaDTO agenda : reservaDTO.getAgenda()) {
//		     LocalDate dataRetirada = agenda.getDataRetirada();
//		     if (datasRetirada.contains(dataRetirada)) {
//		         dataRepetida = true;
//		         break;
//		     } else {
//		         datasRetirada.add(dataRetirada);
//		     }
//		 }
//
//		 if (dataRepetida) {
//		     System.out.println("Existe pelo menos uma data de retirada repetida.");
//		 } else {
//		     System.out.println("Não existem datas de retirada repetidas.");
//		 }


		 
		
//	    Reserva reserva = new Reserva();
//	    
//	    
//	    
//	    reserva.setSetor(reservaDTO.getSetor());
//	    reserva.setResponsavel(reservaDTO.getResponsavel());
//	    
//	    List<Agenda> agenda = new ArrayList<>();
//	    
//	    
//	    for(AgendaDTO agendaDTO : reservaDTO.getAgenda()) {
//	    	Agenda agendaDb = new Agenda();
//	    	
//	    	agendaDb.setDataRetirada(agendaDTO.getDataRetirada());
//	    	agendaDb.setHoraRetirada(agendaDTO.getHoraRetirada());
//	    	agendaDb.setDataDevolucao(agendaDTO.getDataDevolucao());
//	    	agendaDb.setHoraDevolucao(agendaDTO.getHoraDevolucao());
//	    	
//	    	agendaDb.setReserva(reserva);
//	    	agenda.add(agendaDb);
//	    }
//	    
//	    reserva.setAgenda(agenda);
//	    
//	    
//	    reserva.setStatus(StatusReserva.ATIVA);
//	    reserva.setTipo(TipoReserva.RECORRENTE);
//	    reserva.setRecorrenciaDeToda("");
//	    
//	    List<Equipamento> equipamentosList = new ArrayList<>();
//	    
//	    for (EquipamentoDTO equipamentoDTO : reservaDTO.getEquipamentos()) {
//	        Equipamento equipamento = new Equipamento();
//	        equipamento.setDescricao(equipamentoDTO.getDescricao());
//	        equipamento.setQuantidade(equipamentoDTO.getQuantidade());
//	        equipamento.setReserva(reserva); // Associar o equipamento à reserva
//	        equipamentosList.add(equipamento);	    
//	    
//	    reserva.getEquipamentos().addAll(equipamentosList);
//
//	    }		
//	    
//		 Iterable<Reserva> list = new ArrayList<>();
//		 
// 	    reservaRepository.saveAll(list);
//		
		return false;
	}
	
	
	
	
	
	
	


	






	








	
	
	
 



	
	
	
}
