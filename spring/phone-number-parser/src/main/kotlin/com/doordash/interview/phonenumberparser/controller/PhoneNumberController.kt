package com.doordash.interview.phonenumberparser.controller

import com.doordash.interview.phonenumberparser.model.Record
import com.doordash.interview.phonenumberparser.model.toCommandView
import com.doordash.interview.phonenumberparser.model.toQueryView
import com.doordash.interview.phonenumberparser.service.PhoneService
import com.doordash.interview.phonenumberparser.view.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PhoneNumberController (private val phoneService: PhoneService){

    //health check endpoint, depending on the env
    @GetMapping("/ping")
    fun ping(): String = "hello"

    @GetMapping("/phone-numbers/{id}")
    fun findById(@PathVariable("id") recordId: Long): ResponseEntity<QueryView> =
            phoneService.findById(recordId)
                    .map { record -> ResponseEntity.ok(record.toQueryView())}
                    .orElse(ResponseEntity.notFound().build())



    @PostMapping("/phone-numbers")
    fun processData(@Valid @RequestBody input: RawInput): ResponseEntity<Output> {
        //1. parse input into a list of records
        val records: List<Record> = input.toRecords()
        //2. process each record in the list
        val processedRecords:List<Record> = records.map{record -> phoneService.upsertRecord(record)}
        //3. build a response entity around the processed list and return
        val commandViewRecords:List<CommandView> = processedRecords.map { record -> record.toCommandView() }
        return ResponseEntity.ok(Output(commandViewRecords))
    }

}