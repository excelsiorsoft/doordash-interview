package com.doordash.interview.phonenumberparser.controller

import com.doordash.interview.phonenumberparser.model.Record
import com.doordash.interview.phonenumberparser.service.PhoneService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PhoneNumberController (private val phoneService: PhoneService){

    @GetMapping("/ping")
    fun ping(): String = "hello"

    @GetMapping("/phone-numbers/{id}")
    fun findById(@PathVariable("id") recordId: Long): ResponseEntity<Record> =
            phoneService.findById(recordId)

    @PostMapping("/phone-numbers")
    fun processRecord(@Valid @RequestBody record: Record): ResponseEntity<Record> =
            phoneService.upsertRecord(record)

}