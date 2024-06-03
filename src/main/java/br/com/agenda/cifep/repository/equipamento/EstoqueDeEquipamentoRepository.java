package br.com.agenda.cifep.repository.equipamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO;
import br.com.agenda.cifep.model.EstoqueEquipamento;

@Repository
public interface EstoqueDeEquipamentoRepository extends JpaRepository<EstoqueEquipamento, Long> {

	EstoqueEquipamento findByValor(String name);
	
	List<EstoqueEquipamento> findAll();

	
	//@Query(value = "SELECT valor AS valor, quantidade AS quantidade FROM `estoque-de-equipamento`;", nativeQuery = true)
	
	@Query("SELECT new br.com.agenda.cifep.dto.equipamentos.EstoqueQuantidadeDTO(e.valor, e.quantidade) "
			+ "FROM EstoqueEquipamento e")
	List<EstoqueQuantidadeDTO> getEstoqueQuantidades();
	
	 

}
