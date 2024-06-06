package br.com.agenda.cifep.service.error;

public class EstoqueInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EstoqueInsuficienteException(String message) {
        super(message);
    }

}
