package com.doordash.interview.phonenumberparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhoneNumberParserApplicationTests() {

	@Autowired
	lateinit var restTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
	}

	@Test
	fun `Querying a known id returns expected result`() {
		val entity = restTemplate.getForEntity<String>("/phone-numbers/386")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains("415-415-4155")
	}


}
