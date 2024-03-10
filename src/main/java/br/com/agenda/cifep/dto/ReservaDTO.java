package br.com.agenda.cifep.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;

public class ReservaDTO {
	
	private String setor;
	private String responsavel;		
	
	private LocalDate dataRetirada;
	private LocalTime horaRetirada;
	
	private LocalDate dataDevolucao;
	
	@JsonSerialize
	@JsonDeserialize
	private LocalTime horaDevolucao;
	
	private StatusReserva statusReserva;
	private TipoReserva tipoReserva;
	
	private List<EquipamentoDTO> equipamentos;
	
	@JsonIgnore
	private String recorrenciaDeToda;
 
	
	
	public ReservaDTO() {}
	
	
	 
	
	
	public ReservaDTO(String setor, String responsavel, LocalDate dataRetirada, LocalTime horaRetirada, String descricaoEquipamento, Integer quantidadeEquipamento) {
	    this.setor = setor;
	    this.responsavel = responsavel;
	    this.dataRetirada = dataRetirada;
	    this.horaRetirada = horaRetirada; 
	}








	public boolean validationItens(ReservaDTO reservaDTO) {
		
	 if (
			reservaDTO.getSetor() == null || reservaDTO.getSetor().isEmpty() ||
			reservaDTO.getResponsavel() == null || reservaDTO.getResponsavel().isEmpty() ||
            reservaDTO.getEquipamentos() == null || reservaDTO.getEquipamentos().isEmpty()) 
	 {
	        System.err.println("Erro: Erro detectado na validação dos itens - Setor, responsável e equipamentos são obrigatórios.");
	        return false;
	 }

	    for (EquipamentoDTO equip : reservaDTO.getEquipamentos()) {
	        if (
	        		equip == null || equip.getDescricao() == null || equip.getDescricao().isEmpty() || 
	        		equip.getQuantidade() == 0 || equip.getQuantidade() == null
	        ) {
	            System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipamento.");
	            return false; 
	        }
	
	        // Verifica se a descrição do equipamento é composta apenas de espaços em branco
	        if (equip.getDescricao().trim().isEmpty()) {
	            System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipamento.");
	            return false; // Se a descrição contiver apenas espaços em branco, retorna false
	        }
	    }
	    return true;
	       
	}
	
	
	public List<ReservaDTO> carregarDados(List <Reserva> reserva, int code) {
		
		List<ReservaDTO> listaDeDados = new ArrayList<>();
		
		reserva.forEach(loadData -> {
			ReservaDTO reservaDTO = new ReservaDTO();
			
			reservaDTO.setSetor(loadData.getSetor());
			reservaDTO.setResponsavel(loadData.getResponsavel());
			
			// para todo join na tabela
			List<EquipamentoDTO> equipamentosDTO = new ArrayList<>();
			loadData.getEquipamentos().forEach(equipamento -> {
				EquipamentoDTO equipDTO = new EquipamentoDTO();
				
				equipDTO.setId(equipamento.getId());
				equipDTO.setDescricao(equipamento.getDescricao());
				equipDTO.setQuantidade(equipamento.getQuantidade());	
				
				equipamentosDTO.add(equipDTO);
			});
			
			
			reservaDTO.setEquipamentos(equipamentosDTO);
			reservaDTO.setDataRetirada(loadData.getDataRetirada());
			reservaDTO.setHorRetirada(loadData.getHoraRetirada());
			reservaDTO.setStatusReserva(loadData.getStatus());
			reservaDTO.setTipoReserva(loadData.getTipo());
			reservaDTO.setDataDevolucao(loadData.getDataDevolucao());
			reservaDTO.setHoraDevolucao(loadData.getHoraDevolucao());
			
			if(code == 1) {
				//reservaDTO.setRecorrenciaDeToda(loadData.getRecorrenciaDeToda());
				listaDeDados.add(reservaDTO);
			} else {
				listaDeDados.add(reservaDTO);
			}
			
			
			
		});	
				
		return listaDeDados;
	}
		
		
		
 
		
		
 
	
	
	
	public String getSetor() {
		return setor;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public List<EquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}
	public LocalDate getDataRetirada() {
		return dataRetirada;
	}
	public LocalTime getHoraRetirada() {
		return horaRetirada;
	}
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	public LocalTime getHoraDevolucao() {
		return horaDevolucao;
	}
		
	public StatusReserva getStatusReserva() {
		return statusReserva;
	}
	public TipoReserva getTipoReserva() {
		return tipoReserva;
	}
	
	@JsonProperty
	public String getRecorrenciaDeToda() {
		return recorrenciaDeToda;
	}





	public void setSetor(String setor) {
		this.setor = setor;
	}	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}	
	public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
		this.equipamentos = equipamentos;
	}
	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public void setHorRetirada(LocalTime horaRetirada) {
		this.horaRetirada = horaRetirada;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public void setHoraDevolucao(LocalTime horaDevolucao) {
		this.horaDevolucao = horaDevolucao;
	}

	public void setHoraRetirada(LocalTime horaRetirada) {
		this.horaRetirada = horaRetirada;
	}

	public void setStatusReserva(StatusReserva statusReserva) {
		this.statusReserva = statusReserva;
	}
	public void setTipoReserva(TipoReserva tipoReserva) {
		this.tipoReserva = tipoReserva;
	}

	public void setRecorrenciaDeToda(String recorrenciaDeToda) {
		this.recorrenciaDeToda = recorrenciaDeToda;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
