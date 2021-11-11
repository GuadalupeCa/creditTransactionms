package com.finance.creditTransactionms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CreditTransactionmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditTransactionmsApplication.class, args);
	}

}
