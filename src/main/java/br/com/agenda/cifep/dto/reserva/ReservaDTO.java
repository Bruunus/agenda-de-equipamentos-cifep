package br.com.agenda.cifep.dto.reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.agenda.cifep.dto.equipamentos.ReservaDeEquipamentoDTO;
import br.com.agenda.cifep.model.Reserva;
import br.com.agenda.cifep.model.StatusReserva;
import br.com.agenda.cifep.model.TipoReserva;

public class ReservaDTO {
	
	private Long id;
	private String setor;
	private String nome;		
	private String sobrenome;
	 
	 
	private StatusReserva statusReserva;
	private TipoReserva tipoReserva;
	
	private List<ReservaDeEquipamentoDTO> equipamentos;
	
	private List<AgendaDTO> agenda;
	 
	
	private String recorrenciaDeToda;
 
	
	
	public ReservaDTO() {
		this.agenda = new ArrayList<>();
	}
	
	
	 
	
	
	public ReservaDTO(String setor, String nome, LocalDate dataRetirada, LocalTime horaRetirada, String descricaoEquipamento, Integer quantidadeEquipamento) {
	    this.setor = setor;
	    this.nome = nome; 
	}








	@SuppressWarnings("unused")
	public boolean validationItens(ReservaDTO reservaDTO) {
		
	 if (
			reservaDTO.getSetor() == null || reservaDTO.getSetor().isEmpty() ||
			reservaDTO.getNome() == null || reservaDTO.getNome().isEmpty() ||
			reservaDTO.getSobrenome() == null || reservaDTO.getSobrenome().isEmpty() ||
			reservaDTO.getEquipamentos() == null || reservaDTO.getEquipamentos().isEmpty()) 
	 {
	        System.err.println("Erro: Erro detectado na validação dos itens - "
	        		+ "Setor, nome, sobrenome e equipamentos são obrigatórios.");
	        
	        System.out.println(
	        		"Setor: "+reservaDTO.getSetor() +"\n"+
	        		"Nome: "+reservaDTO.getNome() +"\n" +
    				"Sobrenome: "+reservaDTO.getSobrenome() +"\n"+
	        		"Equipamento(s): "	        		
	        		);
	        
	        List<ReservaDeEquipamentoDTO> equipamentosError = new ArrayList<>();
	        
	        if (reservaDTO.getEquipamentos() != null) {
	            equipamentosError.addAll(reservaDTO.getEquipamentos());
	            equipamentosError.forEach(equipamento -> {
		        	System.out.println(equipamento+"");
		        });
	        } else {
	        	System.out.println("A lista de equipamentos está vazia, não é possível processar.");
	        }
	        
	         
	        
	        
	        
	        
	        return false;
	 }

	    for (ReservaDeEquipamentoDTO equip : reservaDTO.getEquipamentos()) {
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
	
	
//	public boolean validationItens(List<ReservaDTO> reservaDTO) {
//		
//		 for(ReservaDTO verificador : reservaDTO) {
//			
//		 if (
//			 verificador.getSetor() == null || verificador.getSetor().isEmpty() ||
//			 verificador.getResponsavel() == null || verificador.getResponsavel().isEmpty() ||
//			 verificador.getEquipamentos() == null || verificador.getEquipamentos().isEmpty()
//		 ) 
//			 {
//			        System.err.println("Erro: Erro detectado na validação dos itens - Setor, responsável e equipamentos são obrigatórios.");
//			        return false;
//			 }
//
//			    for (ReservaDeEquipamentoDTO equip : verificador.getEquipamentos()) {
//			        if (
//			        		equip == null || equip.getDescricao() == null || equip.getDescricao().isEmpty() || 
//			        		equip.getQuantidade() == 0 || equip.getQuantidade() == null
//			        ) {
//			            System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipamento.");
//			            return false; 
//			        }
//			
//			        // Verifica se a descrição do equipamento é composta apenas de espaços em branco
//			        if (equip.getDescricao().trim().isEmpty()) {
//			            System.err.println("Erro: Erro detectado na declaração de equipamento - Não pode ser nulo nem vazio ou pelo menos um equipamento.");
//			            return false; // Se a descrição contiver apenas espaços em branco, retorna false
//			        }
//			    }			    
//			
//		};
//		
//		return true;
//				       
//		}
	
	 
	public List<ReservaDTO> carregarDados(List <Reserva> reserva) {
		
		List<ReservaDTO> listaDeDados = new ArrayList<>();
		
		reserva.forEach(loadData -> {
			ReservaDTO reservaDTO = new ReservaDTO();
			
			reservaDTO.setId(loadData.getId());
			reservaDTO.setSetor(loadData.getSetor());
			reservaDTO.setNome(loadData.getNome());
			reservaDTO.setSobrenome(loadData.getSobrenome());
			
			// para todo join na tabela
			List<ReservaDeEquipamentoDTO> equipamentosDTO = new ArrayList<>();
			loadData.getEquipamentos().forEach(equipamento -> {
				ReservaDeEquipamentoDTO equipDTO = new ReservaDeEquipamentoDTO();
				
				equipDTO.setId(equipamento.getId());
				equipDTO.setDescricao(equipamento.getDescricao());
				equipDTO.setQuantidade(equipamento.getQuantidade());	
				
				equipamentosDTO.add(equipDTO);
			});						
			reservaDTO.setEquipamentos(equipamentosDTO);
			
			
			List<AgendaDTO> agenda = new ArrayList<>();			
			loadData.getAgenda().forEach(datas -> {
				AgendaDTO agendaDTO = new AgendaDTO();
				
				agendaDTO.setDataRetirada(datas.getDataRetirada());
				agendaDTO.setHoraRetirada(datas.getHoraRetirada());
				agendaDTO.setDataDevolucao(datas.getDataDevolucao());
				agendaDTO.setHoraDevolucao(datas.getHoraDevolucao());
				
				
				agenda.add(agendaDTO);
			});
			
			reservaDTO.setAgenda(agenda);
			
			reservaDTO.setStatusReserva(loadData.getStatus());
			reservaDTO.setTipoReserva(loadData.getTipo());			
			reservaDTO.setRecorrenciaDeToda(loadData.getRecorrenciaDeToda());
			
			
			listaDeDados.add(reservaDTO);			
			
		});	
				
		return listaDeDados;
	}
		
	
	
	
		
 
		
		
 
	
	private static void converterTime(Object setHoraRetirada) {
		// TODO Auto-generated method stub
		
	}




 





	public Long getId() {
		return id;
	}
	
	public String getSetor() {
		return setor;
	}
	public String getNome() {
		return nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public List<ReservaDeEquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}
	public List<AgendaDTO> getAgenda() {
		return agenda;
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



	public void setId(Long id) {
		this.id = id;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}	
	public void setEquipamentos(List<ReservaDeEquipamentoDTO> equipamentos) {
		this.equipamentos = equipamentos;
	}
	public void setAgenda(List<AgendaDTO> agenda) {
		this.agenda = agenda;
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



	@Override
	public String toString() {
		 
		return "Setor: "+ this.getSetor() + "\nResponsável: "+ this.getNome() + 
				"\nEquipamento(s): " + this.getEquipamentos() + "\nAgenda: " + this.getAgenda();
	}





	 

	
	
	
	
	
	
	
	
	
	
	
	
	

}
