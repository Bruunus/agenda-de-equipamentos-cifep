package br.com.agenda.cifep.service.reserva;

import java.time.LocalDate;
import java.time.Month;
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
	
	
	// Serviços para reservas ativas
	
	
	public List<ReservaDTO> carregarTodasReservas() {
		 
		 
		List<Reserva> reservas =  reservaRepository.findAll();		
		return reservaDTO.carregarDados(reservas);
	}
	
	
	
	public List<ReservaDTO> carregarTodasReservasAtivas() {
		
		List<Reserva> reservas =  reservaRepository.findByStatus(StatusReserva.ATIVA);		
		return reservaDTO.carregarDados(reservas);

	}

 
	public List<ReservaDTO> carregarTodasReservasAtivasAnuais() {
		 
		List<Reserva> reservas =  reservaRepository.findByStatusAndTipo(StatusReserva.ATIVA, TipoReserva.ANUAL);
		return reservaDTO.carregarDados(reservas);		
		
	}


	// 	Consulta com JPQL
	public List<ReservaDTO> carregarTodasReservasMesAtual(int mes) {		

		List<Reserva> reservas =  reservaRepository.pesquisaMesAtualAtivas(mes);	
		return reservaDTO.carregarDados(reservas);
		
	}
	
	// SQL nativo
	public List<ReservaDTO> carregarTodasReservasDoMesAtual() {
		
		int now = LocalDate.now().getMonthValue();	
		System.out.println(now);
		//int teste = 4;
		List<Reserva> mesAtual = reservaRepository.pesquisaPorMesAtual(now); 
		
		if(mesAtual.isEmpty() || mesAtual == null) {
			return Collections.emptyList();
		} else {				
			return reservaDTO.carregarDados(mesAtual);
		}
	 
		
	}		


	// SQL nativo
	public List<ReservaDTO> carregarTodasReservasDoDiaAtual() {
		
		int day = LocalDate.now().getDayOfMonth();
		//int teste = 18;
		List<Reserva> diaAtual = reservaRepository.pesquisaPorDiaAtual(day);
		
		if(diaAtual.isEmpty() || diaAtual == null) {
			return Collections.emptyList();
		} else {				
			return reservaDTO.carregarDados(diaAtual);
		}
		
	}



	public List<ReservaDTO> carregarReservasPorSetor(String sector) {
		
		List<Reserva> setor = reservaRepository.pesquisaPorSetor(sector);
		
		
		if(setor.isEmpty() || setor == null) {
			return Collections.emptyList();
		} else {				
			return reservaDTO.carregarDados(setor);
		}
	}
			

	public List<ReservaDTO> pesquisarReservasPorNomeOuSetor(String search) {
		List<Reserva> porParametro = reservaRepository.pesquisaPorNomeOuSetor(search);
		
		if(porParametro.isEmpty() || porParametro == null) {
			return Collections.emptyList();
		} else {	
			
			return reservaDTO.carregarDados(porParametro);
		}
	}


	
	
	
	// Serviços para reservas finalizadas


	public List<ReservaDTO> carregarReservasFinalizadas() {
		
		List<Reserva> finalizadas = reservaRepository.findByStatus(StatusReserva.FINALIZADA);
		return reservaDTO.carregarDados(finalizadas);
	}
		
	// 	SQL nativo
		public List<ReservaDTO> carregarReservasFinalizadasPorMes(int mes) {		
	
			List<Reserva> finalizadasPorMes =  reservaRepository.pesquisaPorMesAtualFinalizadas(mes);		
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
