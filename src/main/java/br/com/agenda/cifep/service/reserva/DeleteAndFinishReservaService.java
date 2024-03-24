package br.com.agenda.cifep.service.reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agenda.cifep.model.Agenda;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class DeleteAndFinishReservaService {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	

	public boolean finalizaReserva(Long id) {
			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		Optional<Reserva> registro = reservaRepository.findById(id);	 
		
		// load register
		if(registro.isPresent()) {
			
			Reserva reservaLoad = registro.get();
						
			if(reservaLoad.getStatus() == StatusReserva.FINALIZADA) {
				return false;
			};
		
		// Date and time provider server
		LocalDate dataDevolucao = LocalDate.now();
		LocalTime horaDevolucao = LocalTime.parse(LocalTime.now().format(formatter));
		
		
		for(Agenda agenda : reservaLoad.getAgenda()) {
			agenda.setDataFinalizada(dataDevolucao);
			agenda.setHoraFinalizada(horaDevolucao);
		}
		
		reservaLoad.setStatus(StatusReserva.FINALIZADA);
		
		reservaRepository.save(reservaLoad);	
		
			
		return true;
		
		} else {			
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
}
