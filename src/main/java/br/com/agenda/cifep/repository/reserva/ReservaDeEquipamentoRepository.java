package br.com.agenda.cifep.repository.reserva;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.model.ReservaDeFluxoDeEquipamento;

@Repository
public interface ReservaDeEquipamentoRepository extends JpaRepository<ReservaDeFluxoDeEquipamento, Long> {
	
	// Próxima funcionalidade
	// ver os equipamentos que estiver na coluna responsavel chamada Kelly (ou qualquer responsavel)
	// SELECT e.* FROM equipamento e INNER JOIN reserva r ON e.reserva_id = r.id WHERE r.responsavel = 'Kelly';
	List<ReservaDeFluxoDeEquipamento> findByReservaNome(String responsavel);
	
}
