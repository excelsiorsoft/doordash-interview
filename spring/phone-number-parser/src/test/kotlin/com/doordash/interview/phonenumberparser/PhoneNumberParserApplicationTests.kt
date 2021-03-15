package com.doordash.interview.phonenumberparser

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.*
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

	@Test
	fun `Posting raw data to the app's create endpoint results in 2 new records`() {

		val objectMapper = ObjectMapper()

		val upsertUrl = "/phone-numbers"
		val headers = HttpHeaders()
		headers.setContentType(MediaType.APPLICATION_JSON)
		val rawInput = JSONObject().apply { put("raw_phone_numbers", "(Home) 415-415-4155 (Cell) 415-514-5145") }

		val request: HttpEntity<String> = HttpEntity<String>(rawInput.toString(), headers)
		val responseEntityStr: ResponseEntity<String> = restTemplate.postForEntity<String>(upsertUrl, request, String::class.java)

		val root: JsonNode = objectMapper.readTree(responseEntityStr.body)
		println(root)


		assertThat(root).isNotEmpty
		//assertThat(root.count()).isEqualTo(2)
		assertThat(responseEntityStr.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(responseEntityStr.body).contains("4154154155")
		assertThat(responseEntityStr.body).contains("4155145145")
	}


}
