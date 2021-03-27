package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WebChatAppApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebChatAppApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// Register resource handler for images
		registry.addResourceHandler("/uploaded-images/**")
				.addResourceLocations("classpath:/public/uploaded-images/")
				.setCachePeriod(0);
	}

}
