package com.doordash.interview.phonenumberparser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
class PhoneNumberParserApplication {


	fun main(args: Array<String>) {
		runApplication<PhoneNumberParserApplication>(*args)
	}
}



