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

	// SELECT
	
	@Query(value = "SELECT new br.com.agenda.cifep.dto.ReservaDTO(r.setor, r.responsavel, r.dataRetirada, r.horaRetirada, e.descricao, e.quantidade) FROM Reserva r JOIN r.equipamentos e", nativeQuery = true)
	List<ReservaDTO> findReservasDTO();

	List<Reserva> findByStatus(StatusReserva s);

	List<Reserva> findByStatusAndTipo(StatusReserva ativa, TipoReserva agendadaAnual);

	// Busca os registros que tenha o mes passado por parametro, que o tipo seja AGENDADA_NAO_ANUAL e com o status ativo
	@Query(value = "SELECT r.* FROM reserva r INNER JOIN data d ON r.id = d.reserva_id WHERE MONTH(d.data_retirada) = :mes AND r.status = 'ATIVA'", nativeQuery = true)
	List<Reserva> pesquisaMesAtualAtivas(@Param("mes") int mes);


	
	@Query(value = "SELECT * FROM reserva r INNER JOIN data d ON r.id = d.reserva_id WHERE MONTH(d.data_retirada) = :mes AND status = 'FINALIZADA'", nativeQuery = true)
	List<Reserva> pesquisaPorMesAtualFinalizadas(@Param("mes") int mes);

	
	List<Reserva> findByResponsavelAndStatus(String nome, StatusReserva finalizada);

	List<Reserva> findBySetorAndStatus(String setor, StatusReserva finalizada);

	
	@Query(value = "SELECT * FROM reserva r INNER JOIN data d ON r.id = d.reserva_id WHERE MONTH(d.data_retirada) = :now AND r.status = 'ATIVA'", nativeQuery = true)
	List<Reserva> pesquisaPorMesAtual(@Param("now")int now);
 
	@Query(value = "SELECT * FROM reserva r INNER JOIN data d ON r.id = d.reserva_id WHERE DAY(data_retirada) = :day AND status = 'ATIVA'", nativeQuery = true)
	List<Reserva> pesquisaPorDiaAtual(int day);
	
	@Query(value = "SELECT * FROM reserva r WHERE setor = :sector AND status = 'ATIVA'", nativeQuery = true)
	List<Reserva> pesquisaPorSetor(@Param("sector")String sector);

	@Query(value = "SELECT * FROM reserva r WHERE responsavel = :nome AND status = 'ATIVA'", nativeQuery = true)
	List<Reserva> pesquisaPorNome(@Param("nome") String nome);

	
	Object findByIdAndStatus(Long id, StatusReserva ativa);
	
	
	// UPDATE
	
	
	
	
}
