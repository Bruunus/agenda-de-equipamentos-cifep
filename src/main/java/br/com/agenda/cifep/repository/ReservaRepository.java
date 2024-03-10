package br.com.agenda.cifep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.dto.ReservaDTO;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

	@Query("SELECT new br.com.agenda.cifep.dto.ReservaDTO(r.setor, r.responsavel, r.dataRetirada, r.horaRetirada, e.descricao, e.quantidade) FROM Reserva r JOIN r.equipamentos e")
	List<ReservaDTO> findReservasDTO();

	List<Reserva> findByStatus(StatusReserva s);

	List<Reserva> findByStatusAndTipo(StatusReserva ativa, TipoReserva agendadaAnual);

	// Busca os registros que tenha o mes passado por parametro, que o tipo seja AGENDADA_NAO_ANUAL e com o status ativo
	@Query(value = "SELECT * FROM reserva r WHERE MONTH(data_retirada) = :mes AND tipo = 'AGENDADA_NAO_ANUAL' AND status = 'ATIVA'", nativeQuery = true)
	List<Reserva> buscaMesAtual(@Param("mes") int mes);

	
	
	
}
