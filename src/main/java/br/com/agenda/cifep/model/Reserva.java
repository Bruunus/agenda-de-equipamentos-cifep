package br.com.agenda.cifep.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private String nome;	
	
	@NotNull
	private String sobrenome;
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"reserva"})
	private List<ReservaDeFluxoDeEquipamento> reservaDeFluxoDeEquipamentos = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	private List<Agenda> agenda = new ArrayList<>();
		
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusReserva status;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoReserva tipo;
	
	@NotNull
	private String recorrenciaDeToda;	
	
	
	
	public Reserva() {}
	
	public Reserva(Long id, @NotNull String setor, @NotNull String responsavel, List<ReservaDeFluxoDeEquipamento> reservaDeFluxoDeEquipamentos,
			List<Agenda> agenda, @NotNull StatusReserva status, @NotNull TipoReserva tipo,
			@NotNull String recorrenciaDeToda) {
		this.id = id;
		this.setor = setor;
		this.nome = responsavel;
		this.reservaDeFluxoDeEquipamentos = reservaDeFluxoDeEquipamentos;
		this.agenda = agenda;
		this.status = status;
		this.tipo = tipo;
		this.recorrenciaDeToda = recorrenciaDeToda;
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

	public String getNome() {
		return nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setNome(String responsavel) {
		this.nome = responsavel;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<ReservaDeFluxoDeEquipamento> getEquipamentos() {
		return reservaDeFluxoDeEquipamentos;
	}

	public void setEquipamentos(List<ReservaDeFluxoDeEquipamento> list) {
		this.reservaDeFluxoDeEquipamentos = list;
	}
		
	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	
	
	public StatusReserva getStatus() {
		return status;
	}

	public void setStatus(StatusReserva status) {
		this.status = status;
	}

	public TipoReserva getTipo() {
		return tipo;
	}

	public void setTipo(TipoReserva tipo) {
		this.tipo = tipo;
	}

	public String getRecorrenciaDeToda() {
		return recorrenciaDeToda;
	}

	public void setRecorrenciaDeToda(String recorrenciaDeToda) {
		this.recorrenciaDeToda = recorrenciaDeToda;
	}
	
	public List<Agenda> getAgenda() {
		return agenda;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " "+this.id;
	}
 
	
	

}
