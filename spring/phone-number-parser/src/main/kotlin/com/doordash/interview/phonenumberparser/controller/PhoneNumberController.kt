package com.doordash.interview.phonenumberparser.controller

import com.doordash.interview.phonenumberparser.model.Record
import com.doordash.interview.phonenumberparser.model.toCommandView
import com.doordash.interview.phonenumberparser.model.toQueryView
import com.doordash.interview.phonenumberparser.service.PhoneService
import com.doordash.interview.phonenumberparser.view.CommandView
import com.doordash.interview.phonenumberparser.view.QueryView
import com.doordash.interview.phonenumberparser.view.RawInput
import com.doordash.interview.phonenumberparser.view.toRecords
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class PhoneNumberController (private val phoneService: PhoneService){

    @GetMapping("/ping")
    fun ping(): String = "hello"

    @GetMapping("/phone-numbers/{id}")
    fun findById(@PathVariable("id") recordId: Long): ResponseEntity<QueryView> =
            phoneService.findById(recordId)
                    .map { record -> ResponseEntity.ok(record.toQueryView())}
                    .orElse(ResponseEntity.notFound().build())

    /*@PostMapping("/phone-numbers")
    fun processData(@Valid @RequestBody record: Record): ResponseEntity<List<CommandView>> {
        //1. parse input into a list of records
        val records: List<Record> = listOf(record);

        //2. for each record in the list
        val processedRecords:List<Record> = records.map{record -> phoneService.upsertRecord(record)}
        //3. build a response entity around the processed list and return
        return ResponseEntity.ok(processedRecords.map { record -> record.toCommandView() })
    }*/


    @PostMapping("/phone-numbers")
    /*fun processData(@Valid @RequestBody record: Record): ResponseEntity<List<CommandView>> {*/
    fun processData(@Valid @RequestBody input: RawInput): ResponseEntity<List<CommandView>> {
        //1. parse input into a list of records
        val records: List<Record> = input.toRecords()

        //2. for each record in the list
        val processedRecords:List<Record> = records.map{record -> phoneService.upsertRecord(record)}
        //3. build a response entity around the processed list and return
        return ResponseEntity.ok(processedRecords.map { record -> record.toCommandView() })
    }

}