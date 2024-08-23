package com.orientalexchange;

import org.springframework.boot.SpringApplication;

public class TestTradingsystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(TradingsystemApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
