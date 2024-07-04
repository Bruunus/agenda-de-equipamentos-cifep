package br.com.agenda.cifep.dto.reserva;

import java.time.LocalDate;

public class RadarDeReservasAgendadasDTO {
	
	private String nome;
//	private String setor;	//	aguardando novas instruções ...
	private String descricaoEquipamento;
	private Integer quantidadeEquipamento;
	private LocalDate dataRetirada;
//	private LocalTime horaRetirada;		//	aguardando novas instruções ...
//	private LocalDate dataFinalizada;	//	aguardando novas instruções ...
	
	public RadarDeReservasAgendadasDTO() {
		
	}
	public RadarDeReservasAgendadasDTO(
			String descricao, Integer quantidade, String name, LocalDate dataRetirada) {
		this.descricaoEquipamento = descricao;
		this.quantidadeEquipamento = quantidade;
		this.nome = name;
		this.dataRetirada = dataRetirada;
	}
	public RadarDeReservasAgendadasDTO(String descricao, Integer quantidade) {
		this.descricaoEquipamento = descricao;
		this.quantidadeEquipamento = quantidade;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+this.nome+"\t"+this.dataRetirada;
	}
	
	public String getDescricao() {
		return descricaoEquipamento;
	}
	
	public void setDescricao(String descricao) {
		this.descricaoEquipamento = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public Integer getQuantidadeEquipamento() {
		return quantidadeEquipamento;
	}
	public void setQuantidadeEquipamento(Integer quantidadeEquipamento) {
		this.quantidadeEquipamento = quantidadeEquipamento;
	}
	
	
	
	
	

}
