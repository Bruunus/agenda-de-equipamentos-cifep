package br.com.agenda.cifep.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		List<Reserva> reservas =  reservaRepository.buscaMesAtualAtivas(mes);		
		return reservaDTO.carregarDados(reservas);
		
	}



	public List<ReservaDTO> carregarReservasFinalizadas() {
		
		List<Reserva> finalizadas = reservaRepository.findByStatus(StatusReserva.FINALIZADA);
		return reservaDTO.carregarDados(finalizadas);
	}
		
	// 	Consulta com JPQL
		public List<ReservaDTO> carregarReservasFinalizadasPorMes(int mes) {		
	
			List<Reserva> finalizadasPorMes =  reservaRepository.buscaMesAtualFinalizadas(mes);		
			return reservaDTO.carregarDados(finalizadasPorMes);
			
		}



		public List<ReservaDTO> carregarReservasFinalizadasPorNome(String nome) {
			List<Reserva> finalizadasPorNome = reservaRepository.findByResponsavelAndStatus(nome, StatusReserva.FINALIZADA);
			if(finalizadasPorNome.isEmpty() || finalizadasPorNome == null) {
				return Collections.emptyList();
			} else {
				finalizadasPorNome.forEach(i -> {System.out.print(i+"\n");});
				return reservaDTO.carregarDados(finalizadasPorNome);
			}
		}
		
		
		public List<ReservaDTO> carregarReservasFinalizadasPorSetor(String setor) {
			
	 
			List<Reserva> finalizadasPorNome = reservaRepository.findBySetorAndStatus(setor, StatusReserva.FINALIZADA);
			if(finalizadasPorNome.isEmpty() || finalizadasPorNome == null) {
				return Collections.emptyList();
			} else {
				finalizadasPorNome.forEach(i -> {System.out.print(i+"\n");});
				return reservaDTO.carregarDados(finalizadasPorNome);
			}
			 
		}
		
		
	

}
