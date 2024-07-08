package br.com.agenda.cifep.dto.reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RadarDeReservasAgendadasDTO {
	
	private Long id;
	private String nome;
	private String setor;	//	aguardando novas instruções ...
	private String descricaoEquipamento;
	private Integer quantidadeEquipamento;
	private int somaQuantidade;
	private LocalDate dataRetirada;	
	private LocalTime horaRetirada;		//	aguardando novas instruções ...
	private LocalDate dataDevolucao;
	private LocalTime horaDevolucao;
	private List<LocalDate> datas;
	
	public RadarDeReservasAgendadasDTO() {}
	
	public RadarDeReservasAgendadasDTO(String descricao, Integer quantidade, Long id, String name, String setor, 
			LocalDate dataRetirada, LocalTime horaRetirada, LocalDate dataDevolucao, LocalTime horaDevolucao) {
		this.setDescricao(descricao);			
		this.setQuantidadeEquipamento(quantidade);
		this.setId(id);
		this.setNome(name);
		this.setSetor(setor);
		this.setDataRetirada(dataRetirada);
		this.setHoraRetirada(horaRetirada);
		this.setDataDevolucao(dataDevolucao);
		this.setHoraDevolucao(horaDevolucao);
		
	}
	
	/**
	 * Contrutor direcionado para o objeto para a lista List<RadarDeReservasAgendadasDTO> totalDisponivel = new ArrayList<>();
	 * do metodo monitoradorDeEstoqueParaReservasPosteriores(List<LocalDate> datas) da classe @UpdateEstoqueService
	 * @param dataRetirada
	 * @param horaRetirada
	 * @param descricao
	 * @param somaQuantidade
	 */
	public RadarDeReservasAgendadasDTO(LocalDate dataRetirada, LocalTime horaRetirada, LocalDate dataDevolucao, LocalTime horaDevolucao, String descricao, int somaQuantidade) {
		this.dataRetirada = dataRetirada;
		this.horaRetirada = horaRetirada;
		this.dataDevolucao = dataDevolucao;
		this.horaDevolucao = horaDevolucao;
		this.descricaoEquipamento = descricao;
		this.somaQuantidade = somaQuantidade;
	}
	
	
		
	public RadarDeReservasAgendadasDTO(String descricao, Integer quantidade) {
		this.descricaoEquipamento = descricao;
		this.quantidadeEquipamento = quantidade;
	}
	
	
	public RadarDeReservasAgendadasDTO(String descricao, Integer quantidadeSomada, LocalDate dataRetirada) {
		this.descricaoEquipamento = descricao;
		this.somaQuantidade = quantidadeSomada;
		this.dataRetirada = dataRetirada;
		
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
	public int getSomaQuantidade() {
		return somaQuantidade;
	}
	public void setSomaQuantidade(int somaQuantidade) {
		this.somaQuantidade = somaQuantidade;
	}
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

	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}

	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}

	public LocalTime getHoraRetirada() {
		return horaRetirada;
	}

	public void setHoraRetirada(LocalTime horaRetirada) {
		this.horaRetirada = horaRetirada;
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

	public List<LocalDate> getDatas() {
		return datas;
	}

	public void setDatas(List<LocalDate> datas) {
		this.datas = datas;
	}

	 
	
	
	
	

}
