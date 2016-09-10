package com.byhiras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableCaching
@EnableJms
public class AuctionTrackerServer {
	public static void main(String[] args) {
		start(args);
	}

	private static void start(String[] args) {
		SpringApplication.run(AuctionTrackerServer.class, args);
	}	
}
