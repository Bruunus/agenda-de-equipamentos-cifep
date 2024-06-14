package br.com.agenda.cifep.dto.equipamentos;

public class EstoqueQuantidadeDTO {	 
 
	
	private String valor;	
	 
	private Integer quantidade;
	
	public EstoqueQuantidadeDTO() {}
	public EstoqueQuantidadeDTO(String valor, int quantidade) {
		this.valor = valor;
		this.quantidade = quantidade;
	}
	

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Valor: "+this.valor+" "+"Quantidade: "+this.quantidade;
	}
	
	
}
