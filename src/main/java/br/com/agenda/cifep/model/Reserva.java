package br.com.agenda.cifep.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.agenda.cifep.dto.EquipamentoDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String setor;
	
	@NotNull
	private String responsavel;
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	private List<Equipamento> equipamentos = new ArrayList<>();

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> list) {
		this.equipamentos = list;
	}
 
	
	

}
