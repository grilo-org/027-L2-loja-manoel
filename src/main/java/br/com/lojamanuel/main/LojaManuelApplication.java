package br.com.lojamanuel.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "br.com.lojamanuel") // Explicitly scan this package
public class LojaManuelApplication {

    public static void main(String[] args) {
        SpringApplication.run(LojaManuelApplication.class, args);
    }
}