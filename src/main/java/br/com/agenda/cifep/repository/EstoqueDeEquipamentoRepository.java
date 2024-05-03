package br.com.agenda.cifep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda.cifep.model.EstoqueEquipamento;

@Repository
public interface EstoqueDeEquipamentoRepository extends JpaRepository<EstoqueEquipamento, Long> {

	EstoqueEquipamento findByDescricao(String name);

}
