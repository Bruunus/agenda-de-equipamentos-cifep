package br.com.agenda.cifep.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EquipamentoDTO {
	
	private Long id;	 
	private String descricao;
	private Integer quantidade;
    private String reserva;
    private LocalDate dataRetirada;
	private LocalTime horarioRetirada;
	private LocalDate dataDevolucao;
	private LocalTime horaDevolucao;
	
    

    
    public EquipamentoDTO(String descricao, Integer quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getReserva() {
		return reserva;
	}

	public void setReserva(String reserva) {
		this.reserva = reserva;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	

	
	

}
