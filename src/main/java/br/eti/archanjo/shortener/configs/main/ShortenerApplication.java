package br.eti.archanjo.shortener.configs.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.eti.archanjo")
public class ShortenerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
