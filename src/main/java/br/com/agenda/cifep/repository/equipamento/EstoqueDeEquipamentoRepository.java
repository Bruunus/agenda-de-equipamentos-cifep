package br.com.agenda.cifep.repository.equipamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.dto.equipamentos.ReservaDeFluxoDeEquipamentoDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.ReservaDeFluxoDeEquipamento;

@Repository
public interface EstoqueDeEquipamentoRepository extends JpaRepository<EstoqueEquipamento, Long> {
	
	List<EstoqueEquipamento> findAll();

	EstoqueEquipamento findByValor(String name);
	
//	List<EstoqueEquipamento> findAll();
	
//	@Query("SELECT new br.com.agenda.cifep.dto.equipamentos.EstoqueEquipamento(e.id, e.descricao, e.valor, e.quantidade) "
//			+ "FROM EstoqueEquipamento e")
//	List<EstoqueEquipamento> findAllTable();

	
	//@Query(value = "SELECT valor AS valor, quantidade AS quantidade FROM `estoque-de-equipamento`;", nativeQuery = true)
	
	@Query("SELECT new br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO(e.valor, e.quantidade) "
			+ "FROM EstoqueEquipamento e")
	List<EstoqueQuantidadeDTO> getEstoqueQuantidades();
	
	
	
	
	/**
	 * Traga a 'descricao' e 'quantidade' de 'reserva-de-fluxo-de-equipamento' em que na tabela 'reserva' o 'status' esteja 
	 * como 'ATIVA' na tabela 'data' o campo 'data_retirada' no formato 'yyyy-mm-dd' usando o método DAY() possa fornecer
	 * o dia específico.
	 * 
	 * Essa funcionalidade foi feita para poder ao coletar esses dados na classe @UpdateEquipamentoService usando o método
	 * @atualizarEstoqueParaMultiplaEAnual para serem comparados com os dados da tabela estoque-de-equipamentos.
	 * 
	 * @param diaRetirada
	 * @return
	 */
	@Query(value = "SELECT rfe.descricao, rfe.quantidade " +
            "FROM `reserva-de-fluxo-de-equipamento` AS rfe " +
            "JOIN reserva AS r ON rfe.reserva_id = r.id " +
            "JOIN data AS d ON r.id = d.reserva_id " +
            "WHERE r.status = 'ATIVA' AND DAY(d.data_retirada) = ?1", nativeQuery = true)
	List<Object[]> getEstoqueDoDiaAtual(int diaRetirada);
	
	


}
