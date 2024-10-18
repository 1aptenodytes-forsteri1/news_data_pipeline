package com.newsdatapipeline.backend;

import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Services.LocationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
