package com.byhiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradeServer {
	public static void main(String[] args) {
		start(args);
	}

	private static void start(String[] args) {
		SpringApplication.run(TradeServer.class, args);
	}	
}
