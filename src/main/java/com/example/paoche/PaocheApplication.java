package com.example.paoche;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.example.paoche.mapper")
public class PaocheApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaocheApplication.class, args);
	}

}
