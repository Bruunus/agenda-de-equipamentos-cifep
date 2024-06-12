package br.com.agenda.cifep.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva-de-fluxo-de-equipamento")
public class ReservaDeFluxoDeEquipamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Integer quantidade;
	 
	
	@ManyToOne
    @JoinColumn(name = "reserva_id")
	@JsonIgnore
    private Reserva reserva;

	
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

	

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
	@JsonIgnore
    public Reserva getReservaIgnore() {
        return reserva;
    }
	
	
	@Override
	public String toString() {
		 
		return this.descricao+" "+this.quantidade;
	}
	
	

}
