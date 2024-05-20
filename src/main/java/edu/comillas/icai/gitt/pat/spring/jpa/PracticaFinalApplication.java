package edu.comillas.icai.gitt.pat.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PracticaFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticaFinalApplication.class, args);
	}

}
