package br.com.agenda.cifep.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agenda.cifep.model.Reserva;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AgendaDTO {
	
	 
	private LocalDate dataRetirada;		
	 
	private LocalTime horaRetirada;
	 
	private LocalDate dataDevolucao;	
	 
	private LocalTime horaDevolucao;
	
	@JsonIgnore
	private Reserva reserva;
	
	
	
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\nData da retirada: " + this.getDataRetirada() + " - Hora da retirada: " + this.getHoraRetirada()
		+ "\nData da devolução: "+ this.getDataDevolucao() + " - Hora da devolução: "+this.getHoraDevolucao();
	}

}
