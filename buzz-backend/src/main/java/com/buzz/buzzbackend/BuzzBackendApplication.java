package com.buzz.buzzbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@ComponentScan({"com.buzz.controllers"})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class BuzzBackendApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(BuzzBackendApplication.class, args);
	}
}
