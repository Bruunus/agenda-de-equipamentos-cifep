package br.com.agenda.cifep.service.error;

import java.util.ArrayList;
import java.util.List;

import br.com.agenda.cifep.dto.reserva.RadarDeReservasAgendadasDTO;

public class EstoqueInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<RadarDeReservasAgendadasDTO> reservasInsuficientes;
	
	public EstoqueInsuficienteException(String message) {
        super(message);
    }
	
	public EstoqueInsuficienteException(List<RadarDeReservasAgendadasDTO> reservasInsuficientes) {
         this.setReservasInsuficientes(reservasInsuficientes);
         this.getReservasInsuficientes();
    }	

	public EstoqueInsuficienteException(String message, List<RadarDeReservasAgendadasDTO> list) {
		super(message);
		this.setReservasInsuficientes(list);
	}
	

	
	
	
	
	
	
	public List<RadarDeReservasAgendadasDTO> getReservasInsuficientes() {
		return reservasInsuficientes;
	}


	private void setReservasInsuficientes(List<RadarDeReservasAgendadasDTO> reservasInsuficientes) {
		this.reservasInsuficientes = reservasInsuficientes;
//		this.reservasInsuficientes.forEach(exceptions -> {System.out.println("Teste de impressão da Exception "+exceptions);});
	}
	
	
	/**
	 * Tratamento de erro que em caso de alguma data não tiver disponibilidade o erro é acionado e retorna a
	 * lista de reservas não aceitas - Esse metodo é usando na classe @UpdateEstoqueService no método 
	 * monitoradorDeEstoqueParaReservasPosteriores()
	 * 
	 * @return
	 */
	public List<RadarDeReservasAgendadasDTO> estoqueInsuficienteException() {

		System.err.println("Algumas datas não possui equipamentos disponíveis");	
		reservasInsuficientes.forEach(i -> {
			i.getDataRetirada();
			i.getDescricao();
			i.getSomaQuantidade();
			System.err.println(
					i.getDataRetirada()+"\t"+
					i.getDescricao()+"\tQuabtidade solicitada: "+
					i.getSomaQuantidade());
		});
		return reservasInsuficientes;		
	}
	
	

}
