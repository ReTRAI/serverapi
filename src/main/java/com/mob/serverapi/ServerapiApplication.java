package com.mob.serverapi;

import com.mob.serverapi.users.repositories.database.tUserTypeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerapiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ServerapiApplication.class, args);
		context.getBean(tUserTypeRepository.class).createDefaultUserType();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServerapiApplication.class);
	}

}
