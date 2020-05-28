package com.example.demo;

import com.example.demo.api.PetApiDelegate;
import com.example.demo.api.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/**
 * Component to list out all of our endpoints at startup.
 */
@Component
class EndpointsListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndpointsListener.class);

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		applicationContext.getBean(RequestMappingHandlerMapping.class)
				.getHandlerMethods().forEach((key, value) -> LOGGER.info("{} {}", key, value));
	}
}
