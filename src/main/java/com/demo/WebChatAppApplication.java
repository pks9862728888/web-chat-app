package com.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@SpringBootApplication
public class WebChatAppApplication implements WebMvcConfigurer {

	@Value("${image.storage.location}")
	private String storageFolder;

	public static void main(String[] args) {
		SpringApplication.run(WebChatAppApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String filePath = (new File(storageFolder)).getAbsolutePath();

		// Finding the appender to use while creating fully qualified path
		String appender;

		if (filePath.endsWith("/") || filePath.endsWith("//")) {
			appender = "";
		} else if (filePath.contains("/")) {
			appender = "/";
		} else {
			appender = "\\";
		}

		// Register resource handler for images
		registry.addResourceHandler("/public/**")
				.addResourceLocations("file:///" + filePath + appender)
				.setCachePeriod(0);
	}

}
