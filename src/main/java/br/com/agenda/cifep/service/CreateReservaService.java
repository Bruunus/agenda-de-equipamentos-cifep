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
public class ReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	

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
	    
	    reserva.setDataDevolucao(reservaDTO.getDataDevolucao());
	    reserva.setHoraDevolucao(reservaDTO.getHoraDevolucao());
	    
	    reserva.setStatus(StatusReserva.ATIVA);
	    reserva.setTipo(TipoReserva.EVENTUAL);
	    reserva.setRecorrenciaDeToda("*");
	    
	    
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
	    reserva.setRecorrenciaDeToda("*");
	    
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
	
	
	
	
	
	
	public List<ReservaDTO> carregarTodasReservas() {
		 
	 
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
			reservaDTO.setStatusReserva(loadData.getStatus());
			reservaDTO.setTipoReserva(loadData.getTipo());
			reservaDTO.setDataDevolucao(loadData.getDataDevolucao());
			reservaDTO.setHoraDevolucao(loadData.getHoraDevolucao());
			
			
			listaDeDados.add(reservaDTO);
			
		});	
		
		
		
		
		return listaDeDados;
	}


	






	public boolean finalizaReserva(Long id) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		Optional<Reserva> registro = reservaRepository.findById(id);
		
		System.out.println(registro.toString());
		
		
		if(registro.isPresent()) {
			
			Reserva reservaLoad = registro.get();
			
			System.out.println(reservaLoad.toString() );
		
			if(reservaLoad.getStatus() == StatusReserva.FINALIZADA) {
				return false;
			};
			
		reservaLoad.setDataDevolucao(LocalDate.now());
		String dataDevolucao = LocalTime.now().format(formatter);
		reservaLoad.setHoraDevolucao(LocalTime.parse(dataDevolucao, formatter));
		reservaLoad.setStatus(StatusReserva.FINALIZADA);		
		 
		reservaRepository.save(reservaLoad);
		return true;
		
		} else {
			
			return false;
		
		}
		
	}








	
	
	
 



	
	
	
}
