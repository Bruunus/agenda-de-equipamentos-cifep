package br.com.agenda.cifep.service.reserva;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;
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
		 
			updateReserva.setDataDevolucao(reservaDTOHttp.getDataDevolucao());
			updateReserva.setDataRetirada(reservaDTOHttp.getDataRetirada());
			updateReserva.setHoraDevolucao(reservaDTOHttp.getHoraDevolucao());
			updateReserva.setHoraRetirada(reservaDTOHttp.getHoraRetirada());
			updateReserva.setRecorrenciaDeToda(reservaDTOHttp.getRecorrenciaDeToda());
			updateReserva.setResponsavel(reservaDTOHttp.getResponsavel());
			updateReserva.setSetor(reservaDTOHttp.getSetor());
			updateReserva.setTipo(reservaDTOHttp.getTipoReserva());
			
			
			List<EquipamentoDTO> equipamentosDTO = new ArrayList<>();
			
			updateReserva.getEquipamentos().forEach(equipamento -> {
				EquipamentoDTO equipDTO = new EquipamentoDTO();
				
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
