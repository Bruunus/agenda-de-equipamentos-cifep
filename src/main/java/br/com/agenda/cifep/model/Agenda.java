package br.com.agenda.cifep.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "data")
public class Agenda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long agendaId;
	
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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataFinalizada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaFinalizada;
	
	@ManyToOne
    @JoinColumn(name = "reserva_id")
	private Reserva reserva;
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	public LocalDate getDataFinalizada() {
		return dataFinalizada;
	}

	public void setDataFinalizada(LocalDate dataFinalizada) {
		this.dataFinalizada = dataFinalizada;
	}

	public LocalTime getHoraFinalizada() {
		return horaFinalizada;
	}

	public void setHoraFinalizada(LocalTime horaFinalizada) {
		this.horaFinalizada = horaFinalizada;
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

	public Reserva getReserva() {
		return reserva;
	}
	
	
	
	
	

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Long getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(Long agendaId) {
		this.agendaId = agendaId;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nData da retirada: " + this.getDataRetirada() + " - Hora da retirada: " + this.getHoraRetirada()
		+ "\nData da devolução: "+ this.getDataDevolucao() + " - Hora da devolução: "+this.getHoraDevolucao();
	}
	
	
	
	

}
