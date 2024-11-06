package br.com.agenda.cifep.repository.equipamento;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;

@Repository
public interface EstqDeEquipamentoRepository extends CrudRepository<EstoqueEquipamento, Long>{

	
	@Query("SELECT new br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO(e.id, e.descricao, e.quantidade) "
			+ "FROM EstoqueEquipamento e")
	List<EstoqueEquipamento> findAllTable();

	
	//@Query(value = "SELECT valor AS valor, quantidade AS quantidade FROM `estoque-de-equipamento`;", nativeQuery = true)
	
	@Query("SELECT new br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO(e.descricao, e.quantidade) "
			+ "FROM EstoqueEquipamento e")
	List<EstoqueQuantidadeDTO> getEstoqueQuantidades();
	
	
}
