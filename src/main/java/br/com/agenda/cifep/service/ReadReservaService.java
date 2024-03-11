package br.com.agenda.cifep.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class ReadReservaService {
	
	
	private static ReservaDTO reservaDTO = new ReservaDTO();
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	public List<ReservaDTO> carregarTodasReservas() {
		 
		 
		List<Reserva> reservas =  reservaRepository.findAll();		
		return reservaDTO.carregarDados(reservas);
	}
	
	
	
	public List<ReservaDTO> carregarTodasReservasAtivas() {
		
		List<Reserva> reservas =  reservaRepository.findByStatus(StatusReserva.ATIVA);		
		return reservaDTO.carregarDados(reservas);

	}



 
	public List<ReservaDTO> carregarTodasReservasAtivasAnuais() {
		 
		List<Reserva> reservas =  reservaRepository.findByStatusAndTipo(StatusReserva.ATIVA, TipoReserva.AGENDADA_ANUAL);
		return reservaDTO.carregarDados(reservas);		
		
	}


	// 	Consulta com JPQL
	public List<ReservaDTO> carregarTodasReservasMesAtual(int mes) {		

		List<Reserva> reservas =  reservaRepository.buscaMesAtual(mes);
		
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
	

}
