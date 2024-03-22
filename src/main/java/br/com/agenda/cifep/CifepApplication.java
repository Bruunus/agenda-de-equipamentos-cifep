package br.com.agenda.cifep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.agenda.cifep.service.reserva.ReadReservaService;

@SpringBootApplication
public class CifepApplication {

	public static void main(String[] args) {
		SpringApplication.run(CifepApplication.class, args);
		System.out.println("Agenda de equipamentos rodando !!!");
		
		
	}

}
