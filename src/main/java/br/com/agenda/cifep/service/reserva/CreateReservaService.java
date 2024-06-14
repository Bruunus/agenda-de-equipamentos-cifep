package br.com.agenda.cifep.service.reserva;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.dto.equipamentos.EquipamentoValidation;
import br.com.agenda.cifep.dto.equipamentos.ReservaDeFluxoDeEquipamentoDTO;
import br.com.agenda.cifep.dto.reserva.AgendaDTO;
import br.com.agenda.cifep.dto.reserva.ReservaDTO;
import br.com.agenda.cifep.model.Agenda;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.ReservaDeFluxoDeEquipamento;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;
import br.com.agenda.cifep.repository.reserva.ReservaRepository;
import br.com.agenda.cifep.service.equipamento.UpdateEquipamentoService;

@Service
public class CreateReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UpdateEquipamentoService updateEquipamentoService = 
		 new UpdateEquipamentoService();
	
	
	
	

	
 
	public boolean novaReservaAgendadaEventual(ReservaDTO reservaDTO) {
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}		

	    Reserva reserva = new Reserva();
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setNome(reservaDTO.getNome());
	    reserva.setSobrenome(reservaDTO.getSobrenome());
	    
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
	    reserva.setRecorrenciaDeToda("*");
	    
	    List<ReservaDeFluxoDeEquipamento> equipamentosList = new ArrayList<>();
	    
	    for (ReservaDeFluxoDeEquipamentoDTO reservaDeFluxoDeEquipamentoDTO : reservaDTO.getEquipamentos()) {
	    		
	        ReservaDeFluxoDeEquipamento reservaDeFluxoDeEquipamento = new ReservaDeFluxoDeEquipamento();
	        
	        reservaDeFluxoDeEquipamento.setDescricao(reservaDeFluxoDeEquipamentoDTO.getDescricao());	        
	        reservaDeFluxoDeEquipamento.setQuantidade(reservaDeFluxoDeEquipamentoDTO.getQuantidade());	        
	        
	        reservaDeFluxoDeEquipamento.setReserva(reserva);  
	        equipamentosList.add(reservaDeFluxoDeEquipamento);
	        
	    
	    reserva.getEquipamentos().addAll(equipamentosList);

	    }	    
	    
	    updateEquipamentoService.validacaoAoAtualizaEstoque(equipamentosList);
	    reservaRepository.save(reserva);	  
	    updateEquipamentoService.atualizacaoDeEstoque(equipamentosList);
	  
	    	
	    
        // chama o método aqui
	    
	    
		return true;
	}
	
	
	
	
	
	
	
	
	
