package br.com.agenda.cifep.dto.equipamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.agenda.cifep.model.EstoqueEquipamento;
import br.com.agenda.cifep.repository.equipamento.EstoqueDeEquipamentoRepository;
import br.com.agenda.cifep.service.equipamento.ReadEquipamentoService;

public class EquipamentoValidation {
	
	
	@Autowired
	private EstoqueDeEquipamentoRepository estoqueDeEquipamentoRepository;
	
	
	@SuppressWarnings("unused")
	public boolean validarEquipamentoNaList(/*List<ReservaDeFluxoDeEquipamentoDTO> list*/) {
		 
		System.out.println("Entrado no metodo de validação de equipamento");
		
		// pego a lista de equipamentos vinda da request	 
//		List<ReservaDeFluxoDeEquipamentoDTO> listHttp = list;
		
		// carrego a lista de estoque de equipamentos do banco
		ReadEquipamentoService service = new ReadEquipamentoService();
		
		List<EstoqueEquipamento> listData = estoqueDeEquipamentoRepository.findAll();
		
		// realizo uma iteração para localizar os dados iguais entre eles
		
//		for(EstoqueEquipamento listaDataBase: listData) {
//			System.out.println(listaDataBase);
//			System.out.println("Validação de equipamento");
//		}
		
		
		return true;
		
		
		
		// se a quantidade do estoque for menor que a quantidade no lista do request retorna false
		
		
//		if(false) {
//			return false;
//		}
		
		
	}
	
	 
	
}
