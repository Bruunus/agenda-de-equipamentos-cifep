package br.com.agenda.cifep.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
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
	private String responsavel;	
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"reserva"})
	private List<Equipamento> equipamentos = new ArrayList<>();
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull 
	private LocalDate dataRetirada;	
	
	
	@NotNull @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaRetirada;
	
	// preenchida no momento da baixa da reserva
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataDevolucao;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaDevolucao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusReserva status;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoReserva tipo;
	
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

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalTime getHoraRetirada() {
		return horaRetirada;
	}

	public void setHoraRetirada(LocalTime horarioRetirada) {
		this.horaRetirada = horarioRetirada;
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
	
	
	
	
 
	
	

}
