package br.com.agenda.cifep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	@Query("SELECT new br.com.agenda.cifep.dto.ReservaDTO(r.setor, r.responsavel, r.dataRetirada, r.horaRetirada, e.descricao, e.quantidade) FROM Reserva r JOIN r.equipamentos e")
	List<ReservaDTO> findReservasDTO();

	
	
}
