package br.com.agenda.cifep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.model.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	
	// Próxima funcionalidade
	// ver os equipamentos que estiver na coluna responsavel chamada Kelly (ou qualquer responsavel)
	// SELECT e.* FROM equipamento e INNER JOIN reserva r ON e.reserva_id = r.id WHERE r.responsavel = 'Kelly';
	List<Equipamento> findByReservaResponsavel(String responsavel);
	
}
