package br.com.agenda.cifep.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReservaDTO {
	
	private String setor;
	private String responsavel;	
	private List<EquipamentoDTO> equipamentos;
	private LocalDate dataRetirada;
	private LocalTime horarioRetirada;
	private LocalDate dataDevolucao;
	private LocalTime horaDevolucao;
	
	
	
	
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
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public LocalTime getHorarioRetirada() {
		return horarioRetirada;
	}
	public void setHorarioRetirada(LocalTime horarioRetirada) {
		this.horarioRetirada = horarioRetirada;
	}
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public LocalTime getHoraDevolucao() {
		return horaDevolucao;
	}
	public void setHoraDevolucao(LocalTime horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}
	
	
	
	
	

}
