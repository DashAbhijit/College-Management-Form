package com.csmtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CollegeAdmissionFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeAdmissionFormApplication.class, args);
	}

}
