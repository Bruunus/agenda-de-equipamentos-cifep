package br.com.agenda.cifep.dto;

import java.util.List;

import br.com.agenda.cifep.model.Equipamento;

public class ReservaDTO {
	
	private String setor;
	private String responsavel;
	private List<EquipamentoDTO> equipamentos;
	
	
	
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
	public List<EquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}
	public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
	

}
