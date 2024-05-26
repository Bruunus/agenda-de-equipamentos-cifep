package br.com.agenda.cifep.config.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketMensagens implements WebSocketHandler {

	
	/*
	 * Esse método é invocado quando uma conexão WebSocket é estabelecida com sucesso. 
	 * Ele recebe como parâmetro um objeto WebSocketSession que representa a sessão do WebSocket 
	 * estabelecida com o cliente. Nesse método, você pode definir ações a serem executadas após 
	 * a conexão ser estabelecida.
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * Esse método é invocado quando uma mensagem é recebida pelo WebSocket. 
	 * Ele recebe como parâmetros o objeto WebSocketSession, que representa a sessão WebSocket 
	 * estabelecida com o cliente, e o objeto WebSocketMessage, que representa a mensagem recebida. 
	 * Nesse método, você pode processar a mensagem recebida e definir as ações apropriadas para 
	 * lidar com ela.
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * Esse método é invocado quando ocorre um erro de transporte durante a comunicação WebSocket. 
	 * Ele recebe como parâmetros o objeto WebSocketSession, que representa a sessão WebSocket 
	 * estabelecida com o cliente, e uma exceção Throwable que representa o erro ocorrido. 
	 * Nesse método, você pode definir as ações apropriadas para lidar com o erro de transporte.
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * Esse método é invocado quando a conexão WebSocket é encerrada. Ele recebe como parâmetros o objeto 
	 * WebSocketSession, que representa a sessão WebSocket encerrada, e o objeto CloseStatus, 
	 * que representa o status de encerramento da conexão. Nesse método, você pode definir as 
	 * ações a serem executadas após o encerramento da conexão.
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub

	}

	
	
	/**
	 * Esse método indica se o servidor suporta mensagens parciais ou não. No exemplo fornecido, 
	 * o método retorna false, o que significa que o servidor não suporta mensagens parciais.
	 */
	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
