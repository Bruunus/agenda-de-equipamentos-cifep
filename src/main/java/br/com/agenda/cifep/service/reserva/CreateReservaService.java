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
import br.com.agenda.cifep.service.estoque.UpdateEstoqueService;

@Service
public class CreateReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UpdateEstoqueService updateEstoqueService = 
		 new UpdateEstoqueService();
	
	private static LocalDate dataDeHoje;
		
	static {
		dataDeHoje = LocalDate.now();
	}
 
	 









	public boolean novaReservaAgendadaEventual(ReservaDTO reservaDTO) {
		
		if(!reservaDTO.validationItens(reservaDTO)) {
			return false;
		}		

	    Reserva reserva = new Reserva();
	    
	    
	    @SuppressWarnings("unused") LocalDate data_de_hoje = null;
	    
	    reserva.setSetor(reservaDTO.getSetor());
	    reserva.setNome(reservaDTO.getNome());
	    reserva.setSobrenome(reservaDTO.getSobrenome());
	    
	    List<Agenda> agenda = new ArrayList<>();
	    List<LocalDate> listaDeDatasParaValidar = new ArrayList<>();
	   
	    
	    for(AgendaDTO agendaDTO : reservaDTO.getAgenda()) {
	    	Agenda agendaDb = new Agenda();
	    	
	    	agendaDb.setDataRetirada(agendaDTO.getDataRetirada());
	    	agendaDb.setHoraRetirada(agendaDTO.getHoraRetirada());
	    	agendaDb.setDataDevolucao(agendaDTO.getDataDevolucao());
	    	agendaDb.setHoraDevolucao(agendaDTO.getHoraDevolucao());
	    	
	    	data_de_hoje = agendaDb.getDataRetirada();
	    	
	    	listaDeDatasParaValidar.add(agendaDTO.getDataRetirada());
	    	
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
	    
	   
	   
	    
	    if(data_de_hoje.equals(dataDeHoje)) {    	
	    	updateEstoqueService.verificacaoDeEstoque(equipamentosList);
		    reservaRepository.save(reserva);	  
		    updateEstoqueService.atualizacaoDeEstoque(equipamentosList);
	    	
	    } else {
	    	System.out.println("Esta reserva não é para hoje! Mas para o dia "+data_de_hoje);
	    	updateEstoqueService.getEstoqueDisponivelDeAgenda(listaDeDatasParaValidar);
	    	reservaRepository.save(reserva);
	    	
	    	// precisa fazer um atualizador de estoque do dia e que ele seja mostrado a cada 24hs as 07:00hs
	    	// esse atuaizador deve sempre buscar a agenda da data do dia atual
	    	// também ele serve para pesquisar reservas futuras
	    	
	    }
	    
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
            
//            updateEstoqueService.atualizaEstoqueMultiplaOuAnual(agendaDTO, equipamentosList);	// verifica se tem agenda para o dia de hoje...            
            
            Agenda agenda = new Agenda();
            agenda.setDataRetirada(agendaDTO.getDataRetirada());
            agenda.setHoraRetirada(agendaDTO.getHoraRetirada());
            agenda.setDataDevolucao(agendaDTO.getDataDevolucao());
            agenda.setHoraDevolucao(agendaDTO.getHoraDevolucao());
            agenda.setReserva(novaReserva);

            novaReserva.getAgenda().add(agenda);

            Reserva reservaSalva = reservaRepository.save(novaReserva);
//            updateEstoqueService.atualizarEstoqueParaMultiplaEAnual();
//            updateEstoqueService.atualizaEstoqueAbrirReserva(equipamentosList);	//não testado ainda
            reservasSalvas.add(reservaSalva);
        }
	    });

	    reservasSalvas.forEach(reserva -> {
	        System.out.println(reserva + "\n");
	    });

	    return !reservasSalvas.isEmpty();
		
		
		
	 
	}
	
	
	/* Funcionalidade desativada */
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
//	            novaReserva.setTipo(TipoReserva.ANUAL);

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
	            
//	            updateEstoqueService.atualizaEstoqueMultiplaOuAnual(agendaDTO, equipamentosList);	// verifica se tem agenda para o dia de hoje...            
	            
	            Agenda agenda = new Agenda();
	            agenda.setDataRetirada(agendaDTO.getDataRetirada());
	            agenda.setHoraRetirada(agendaDTO.getHoraRetirada());
	            agenda.setDataDevolucao(agendaDTO.getDataDevolucao());
	            agenda.setHoraDevolucao(agendaDTO.getHoraDevolucao());
	            agenda.setReserva(novaReserva);

	            novaReserva.getAgenda().add(agenda);

	            Reserva reservaSalva = reservaRepository.save(novaReserva);
//	            updateEstoqueService.atualizacaoDeEstoque(equipamentosList);	//não testado ainda
	            reservasSalvas.add(reservaSalva);
	        }
	    });

	    reservasSalvas.forEach(reserva -> {
	        System.out.println(reserva + "\n");
	    });

	    return !reservasSalvas.isEmpty();
	}


	
	
	


	






	








	
	
	
 



	
	
	
}
