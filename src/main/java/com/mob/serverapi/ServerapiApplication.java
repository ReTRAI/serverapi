package com.mob.serverapi;

import com.mob.serverapi.device.repositories.database.tDeviceStatusRepository;
import com.mob.serverapi.reseller.repositories.database.tResellerMovementTypeRepository;
import com.mob.serverapi.support.repositories.database.tTicketStatusRepository;
import com.mob.serverapi.users.repositories.database.tUserRepository;
import com.mob.serverapi.users.repositories.database.tUserStatusRepository;
import com.mob.serverapi.users.repositories.database.tUserTypeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootApplication
public class ServerapiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        ConfigurableApplicationContext context = SpringApplication.run(ServerapiApplication.class, args);

        /**
         * Initial Data Seed
         */

        context.getBean(tUserTypeRepository.class).createDefaultUserType();
        context.getBean(tUserStatusRepository.class).createDefaultUserStatus();
        context.getBean(tTicketStatusRepository.class).createDefaultTicketStatus();
        context.getBean(tDeviceStatusRepository.class).createDefaultDeviceStatus();
        context.getBean(tResellerMovementTypeRepository.class).createDefaultResellerMovementType();
        context.getBean(tUserRepository.class).createDefaultUser();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServerapiApplication.class);
    }

}
