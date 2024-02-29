package br.com.agenda.cifep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CifepApplication {

	public static void main(String[] args) {
		SpringApplication.run(CifepApplication.class, args);
		System.out.println("Agenda de equipamentos rodando !!!");
	}

}