//	
	
	public boolean createReservaMultipla(List<ReservaDTO> reservaDTO) {
		
		for (ReservaDTO reserva : reservaDTO) {
		    if (!reserva.validationItens(reserva)) {
		        return false;
		    }
		}
		
		List<Reserva> reservasSalvas = new ArrayList<>();
		
		
		reservaDTO.forEach(reserva -> {
	    	
        int quantidade = reserva.getAgenda().size(); 

        for (int i = 0; i < quantidade; i++) {
        	
            Reserva novaReserva = new Reserva();
            novaReserva.setNome(reserva.getNome());
            novaReserva.setSobrenome(reserva.getSobrenome());
            novaReserva.setSetor(reserva.getSetor());
            novaReserva.setRecorrenciaDeToda("*");
            novaReserva.setStatus(StatusReserva.ATIVA);
            novaReserva.setTipo(TipoReserva.MULTIPLA);

            List<ReservaDeFluxoDeEquipamento> equipamentosList = new ArrayList<>();

            reserva.getEquipamentos().forEach(equipamentoDTO -> {
                ReservaDeFluxoDeEquipamento reservaDeFluxoDeEquipamento = new ReservaDeFluxoDeEquipamento();
                reservaDeFluxoDeEquipamento.setDescricao(equipamentoDTO.getDescricao());
                reservaDeFluxoDeEquipamento.setQuantidade(equipamentoDTO.getQuantidade());
                reservaDeFluxoDeEquipamento.setReserva(novaReserva);
                equipamentosList.add(reservaDeFluxoDeEquipamento);
            });

            novaReserva.setEquipamentos(equipamentosList);

            AgendaDTO agendaDTO = reserva.getAgenda().get(i);
            
            updateEquipamentoService.atualizaEstoqueMultiplaOuAnual(agendaDTO, equipamentosList);	// verifica se tem agenda para o dia de hoje...            
            
            Agenda agenda = new Agenda();
            agenda.setDataRetirada(agendaDTO.getDataRetirada());
            agenda.setHoraRetirada(agendaDTO.getHoraRetirada());
            agenda.setDataDevolucao(agendaDTO.getDataDevolucao());
            agenda.setHoraDevolucao(agendaDTO.getHoraDevolucao());
            agenda.setReserva(novaReserva);

            novaReserva.getAgenda().add(agenda);

            Reserva reservaSalva = reservaRepository.save(novaReserva);
//            updateEquipamentoService.atualizarEstoqueParaMultiplaEAnual();
//            updateEquipamentoService.atualizaEstoqueAbrirReserva(equipamentosList);	//não testado ainda
            reservasSalvas.add(reservaSalva);
        }
	    });

	    reservasSalvas.forEach(reserva -> {
	        System.out.println(reserva + "\n");
	    });

	    return !reservasSalvas.isEmpty();
		
		
		
	 
	}
	
	
	
	public boolean novaReservaAgendadaAnual(List<ReservaDTO> reservaDTO) {
		
		for (ReservaDTO reserva : reservaDTO) {
		    if (!reserva.validationItens(reserva)) {
		        return false;
		    }
		}
		
		
	    List<Reserva> reservasSalvas = new ArrayList<>();

	    reservaDTO.forEach(reserva -> {
	    	
	        int quantidade = reserva.getAgenda().size(); //65

	        for (int i = 0; i < quantidade; i++) {
	        	
	            Reserva novaReserva = new Reserva();
	            novaReserva.setNome(reserva.getNome());
	            novaReserva.setSobrenome(reserva.getSobrenome());
	            novaReserva.setSetor(reserva.getSetor());
	            novaReserva.setRecorrenciaDeToda(reserva.getRecorrenciaDeToda());
	            novaReserva.setStatus(StatusReserva.ATIVA);
	            novaReserva.setTipo(TipoReserva.ANUAL);

	            List<ReservaDeFluxoDeEquipamento> equipamentosList = new ArrayList<>();

	            reserva.getEquipamentos().forEach(equipamentoDTO -> {
	                ReservaDeFluxoDeEquipamento reservaDeFluxoDeEquipamento = new ReservaDeFluxoDeEquipamento();
	                reservaDeFluxoDeEquipamento.setDescricao(equipamentoDTO.getDescricao());
	                reservaDeFluxoDeEquipamento.setQuantidade(equipamentoDTO.getQuantidade());
	                reservaDeFluxoDeEquipamento.setReserva(novaReserva);
	                equipamentosList.add(reservaDeFluxoDeEquipamento);
	            });

	            novaReserva.setEquipamentos(equipamentosList);

	            AgendaDTO agendaDTO = reserva.getAgenda().get(i);
	            
	            updateEquipamentoService.atualizaEstoqueMultiplaOuAnual(agendaDTO, equipamentosList);	// verifica se tem agenda para o dia de hoje...            
	            
	            Agenda agenda = new Agenda();
	            agenda.setDataRetirada(agendaDTO.getDataRetirada());
	            agenda.setHoraRetirada(agendaDTO.getHoraRetirada());
	            agenda.setDataDevolucao(agendaDTO.getDataDevolucao());
	            agenda.setHoraDevolucao(agendaDTO.getHoraDevolucao());
	            agenda.setReserva(novaReserva);

	            novaReserva.getAgenda().add(agenda);

	            Reserva reservaSalva = reservaRepository.save(novaReserva);
	            updateEquipamentoService.atualizacaoDeEstoque(equipamentosList);	//não testado ainda
	            reservasSalvas.add(reservaSalva);
	        }
	    });

	    reservasSalvas.forEach(reserva -> {
	        System.out.println(reserva + "\n");
	    });

	    return !reservasSalvas.isEmpty();
	}


	
	
	


	






	








	
	
	
 



	
	
	
}
