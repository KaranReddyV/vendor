package com.ojas.vendor;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.example.controller" })
@EnableJpaRepositories({ "com.example.repo" })
@EntityScan({ "com.example.entity" })
public class VendorApplication {
	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(VendorApplication.class, args);
	}
}
