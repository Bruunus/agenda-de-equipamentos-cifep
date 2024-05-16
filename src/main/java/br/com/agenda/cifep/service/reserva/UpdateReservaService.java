package br.com.agenda.cifep.service.reserva;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.agenda.cifep.dto.AgendaDTO;
import br.com.agenda.cifep.dto.ReservaDeEquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Agenda;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.repository.ReservaRepository;

@Service
public class UpdateReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
 

	
	public ReservaDTO atualizarReserva(Long id, ReservaDTO reservaDTOHttp) {		
		 
		
		Reserva updateReserva = (Reserva) reservaRepository.findByIdAndStatus(id, StatusReserva.ATIVA);
		
		if (updateReserva == null) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Falha ao encontrar registro à ser atualizado, foi alterado ou deletado.");
		  
		} else {
		 
		List<AgendaDTO> novaAgendaDTO = reservaDTOHttp.getAgenda();
		List<Agenda> agenda = updateReserva.getAgenda();

		int tamanhoLista = Math.min(novaAgendaDTO.size(), agenda.size());

		for (int i = 0; i < tamanhoLista; i++) {
		    AgendaDTO agendaDTO = novaAgendaDTO.get(i);
		    Agenda agendaToUpdate = agenda.get(i);

		    agendaToUpdate.setDataRetirada(agendaDTO.getDataRetirada());
		    agendaToUpdate.setHoraRetirada(agendaDTO.getHoraRetirada());
		    agendaToUpdate.setDataDevolucao(agendaDTO.getDataDevolucao());
		    agendaToUpdate.setHoraDevolucao(agendaDTO.getHoraDevolucao());
		}

			
			
			updateReserva.setRecorrenciaDeToda(reservaDTOHttp.getRecorrenciaDeToda());
			updateReserva.setNome(reservaDTOHttp.getNome());
			updateReserva.setSobrenome(reservaDTOHttp.getSobrenome());
			updateReserva.setSetor(reservaDTOHttp.getSetor());
			updateReserva.setTipo(reservaDTOHttp.getTipoReserva());
			
			
			List<ReservaDeEquipamentoDTO> equipamentosDTO = new ArrayList<>();
			
			updateReserva.getEquipamentos().forEach(equipamento -> {
				ReservaDeEquipamentoDTO equipDTO = new ReservaDeEquipamentoDTO();
				
				equipDTO.setId(equipamento.getId());
				equipDTO.setDescricao(equipamento.getDescricao());
				equipDTO.setQuantidade(equipamento.getQuantidade());	
				
				equipamentosDTO.add(equipDTO);
			});
			
			
			
			reservaRepository.save(updateReserva);
			
			return reservaDTOHttp;
		}		
				

	}

}
